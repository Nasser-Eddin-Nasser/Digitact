import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage';

import { FormsData } from '../model/forms-data.model';

@Injectable({
  providedIn: 'root',
})
export class StorageHandlerService {
  constructor(private storage: Storage) {}

  addItem(key: string, value: DeepPartial<FormsData>): Promise<FormsData> {
    return this.storage.ready().then(() => {
      return this.storage.set(key, value).then((item) => {
        return item;
      });
    });
  }

  getItem(key: string): Promise<undefined | FormsData> {
    return this.storage.ready().then(() => {
      return this.storage.get(key).then((item) => {
        if (!item) {
          return undefined;
        }
        return item;
      });
    });
  }

  getAllItems(): Promise<FormsData[]> {
    const items: Array<FormsData> = [];
    return this.storage.ready().then(() => {
      return this.storage
        .forEach((value) => {
          items.push(value);
        })
        .then(() => {
          return items;
        });
    });
  }
  deleteItem(key: string): Promise<undefined | FormsData> {
    return this.storage.ready().then(() => {
      return this.storage.get(key).then((item) => {
        if (item) {
          return this.storage.remove(key).then((item) => {
            return item;
          });
        }
        return undefined;
      });
    });
  }

  deleteAllItems(): Promise<void | FormsData> {
    return this.storage.ready().then(() => {
      return this.storage.clear().then((item) => {
        return item;
      });
    });
  }

  updateItem(
    key: string,
    value: DeepPartial<FormsData>
  ): Promise<undefined | FormsData> {
    return this.storage.ready().then(() => {
      return this.storage.get(key).then((item) => {
        if (!item) {
          return undefined;
        }
        return this.storage.set(key, value).then((item) => {
          return item;
        });
      });
    });
  }
}

type DeepPartial<T> = {
  [key in keyof T]?: DeepPartial<T[key]>;
};
