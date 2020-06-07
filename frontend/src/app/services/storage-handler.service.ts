/**
 * @description
 *  This file handles all the CRUD operations related to ionic storage
 */
import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage';

import { FormsData } from '../model/forms-data.model';
import { RatingForm } from '../rating/model/rating-form.model';

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
  async addItem(
    dbObject: Storage,
    key: string,
    value: DeepPartial<FormsData> | DeepPartial<RatingForm>
  ): Promise<FormsData | RatingForm> {
    await dbObject.ready();
    const item = await dbObject.set(key, value);
    return item;
  }

  /**
   * Used to get an item from the storage
   * @param key Unique key for the item
   * @returns Promise of forms data object
   */
  // tslint:disable-next-line
  async getItem(dbObject: Storage, key: string): Promise<any> {
    await dbObject.ready();
    const item = await dbObject.get(key);
    return item;
  }

  /**
   * Used to get all items from the storage
   * @returns Promise of array of forms data object
   */
  async getAllItems(dbObject: Storage): Promise<FormsData[] | RatingForm[]> {
    const items: Array<FormsData> | Array<RatingForm> = [];
    await dbObject.ready();
    await dbObject.forEach((value) => {
      items.push(value);
    });
    return items;
  }

  /**
   * Used to delete an item from the storage
   * @param key Unique key for the item
   * @returns Promise of void
   */
  async deleteItem(dbObject: Storage, key: string): Promise<void> {
    await dbObject.ready();
    const item = await dbObject.get(key);
    if (!item) {
      return undefined;
    }
    return dbObject.remove(key);
  }

  /**
   * Used to delete all items in the storage
   * @returns Promise of void
   */
  async deleteAllItems(dbObject: Storage): Promise<void> {
    await dbObject.ready();
    return dbObject.clear();
  }

  /**
   * Used to update an existing item in the storage
   * @param key Unique key for the item
   * @param value Conatains the FormsData field objects to update
   * @returns Promise of forms data object
   */
  async updateItem(
    dbObject: Storage,
    key: string,
    value: DeepPartial<FormsData | RatingForm>
  ): Promise<FormsData | RatingForm> {
    await dbObject.ready();
    const item = await dbObject.get(key);
    if (!item) {
      return undefined;
    }
    const data = await dbObject.set(key, value);
    return data;
  }
}

type DeepPartial<T> = {
  [key in keyof T]?: DeepPartial<T[key]>;
};
