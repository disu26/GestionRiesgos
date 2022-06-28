import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { EmptyProject } from '../models/empty-project';
import { Project } from '../models/project';
import { Risk } from '../models/risk';
import { FireserviceService } from '../service/fireservice.service';
import { ProjectService } from '../service/project.service';
import { faEye } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.css']
})
export class ProjectDetailComponent implements OnInit {
  userLogged = this.afAuth.getUserLogged();
  uid: any;
  disabled: boolean = false;

  faEye = faEye;

  projects: Project[] = [];
  project: Project | undefined;
  risks: Risk[] = [];
  user: any = '';

  constructor(
    private afAuth: FireserviceService,
    private projectService: ProjectService
  ) { }

  ngOnInit(): void {
    this.getProjects();
    this.traerdatos();
  }

  getProjects() {
    this.userLogged.subscribe(value => {
      this.uid = value?.uid;
    });
    this.projectService.getProjects().subscribe((data) => {
      this.projects = data;
    });
  }

  getProject(id: string) {
    console.log(id)
    this.projectService.getProject(id).subscribe((data) => {
      this.project = data;
      this.risks = data.risks;
    })
  }

  traerdatos() {
    this.userLogged.subscribe((value) => {     
      if (value?.email == undefined) {
        this.disabled = true;       
      } else {
        this.disabled = false;     
      }
    });
  }

}
