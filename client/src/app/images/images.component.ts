import { Component, OnInit } from '@angular/core';
import DateTimeFormat = Intl.DateTimeFormat;
import {ImageService} from '../service/image.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {
  serverUrl: any;
  memes: any;
  selectedSize = 3;
  searchDescription: string;
  dataArray = [];
  constructor(private imageService: ImageService,  private router: Router) { }

  ngOnInit() {
    this.refreshImages();
  }

  refreshImages() {
    this.imageService.getAllMemes().subscribe(
      result => this.memes = result,
      error1 => console.log(error1)
    );
  }
  getUrl(currUrl) {
   this.serverUrl =  'http://localhost:8080' + currUrl;
  }
  deleteMeme(id) {
    this.imageService.deleteMeme(id).subscribe(
      data => {console.log(data),
      this.refreshImages();
      },
      error1 => console.log(error1)
    );
  }

  editMeme(id) {
    return this.router.navigate(['edit', id]);
  }

  createMeme() {
    return this.router.navigate(['upload']);
  }

  searchByDescription(searchedDescription) {
    console.log(searchedDescription);
    this.imageService.findByDescription(searchedDescription).subscribe(
      data  => {
        console.log(searchedDescription),
          this.memes = data;
      },
      error1 => console.log(error1)
    );
  }

  explore() {
    return this.router.navigate(['explore']);
  }

}

export class Meme {
  constructor(
     public id: number,
     public pictureUrl: string,
     public description: string,
     public datePosted: DateTimeFormat
  ) {}
}
