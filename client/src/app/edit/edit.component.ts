import { Component, OnInit } from '@angular/core';
import {ImageService} from '../service/image.service';
import {Meme} from '../images/images.component';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  id: number;
  meme: Meme;
  file: File;
  serverUrl: any;
  description: string;
  pictureUrl: any;
  constructor(private imageService: ImageService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params.id;
    this.meme = new Meme(0, '', '', null);
    this.imageService.findById(this.id).subscribe(
      data => {
        this.meme = data,
          this.description = data.description;
        this.pictureUrl = data.pictureUrl;
      }
      );
  }

  getUrl() {
    this.serverUrl = 'http://localhost:8080' + this.pictureUrl;
    console.log(this.serverUrl);
  }

  catchFile(file) {
    this.file = file.files[0];
    console.log(file.toString());
  }

  editMeme(id) {
    this.imageService.editMeme(id, this.file, this.description).subscribe(
      data => {
         return this.router.navigate(['dashboard']);
      },
      err => console.log(err)
    );
  }

}
