import { ProjectState } from './project.state';
import { Priority } from "./project.priority";

export class Project {
  projectID!: string;
  projectName!: string;
  description!: string;
  priority!: Priority;
  projectState!: ProjectState;
  createdDate!: Date;
  employeeID!: string;
  companyID!: string;
}
