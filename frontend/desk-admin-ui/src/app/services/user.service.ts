import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  getUserInfo(): Observable<UserInfo> {
    return this.http.get<UserInfo>('/api/v1/userinfo');    
  }

}

export class UserInfo {

  constructor(
    public username: string,
    public hash: string
  ) {
    
  }

}

