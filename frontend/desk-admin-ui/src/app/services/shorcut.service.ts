import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShorcutService {

  constructor(
    private http: HttpClient
  ) { }

  getAllShortcut(): Observable<Shortcut[]> {
    return this.http.get<Shortcut[]>('/api/v1/shortcuts');
  }

  addShortcut(shortcut: Shortcut): Observable<any> {
    return this.http.post<any>('/api/v1/shortcut', shortcut);
  }

  removeShortcut(order: number): Observable<any> {
    return this.http.delete<any>('/api/v1/shortcut', {
      params: {
        order: order
      }
    })
  }
  
}

export class Shortcut {
  constructor(
    public order: number,
    public modifiers: string,
    public key: string,
    public shortcutName: string,
    public applicationName: string
  ) {

  }
}
