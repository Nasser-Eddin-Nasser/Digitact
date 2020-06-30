/**
 * @description
 *  This file handles all the CRUD operations related to ionic storage
 */
import { Injectable } from '@angular/core';
import {
  createInstance,
  defineDriver,
  INDEXEDDB,
  LOCALSTORAGE,
} from 'localforage';
import { _driver } from 'localforage-cordovasqlitedriver';
import * as CordovaSQLiteDriver from 'localforage-cordovasqlitedriver';

@Injectable({
  providedIn: 'root',
})
export class StorageHandlerService {
  applicantDetailsDb: Promise<LocalForage>;
  applicantRatingsDb: Promise<LocalForage>;
  commonPropertiesDb: Promise<LocalForage>;

  constructor() {
    const commonPropertiesDbConfig = {
      name: 'digitactDb',
      storeName: 'commonProperties',
      driverOrder: ['sqlite', 'indexeddb', 'localstorage'],
    };

    this.commonPropertiesDb = new Promise((resolve, reject) => {
      let db: LocalForage;
      defineDriver(CordovaSQLiteDriver)
        .then(() => {
          db = createInstance(commonPropertiesDbConfig);
        })
        .then(() =>
          db.setDriver(
            this.getDriverOrder(commonPropertiesDbConfig.driverOrder)
          )
        )
        .then(() => {
          resolve(db);
        })
        .catch((reason) => reject(reason));
    });
    const applicantDetailsDbConfig = {
      name: 'digitactDb',
      storeName: 'applicantsDetails',
      driverOrder: ['sqlite', 'indexeddb', 'localstorage'],
    };

    this.applicantDetailsDb = new Promise((resolve, reject) => {
      let db: LocalForage;
      defineDriver(CordovaSQLiteDriver)
        .then(() => {
          db = createInstance(applicantDetailsDbConfig);
        })
        .then(() =>
          db.setDriver(
            this.getDriverOrder(applicantDetailsDbConfig.driverOrder)
          )
        )
        .then(() => {
          resolve(db);
        })
        .catch((reason) => reject(reason));
    });

    const applicantRatingsDbConfig = {
      name: 'digitactDb',
      storeName: 'applicantsRating',
      driverOrder: ['sqlite', 'indexeddb', 'localstorage'],
    };

    this.applicantRatingsDb = new Promise((resolve, reject) => {
      let db: LocalForage;
      defineDriver(CordovaSQLiteDriver)
        .then(() => {
          db = createInstance(applicantRatingsDbConfig);
        })
        .then(() =>
          db.setDriver(
            this.getDriverOrder(applicantRatingsDbConfig.driverOrder)
          )
        )
        .then(() => {
          resolve(db);
        })
        .catch((reason) => reject(reason));
    });
  }

  /**
   * Used to get the corresponding storage method keyword used by localforage
   * @param driveOrder - Holds the prioritised driveer order array
   * @returns array of strings with corresponding keyword supported by localforage
   */

  getDriverOrder(driverOrder: string[]): string[] {
    return driverOrder.map((driver) => {
      switch (driver) {
        case 'sqlite':
          return _driver;
        case 'indexeddb':
          return INDEXEDDB;
        case 'localstorage':
          return LOCALSTORAGE;
      }
    });
  }

  /**
   * Used to generate next Id
   * @returns Promise of string containing next id
   */

  async getNextId(): Promise<string> {
    return await new Promise((resolve, reject) => {
      this.commonPropertiesDb
        .then((localForageObject) => {
          localForageObject.getItem('recentApplicantId').then((val: number) => {
            const nextId: number = val ? val + 1 : 1;
            localForageObject.setItem('recentApplicantId', nextId);
            resolve(nextId.toString());
          });
        })
        .catch((reason) => reject(reason));
    });
  }

  /**
   * Used to create and and add an item in the storage
   * @param key Unique key for the item
   * @param value Conatains the FormsData field objects
   * @returns Promise of forms data object
   */
  async addItem<T>(
    dbObject: Promise<LocalForage>,
    key: string,
    value: T
  ): Promise<T> {
    return await new Promise((resolve, reject) => {
      dbObject
        .then((localForageObject) => {
          localForageObject.setItem(key, value).then((storedValue: T) => {
            resolve(storedValue);
          });
        })
        .catch((reason) => reject(reason));
    });
  }
  /**
   * Used to get an item from the storage
   * @param dbObject The storage object
   * @param key Unique key for the item
   * @returns Promise of forms data object
   */
  async getItem<T>(dbObject: Storage, key: string): Promise<T> {
    await dbObject.ready();
    const item = await dbObject.get(key);
    return item;
  }

  /**
   * Used to get all items from the storage
   * @param dbObject The storage object
   * @returns Promise of array of forms data object
   */
  async getAllItems<T>(dbObject: Storage): Promise<Array<T>> {
    const items: Array<T> = [];
    await dbObject.ready();
    await dbObject.forEach((value) => {
      items.push(value);
    });
    return items;
  }

  /**
   * Used to delete an item from the storage
   * @param dbObject The storage object
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
   * @param dbObject The storage object
   * @returns Promise of void
   */
  async deleteAllItems(dbObject: Storage): Promise<void> {
    await dbObject.ready();
    return dbObject.clear();
  }

  /**
   * Used to update an existing item in the storage
   * @param dbObject The storage object
   * @param key Unique key for the item
   * @param value Conatains the FormsData field objects to update
   * @returns Promise of forms data object
   */
  updateItem<T>(
    dbObject: Storage,
    key: string,
    value: DeepPartial<FormsData | RatingForm> | string
  ): Promise<T> {
    dbObject.ready();
    const item = dbObject.get(key);
    if (!item) {
      return undefined;
    }
    const data = dbObject.set(key, value);
    return data;
  }
}

type DeepPartial<T> = {
  [key in keyof T]?: DeepPartial<T[key]>;
};
