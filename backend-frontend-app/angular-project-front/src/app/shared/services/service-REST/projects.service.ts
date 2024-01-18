import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Project } from '../../models/project/project.model';

@Injectable({ providedIn: 'root' })
export class ProjectService {
  constructor(private httpClient: HttpClient) {}

  dbServer: string = environment.gatewayService+'/clean-archi-bs-ms-project';

  getAllProjects(): Observable<Array<Project>> {
    return this.httpClient.get<Array<Project>>(this.dbServer + '/projects');
  }

  createProject(project: Project): Observable<Project> {
    return this.httpClient.post<Project>(this.dbServer + '/projects', project);
  }

  deleteProject(projectID: string): Observable<void> {
    return this.httpClient.delete<void>(
      this.dbServer + '/projects/' + projectID
    );
  }

  getProject(projectID: string|any): Observable<Project> {
    return this.httpClient.get<Project>(
      this.dbServer + '/projects/' + projectID
    );
  }

  updateProject(project: Project): Observable<Project> {
    return this.httpClient.put<Project>(
      this.dbServer + '/projects/' + project.projectID,
      project
    );
  }
}
