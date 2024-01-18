import { Router } from '@angular/router';
import { ProjectEventPublisher } from '../../../../shared/events-publisher/events.publisher';
import { Component, Input, OnInit } from '@angular/core';
import { Project } from 'src/app/shared/models/project/project.model';
import { ProjectEvent } from 'src/app/shared/models/events.model';
import { ProjectService } from 'src/app/shared/services/service-REST/projects.service';

@Component({
  selector: 'app-sub-compo-project-printer',
  templateUrl: './sub-compo-project-printer.component.html',
  styleUrls: ['./sub-compo-project-printer.component.scss']
})
export class SubCompoProjectPrinterComponent implements OnInit {

  constructor(private projectService: ProjectService,
    private projectEventPublisher: ProjectEventPublisher,
    private router: Router) { }

  @Input () projects!: Array<Project>;

  ngOnInit(): void {
    this.projectEventPublisher.projectEventObservable.subscribe((projectEvent: ProjectEvent)=>{
      switch(projectEvent){
        case ProjectEvent.UPDATE_PROJECT_FORM:
          this.router.navigateByUrl('/project-update/'+this.projectID);
          break;
      }
    })
  }

projectID!: string;
onProjectUpdate(project: Project){
  this.projectID = project.projectID;
  this.projectEventPublisher.publishProjectEvent(ProjectEvent.UPDATE_PROJECT_FORM);
}
onProjectDelete(projectID: string){
  if(window.confirm('COMFIRM PROJECT DELETE')){
    this.projectService.deleteProject(projectID).subscribe(
      ()=>{
        this.projectEventPublisher.publishProjectEvent(ProjectEvent.REFRESH);
      }
    );
  }
}

}
