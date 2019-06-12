import {Component, OnInit} from '@angular/core';

export class CanvasElement {
  constructor(
    public source: string,
    public isText: boolean,
    public width: number,
    public height: number
  ) {}
}

@Component({
  selector: 'app-canvas',
  templateUrl: './canvas.component.html',
  styleUrls: ['./canvas.component.css']
})
export class CanvasComponent implements OnInit {

  description: string;
  file: File;
  public message: string;
  canvas: HTMLCanvasElement;
  context: CanvasRenderingContext2D;
  elements = [];
  rows: number;
  cols: number;
  widthOfElement;
  heightOfElement;
  fontSize = 200;
  i = [];
  j = [];
  // font = this.fontSize + 'px Arial';
  elementIteratorCount;

  constructor() {
  }

  ngOnInit() {
    this.canvas = document.getElementById('canvas') as HTMLCanvasElement;
    this.context = this.canvas.getContext('2d') as CanvasRenderingContext2D;
  }

  paintCanvas() {
    this.elementIteratorCount = 0;
    console.log(this.elements);
    for (let i = 0; i < this.rows; i++) {
      for (let j = 0; j < this.cols; j++) {
        console.log(this.elementIteratorCount);
        const elementToInsert = this.elements[this.elementIteratorCount];
        if (!elementToInsert) {
          this.elementIteratorCount++;
          continue;
        } else if (!elementToInsert.isText) {
          const image = this.createImage(elementToInsert.source);
          console.log(image);
          this.context.drawImage(image, j * this.widthOfElement, i * this.heightOfElement, this.widthOfElement, this.heightOfElement);
        } else {
          this.insertTextIntoCanvas(elementToInsert.source, j, i);
        }
        this.elementIteratorCount++;
      }
    }
  }

  createImage(imgUrl) {
    const image = new Image();
    image.src = imgUrl;
    return image;
  }

  insertText(text) {
    const textElement = new CanvasElement(text, true, this.widthOfElement, this.heightOfElement);
    console.log(text);
    this.elements.push(textElement);
  }

  insertTextIntoCanvas(text, j, i) {
      const lines = text.split('\n');
      // const lines = text;
      const lineHeight = this.fontSize + 5; // if you change the context font size you should change this line
      console.log(this.fontSize);
      for (let h = 0; h < lines.length; h++) {
        if (j === 0) {
          this.context.strokeText(lines[h], 0.001 * this.widthOfElement, i * this.heightOfElement +
            this.heightOfElement * 0.2 + (h * lineHeight));
        } else {
          this.context.strokeText(lines[h], j * this.widthOfElement, i * this.heightOfElement +
            this.heightOfElement * 0.2 + (h * lineHeight));
        }
      }
  }

  preview(files) {
    const reader = new FileReader();
    reader.readAsDataURL(files);
    // tslint:disable-next-line:variable-name
    reader.onload = (_event) => {
      const imgURL = reader.result;
      console.log('from preview ' + imgURL);
      const picture = new CanvasElement(imgURL.toString(), false, this.widthOfElement, this.heightOfElement);
      this.elements.push(picture);
    };
  }

  catchFile(file) {
    this.file = file.files[0];
    this.preview(this.file);
  }

  updateArrays() {
  this.i = new Array(this.rows);
  console.log(this.i.length);
  this.j = new Array(this.cols);
  console.log(this.j.length);
  this.widthOfElement = 600 / this.cols;
  this.heightOfElement = 512 / this.rows;
  }

}


