import { Component, OnInit } from '@angular/core';
import {DomainService} from '../service/domain.service';
import {Meme} from '../images/images.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-domain',
  templateUrl: './domain.component.html',
  styleUrls: ['./domain.component.css']
})
export class DomainComponent implements OnInit {

  domains: Domains[];
  memes: Meme[];
  currentDomain: string;
  searchDescription: string;
  constructor(private domainService: DomainService, private router: Router) { }

  ngOnInit() {
    this.getAllDomains();
  }

  getAllDomains() {
    this.domainService.getAllDomains().subscribe(
      data => { this.domains = data;
      },
      error1 => console.log(error1)
    );
  }

  refreshMemes() {
    console.log(this.currentDomain);
    this.domainService.getMemes(this.currentDomain).subscribe(
      data => {
        this.memes = data;
      },
      error1 => console.log(error1)
    );
  }

  navigateHome() {
    return this.router.navigate(['dashboard']);
  }

  searchByDescription() {
    this.domainService.searchDescription(this.searchDescription).subscribe(
      data => {
        this.memes = data;
      },
      error1 => console.log(error1)
    );
  }
}

export class  Domains {
  constructor(
    public id: number,
    public name: string,
    public address: string
  ) {}
}
