import { Injectable } from '@angular/core';

@Injectable()
export class AuthenticationService {
  constructor() {}

  isAuthenticated(): boolean {
    return true;
  }
}
