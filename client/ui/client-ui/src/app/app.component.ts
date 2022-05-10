import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { HashService, Shortcut } from './hash.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  hashForm: FormGroup;
  hashes: Shortcut[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private hashService: HashService
  ) {

  }

  ngOnInit(): void {
    this.hashForm = this.formBuilder.group({
      hash: new FormControl("")
    })
  }

  get form() { return this.hashForm.controls; }

  search() {
    this.hashService.getAllShortcuts(this.form['hash'].value) 
      .subscribe(hashes => {
        console.log(hashes);
        this.hashes = hashes;
      }, err => {
        console.log(err)
      })
  }
  
  execute(id: number) {
    this.hashService.execute(id).subscribe(res => {
      console.log(res)
    }, err => {
      console.log(err)
    })
  }
}
