import { Injectable } from "@angular/core";
import { Resolve, ActivatedRouteSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { Project } from "src/app/shared/models/project/project.model";
import { ProjectService } from "../../service-REST/projects.service";

@Injectable({providedIn:'root'})
export class GetAllProjectsResolve implements Resolve<Array<Project>> {
  constructor(private projectService: ProjectService){}
  resolve(): Observable<Project[]>  {
    return this.projectService.getAllProjects();
  }
}

@Injectable({providedIn:'root'})
export class GetProjectByIDResolve implements Resolve<Project> {
  constructor(private projectService: ProjectService){}
  resolve(route: ActivatedRouteSnapshot): Observable<Project>{
    return this.projectService.getProject(route.paramMap.get('projectID'));
  }
}
