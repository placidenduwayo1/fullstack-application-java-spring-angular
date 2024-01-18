#Full stack application
## Description

- Application de gestion des employées, des projets associés à ces employées et ces projets sont crées pour des sociétés.
- La partie back: architecture orientée microservices,le pattern clean-architecture et le front: Angular, le pattern RxJS
- Containeurisation de chaque service de l'application avec docker et docker-compose

### Backend:
- framework spring boot
- microservices-base application
	- ***clean-archi-business-service-address***
	- ***clean-archi-business-service-employee***
	- ***clean-archi-business-service-project***
	- ***clean-archi-business-service-company***
	
- communication entre microservices (1); gestion des scénarios alternatifs et la résilience (2)
	- (1) spring cloud openfeign
	- (2) spring cloud circuit breaker (Resilience4J)

- design pattern dev pour chaque microservice métier: clean architecture
- service utilitaire pour la centralisation et la gestion des configurations des services métiers et utilitaires: ***microservices-config-service***
- service de passerelle entre le backend (spring-project-back) et le frontend (angular-project-front): ***backend-frontend-gateway-service***
     
- service d'enregistrement des microservices métiers et la gateway dans un annuaire: ***microservices-registration-service***
     
- Images docker des services de l'application
- Déploiement de la pile des images de l'application dans des containers docker avec un docker-compose file: [docker-compose](https://github.com/placidenduwayo1/fullstack-application-springboot-angular-deployment.git)
   

### Frontend:

- framework angular
- pattern observer RxJS

### Architecture globale: 
[architecture](https://drive.google.com/file/d/1cl-0_Iv-YiYb8pXeH6rUK0WdogEmwGNl/view?usp=share_link)
