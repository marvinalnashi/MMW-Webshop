import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {UserModel} from '../../models/user.model';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user: UserModel;

  constructor( private location: Location, private userService: UserService, private router: Router) {
    this.user = new UserModel();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.signUp(this.user).subscribe(u => {
      this.router.navigate(['/login']);
    });
  }
}
