import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Role} from '../../enum/Role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isInvalid: boolean;
  isLoggedOut: boolean;
  isSubmitted = false;
  model: any = {
    username: '',
    password: ''
  };
  returnUrl = '/';

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit() {
    const paramMap = this.route.snapshot.queryParamMap;
    this.isLoggedOut = paramMap.has('logout');
    this.returnUrl = paramMap.get('returnUrl');
  }

  onSubmit() {
    this.isSubmitted = true;
    this.userService.login(this.model).subscribe(
      user => {
        if (user) {
          if (user.role !== Role.Customer) {
            this.returnUrl = '/seller';
          }
          this.router.navigateByUrl(this.returnUrl);
        } else {
          this.isLoggedOut = false;
          this.isInvalid = true;
        }
      }
    );
  }
}
