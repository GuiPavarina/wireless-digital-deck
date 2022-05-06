import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: string = ""

  constructor(private http: HttpClient) { }

  ngOnInit(): void {

    this.http.get('/api/v1/userinfo').subscribe((res: any) => {
      console.log(res)
      this.user = res.username;
    }, (err) => {
      console.log(err)
    })

  }

}
