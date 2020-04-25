import { UserRegistrationService } from '../../service/user-registration/user-registration.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: User = new User("", "");
  message: any;
  constructor(private service:UserRegistrationService) { }

  ngOnInit() {
  }

  public addUser() {
    let reg = this.service.insertUser(this.user);
    reg.subscribe((data)=>this.message = data);
  }

}
