/**
 * @description
 *  This file handles all the CRUD operations related to ionic storage
 */
import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage';

import { FormsData } from '../model/forms-data.model';

@Injectable({
  providedIn: 'root',
})
export class StorageHandlerService {
  constructor(private storage: Storage) {}

  /**
   * Used to create and and add an item in the storage
   * @param key Unique key for the item
   * @param value Conatains the FormsData field objects
   * @returns Promise of forms data object
   */
  addItem(key: string, value: DeepPartial<FormsData>): Promise<FormsData> {
    return this.storage.ready().then(() => {
      return this.storage.set(key, value).then((item) => {
        return item;
      });
    });
  }

  /**
   * Used to get an item from the storage
   * @param key Unique key for the item
   * @returns Promise of forms data object
   */
  getItem(key: string): Promise<FormsData> {
    return this.storage.ready().then(() => {
      return this.storage.get(key).then((item) => {
        return item;
      });
    });
  }

  /**
   * Used to get all items from the storage
   * @returns Promise of array of forms data object
   */
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

  /**
   * Used to delete an item from the storage
   * @param key Unique key for the item
   * @returns Promise of void
   */
  deleteItem(key: string): Promise<void> {
    return this.storage.ready().then(() => {
      return this.storage.get(key).then((item) => {
        if (!item) {
          return undefined;
        }
        return this.storage.remove(key);
      });
    });
  }

  /**
   * Used to delete all items in the storage
   * @returns Promise of void
   */
  deleteAllItems(): Promise<void> {
    return this.storage.ready().then(() => {
      return this.storage.clear();
    });
  }

  /**
   * Used to update an existing item in the storage
   * @param key Unique key for the item
   * @param value Conatains the FormsData field objects to update
   * @returns Promise of forms data object
   */
  updateItem(key: string, value: DeepPartial<FormsData>): Promise<FormsData> {
    return this.storage.ready().then(() => {
      return this.storage.get(key).then((item) => {
        if (!item) {
          return undefined;
        }
        return this.storage.set(key, value).then((data) => {
          return data;
        });
      });
    });
  }
}

type DeepPartial<T> = {
  [key in keyof T]?: DeepPartial<T[key]>;
};
