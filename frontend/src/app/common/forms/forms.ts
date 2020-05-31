// tslint:disable-next-line: import-blacklist
import {
  AbstractControlOptions,
  AsyncValidatorFn,
  FormArray as AngularFormArray,
  FormControl as AngularFormControl,
  FormGroup as AngularFormGroup,
  ValidatorFn,
} from '@angular/forms';
import { Observable } from 'rxjs';

/*
  As of writing this piece of code, the structure of Reactive Forms was not type safe.

  An example:

  Imagine you have created the following form:
  ```
    const myForm = new FormGroup({
      name: new FormGroup({
        firstName: new FormControl(''),
        lastName: new FormControl('')
      })
    });
  ```

  Now, when typing in the following, you don't get any compiler error:

  ```
    console.log(myForm.controls.theName);
  ```

  Even worse: You cannot write the following line:
  ```
    console.log(myForm.controls.name.controls.firstName);
  ```
  because "name" is typed as an "AbstractControl".

  There are a few libraries on NPM that (might) solve this issue:
  - https://www.npmjs.com/package/ngx-strongly-typed-forms
  - https://www.npmjs.com/package/@ng-stack/forms
  - https://www.npmjs.com/package/ngx-sub-form
  However, they only had a few thousand downloads per week.
  Some were even not compatible with Ivy.

  So, here comes a really simple attempt to fix this issue on our own.
*/

type MyFormControls<T> = {
  [key in keyof T]: MyGenericFormControl<T[key]>;
};

interface ControlUniquenessItem {
  /**
   * This element is used to allow the typechecker to distinguish "UseControl" elements from other ones.
   * Pretty "hacky", but it works...
   */
  __el: 'ctrl';
}
/**
 * A hint for the typechecker that we want to use a FormControl instead of a FormArray here.
 */
export type UseControl<T extends string[] | number[]> = T &
  ControlUniquenessItem;

type FormValue<T> = {
  // Remove the "ControlUniquenessItem" and make everything optional.
  [key in keyof T]?: T[key] extends UseControl<infer U> ? U : FormValue<T[key]>;
};

type MyGenericFormControl<T> = T extends UseControl<infer U>
  ? FormControl<U>
  : T extends string | number
  ? FormControl<T>
  : T extends (infer V)[]
  ? FormArray<V>
  : T extends {} // If it is an Object, it is (most likely) a group.
  ? FormGroup<T>
  : never; // Anything else is not permitted.

/**
 * A recursive Partial.
 */
type DeepPartial<T> = {
  [key in keyof T]?: DeepPartial<T[key]>;
};

/**
 * In some methods of the Form Controls, you can either pass a raw value, or a value and a disabled state.
 */
type InitialValueOrInitialValueAndDisabled<T> =
  | T
  | { value: T; disabled: boolean };

/**
 * A wrapper around Angular's FormControl.
 * For an example, see comment on the FormGroup wrapper class.
 */
export class FormControl<
  T extends string | string[] | number | number[]
> extends AngularFormControl {
  readonly value: T;
  readonly valueChanges: Observable<T>;

  constructor(
    formState?: InitialValueOrInitialValueAndDisabled<T>,
    validatorOrOpts?:
      | ValidatorFn
      | ValidatorFn[]
      | AbstractControlOptions
      | null,
    asyncValidator?: AsyncValidatorFn | AsyncValidatorFn[] | null
  ) {
    super(formState, validatorOrOpts, asyncValidator);
  }

  setValue(
    value: T,
    options?: {
      onlySelf?: boolean;
      emitEvent?: boolean;
      emitModelToViewChange?: boolean;
      emitViewToModelChange?: boolean;
    }
  ): void {
    super.setValue(value, options);
  }

  patchValue(
    value: T,
    options?: {
      onlySelf?: boolean;
      emitEvent?: boolean;
      emitModelToViewChange?: boolean;
      emitViewToModelChange?: boolean;
    }
  ): void {
    super.patchValue(value, options);
  }

  reset(
    formState?: InitialValueOrInitialValueAndDisabled<T>,
    options?: {
      onlySelf?: boolean;
      emitEvent?: boolean;
    }
  ): void {
    super.reset(formState, options);
  }
}

/**
 * A wrapper around Angular's FormArray.
 * For an example, see comment on the FormGroup wrapper class.
 */
export class FormArray<T> extends AngularFormArray {
  /**
   * Important: The "value" only includes currently not disabled elements.
   */
  readonly value: FormValue<T>[];

  constructor(
    public controls: MyGenericFormControl<T>[],
    validatorOrOpts?:
      | ValidatorFn
      | ValidatorFn[]
      | AbstractControlOptions
      | null,
    asyncValidator?: AsyncValidatorFn | AsyncValidatorFn[] | null
  ) {
    super(controls, validatorOrOpts, asyncValidator);
  }

  at(index: number): MyGenericFormControl<T> {
    return super.at(index) as MyGenericFormControl<T>;
  }

  push(control: MyGenericFormControl<T>): void {
    super.push(control);
  }

  insert(index: number, control: MyGenericFormControl<T>): void {
    super.insert(index, control);
  }

  setControl(index: number, control: MyGenericFormControl<T>): void {
    super.setControl(index, control);
  }

  setValue(
    value: MyGenericFormControl<T>[],
    options?: {
      onlySelf?: boolean;
      emitEvent?: boolean;
    }
  ): void {
    super.setValue(value, options);
  }

  patchValue(
    value: MyGenericFormControl<T>[],
    options?: {
      onlySelf?: boolean;
      emitEvent?: boolean;
    }
  ): void {
    super.patchValue(value, options);
  }

  reset(
    value?: InitialValueOrInitialValueAndDisabled<T>[],
    options?: {
      onlySelf?: boolean;
      emitEvent?: boolean;
    }
  ): void {
    super.reset(value, options);
  }
}

/**
 * A wrapper around Angular's FormGroup.
 * The structure is type safe. Example:
 *
 * ``` ts
 * interface MyModel {
 *  address: Address;
 *  order: Order[];
 * }
 *
 * interface Address {
 *  name: Name;
 * }
 * interface Name {
 *  firstName: string;
 *  lastName: string;
 * }
 *
 * interface Order {
 *   itemName: string;
 *   amount: number;
 * }
 *
 * const test = new FormGroup<MyModel>({
 *  address: new FormGroup<Address>({
 *    name: new FormGroup<Name>({
 *       firstName: new FormControl(''),
 *       lastName: new FormControl(''),
 *    }),
 *  }),
 *
 *  order: new FormArray<Order>([]),
 * });
 *
 * console.log(test.controls.address.controls.name.controls.firstName.value);
 * ```
 */
export class FormGroup<T> extends AngularFormGroup {
  /**
   * Important: The "value" only includes currently not disabled elements.
   */
  readonly value: FormValue<T>;
  /**
   * Important: The data passed in the Observable only includes currently not disabled elements.
   */
  readonly valueChanges: Observable<DeepPartial<T>>;

  constructor(
    public controls: MyFormControls<T>,
    validatorOrOpts?:
      | ValidatorFn
      | ValidatorFn[]
      | AbstractControlOptions
      | null,
    asyncValidator?: AsyncValidatorFn | AsyncValidatorFn[] | null
  ) {
    super(controls, validatorOrOpts, asyncValidator);
  }

  setValue(
    value: T,
    options?: {
      onlySelf?: boolean;
      emitEvent?: boolean;
    }
  ): void {
    super.setValue(value, options);
  }

  patchValue(
    value: DeepPartial<T>,
    options?: {
      onlySelf?: boolean;
      emitEvent?: boolean;
    }
  ): void {
    super.patchValue(value, options);
  }

  // It is actually also possible to pass an object containing, for each item, both the value and the disabled state.
  // Currently, we don't support this.
  reset(
    value?: T,
    options?: {
      onlySelf?: boolean;
      emitEvent?: boolean;
    }
  ): void {
    super.reset(value, options);
  }
}
