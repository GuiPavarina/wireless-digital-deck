import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HashService {

  constructor(
    private http: HttpClient
  ) { }

  getAllShortcuts(hash: string): Observable<Shortcut[]> {
    return this.http.get<Shortcut[]>(`${environment.serverUrl}/login/${hash}`);
  }

  execute(id: number): Observable<any> {
    return this.http.get<any>(`${environment.serverUrl}/shortcut/${id}`);
  }
}

export class Shortcut {
  constructor(
    public id: number,
    public app: string,
    public name: string
  ) {

  }
}