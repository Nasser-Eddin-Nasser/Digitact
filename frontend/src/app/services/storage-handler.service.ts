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
  applicantDetailsDb: Storage;
  apllicantRatingsDb: Storage;

  constructor() {
    this.applicantDetailsDb = new Storage({
      name: 'digitact',
      storeName: 'applicants-details',
      driverOrder: ['indexeddb', 'localstorage'],
    });
    this.apllicantRatingsDb = new Storage({
      name: 'digitact',
      storeName: 'applicants-rating',
      driverOrder: ['indexeddb', 'localstorage'],
    });
  }

  /**
   * Used to create and and add an item in the storage
   * @param key Unique key for the item
   * @param value Conatains the FormsData field objects
   * @returns Promise of forms data object
   */
  addItem(
    dbObject: Storage,
    key: string,
    value: DeepPartial<FormsData> | string
  ): Promise<FormsData> {
    return dbObject.ready().then(() => {
      return dbObject.set(key, value).then((item) => {
        return item;
      });
    });
  }

  /**
   * Used to get an item from the storage
   * @param key Unique key for the item
   * @returns Promise of forms data object
   */
  getItem(dbObject: Storage, key: string): Promise<FormsData> {
    return dbObject.ready().then(() => {
      return dbObject.get(key).then((item) => {
        return item;
      });
    });
  }

  /**
   * Used to get all items from the storage
   * @returns Promise of array of forms data object
   */
  getAllItems(dbObject: Storage): Promise<FormsData[]> {
    const items: Array<FormsData> = [];
    return dbObject.ready().then(() => {
      return dbObject
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
  deleteItem(dbObject: Storage, key: string): Promise<void> {
    return dbObject.ready().then(() => {
      return dbObject.get(key).then((item) => {
        if (!item) {
          return undefined;
        }
        return dbObject.remove(key);
      });
    });
  }

  /**
   * Used to delete all items in the storage
   * @returns Promise of void
   */
  deleteAllItems(dbObject: Storage): Promise<void> {
    return dbObject.ready().then(() => {
      return dbObject.clear();
    });
  }

  /**
   * Used to update an existing item in the storage
   * @param key Unique key for the item
   * @param value Conatains the FormsData field objects to update
   * @returns Promise of forms data object
   */
  updateItem(
    dbObject: Storage,
    key: string,
    value: DeepPartial<FormsData>
  ): Promise<FormsData> {
    return dbObject.ready().then(() => {
      return dbObject.get(key).then((item) => {
        if (!item) {
          return undefined;
        }
        return dbObject.set(key, value).then((data) => {
          return data;
        });
      });
    });
  }
}

type DeepPartial<T> = {
  [key in keyof T]?: DeepPartial<T[key]>;
};
