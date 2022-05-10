import { Component, OnInit } from '@angular/core';
import { UserInfo, UserService } from '../services/user.service';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  userinfo: UserInfo;

  shortcutForm: FormGroup = null;

  modifiers: any = [
    { name: 'Ctrl', shortcut: 'CONTROL'},
    { name: 'Alt', shortcut: 'ALT'}
  ]

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    // TODO
    // Load list of shorcuts at the start

    this.shortcutForm = this.formBuilder.group({
      key: ['', Validators.required],
      applicationName: [''],
      modifiers: this.formBuilder.array([
        this.formBuilder.control('')
      ])
    });

    this.userService.getUserInfo().subscribe(userinfo => {
      this.userinfo = userinfo;
    })
  }

  onCheckChange(e) {
    const modifiersForm: FormArray = this.shortcutForm.get('modifiers') as FormArray;

    if (e.target.checked) {
      modifiersForm.push(new FormControl(e.target));

    }  else {
      let i: number = 0;
  
      modifiersForm.controls.forEach((ctrl: FormControl) => {
        if(ctrl.value.shortcut == e.target.shortcut) {
          modifiersForm.removeAt(i);
          return;
        }
        i++;
      });
    }

  }

  onSubmit() {
    console.log('submit')
  }

}
