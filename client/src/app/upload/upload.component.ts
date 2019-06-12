import { Component, OnInit } from '@angular/core';
import {ImageService} from '../service/image.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {
  description: string;
  file: File;
  constructor(private imageService: ImageService,  private router: Router) {
  }
  ngOnInit() {
  }
  catchFile(file) {
    this.file = file.files[0];
    console.log(file.toString());
  }

  doUpload() {
    this.imageService.saveImage(this.file, this.description).subscribe(
      res => this.router.navigate(['dashboard']),
        err => {
      console.log(err);
    });
  }
}
