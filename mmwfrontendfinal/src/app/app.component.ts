import {AfterViewInit, Component, ElementRef} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'shop';
  // constructor(private elementRef: ElementRef) {}
  // ngAfterViewInit() {
  //   this.elementRef.nativeElement.ownerDocument
  //     .body.style.backgroundColor = '#1565c0';
  //     .body.style.backgroundImage = 'linear-gradient(#b481d9, #1565c0)';
  // }
}
