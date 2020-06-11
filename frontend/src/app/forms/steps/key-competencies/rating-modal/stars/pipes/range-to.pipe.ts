import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'rangeTo',
})
export class RangeToPipe implements PipeTransform {
  /**
   * This **pure** Pipe creates an array containing numbers from `start` to `end`.
   *
   * Example usage:
   *
   * ```
   * <ng-container *ngFor="n of 1 | rangeTo 3">
   *  {{n}},
   * </ng-container>
   * ```
   *
   * This will output:
   * ```
   * 1,2,3,
   * ```
   */
  transform(start: number, end: number): number[] {
    const result: number[] = [];
    for (let i = start; i <= end; i++) {
      result.push(i);
    }

    return result;
  }
}
