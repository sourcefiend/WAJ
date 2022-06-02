import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-language-picker',
  templateUrl: './language-picker.component.html',
  styleUrls: ['./language-picker.component.css']
})
export class LanguagePickerComponent implements OnInit {

  currentLanguage!: string;

  constructor(
    private translateService: TranslateService,
  ) { }

  ngOnInit(): void {
  }

  languageChange(newLanguage: string) {
    switch(newLanguage){
      case 'hr':
        this.translateService.use('hr');
        this.currentLanguage = 'hr';
        break;
      case 'en':
        this.translateService.use('en');
        this.currentLanguage = 'en';
        break;
    }
  }

}
