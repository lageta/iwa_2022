
# IWA_2022

Spring Boot application that allows to connect
seasonal job seekers and employers (restaurants, bars, campgrounds, ...).

Back repository 

## Architecure

Iwa_2022

```
┌── .gitlab-ci.yml
├── .gitignore
├── docker-compose.yml
├── init.sql
├── README.md
├── affect
├── auth
├── gateway
├── offers
├── recommendation
└── users
	├── gradle
	├── src
	├── Dockerfile
	└── ...
```


## Getting Started

This project works with JDK 17, Gradle and Spring Boot 2.7.5.

It uses docker (recommended: 20.10.20) and postgres 15.0 database.

The project was created with Ubuntu 22.04, thus we recommend using this version for installing everything.

- Install Docker
- `docker compose up --build` at the top-level of the repo, it will automatically build the app, his microservices, the postgres database etc...


## CI

There is Gitlab CI wich consist in three stages :
 - build : For all branches this stage build all the docker images and place them in the gitlab container registry depending on the branch name and the microservice image name.
 - test : For all branches, actually this stage is not used because the test are run within de dockerfiles during the build using `gradle check`.
 - deploy : Only for the branches production and recette this stage actually take one microservice from the container registry ad push it to Heroku. Thus the deployment is not complete due to the incompatibility of realeasing the entire app on Heroku using free tier. We are looking for others plateforms.

## Env var 

You need to use the following env var. Place a `.env` file at the root of the project next to the `docker-compose.yml` file.

`SPRING_DATASOURCE_URL`=`jdbc:postgresql://iwa_2022-db-1:5432/postgres`

`SPRING_DATASOURCE_USERNAME`=`postgres`

`SPRING_DATASOURCE_PASSWORD`=`postgres`

`SPRING_DATASOURCE_DRIVER_CLASS_NAME`=`org.postgresql.Driver`

`SPRING_DATASOURCE_JDBCURL`=` ${spring.datasource.url}`

`SERVER_PORT`= `6060`

`SPRING_SECURITY_NAME`= ...

`SPRING_SECURITY_PASSWORD`= ...

`SPRING_SECURITY_ROLES`=`admin`

`SPRING_MAIL_HOST`=`smtp.gmail.com`

`SPRING_MAIL_PORT`=`465`

`MAIL_USERNAME`= ...

`MAIL_PASSWORD`= ...

`SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH`=`true`

`SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE`=`true`

For `MAIL_USERNAME` and `MAIL_PASSWORD` use the Google documentation to register an application to a gmail messa

## Authors

Axel LAGET
Cem Sarisoy
