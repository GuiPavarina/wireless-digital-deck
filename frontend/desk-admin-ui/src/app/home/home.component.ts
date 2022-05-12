import { Component, OnInit } from '@angular/core';
import { UserInfo, UserService } from '../services/user.service';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ShorcutService, Shortcut } from '../services/shorcut.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  error: boolean = false;

  userinfo: UserInfo;

  shortcutForm: FormGroup = null;

  shortcuts: Shortcut[] = [];

  modifiers: any = [
    { name: 'Ctrl', shortcut: 'CONTROL'},
    { name: 'Alt', shortcut: 'ALT'},
    { name: 'Shift', shortcut: 'SHIFT'}
  ]

  constructor(
    private userService: UserService,
    private shortcutService: ShorcutService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    this.loadList();

    this.shortcutForm = this.formBuilder.group({
      key: ['', Validators.required],
      applicationName: ['', Validators.required],
      shortcutName: ['', Validators.required],
      order: ['', Validators.required],
      modifiers: this.formBuilder.array([
        
      ])
    });

    this.userService.getUserInfo().subscribe(userinfo => {
      this.userinfo = userinfo;
    })
  }

  get form() { return this.shortcutForm.controls; }

  loadList(): void {
    this.shortcutService.getAllShortcut().subscribe(list => {
      this.shortcuts = list;
    })
  }

  onCheckChange(e) {
    const modifiersForm: FormArray = this.shortcutForm.get('modifiers') as FormArray;

    if (e.target.checked) {
      modifiersForm.push(new FormControl(e.target.value));

    }  else {
      let i: number = 0;
  
      modifiersForm.controls.forEach((ctrl: FormControl) => {
        if(ctrl.value == e.target.value) {
          modifiersForm.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  remove(order: number) {
    this.shortcutService.removeShortcut(order).subscribe(res => {
      this.loadList()
    }, err => {
      console.log(err)
    })
  }

  resetForm() {
    this.shortcutForm.controls['key'].setValue('');
    this.shortcutForm.controls['applicationName'].setValue('')
    this.shortcutForm.controls['shortcutName'].setValue('')
    this.shortcutForm.controls['order'].setValue('')
  }

  onSubmit() {

    if(this.shortcutForm.invalid) {
      this.error = true;
      return;
    }
    this.error = false;

    this.shortcutService.addShortcut(
      new Shortcut(
        this.form['order'].value,
        this.form['modifiers'].value.join(','),
        this.form['key'].value,
        this.form['shortcutName'].value,
        this.form['applicationName'].value)
    ).subscribe(res => {
      this.loadList();
      this.resetForm();
    }, err => {
      console.log(err)
    })
  }

}
