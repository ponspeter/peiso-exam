# Peiso Exam

### Description
This project is a sample project for Peiso Exam. 
It connect to Kounta API using basic authentication. 
It fetch the records from Kounta API and save it to local database.

> Technologies & Frameworks
* Frontend 
    * VueJS
        * [*vue-resource: ^1.5.1*](https://github.com/pagekit/vue-resource)
        * [*vue-router: ^3.0.2*](https://router.vuejs.org/)
        * [*vuelidate: ^0.7.4*](https://github.com/vuelidate/vuelidate)
* Backend
    * Spring Boot: 2.6.6
    * Java 17
        * *actuator*
        * *data-jpa*
        * *web*
        * *swagger*
        * *spring-feign*
        * *lombok*
    * MySQL


> REST Routes

**Backend:**

| Name           | Path                       | HTTP Verb | Purpose                                     	         |
|----------------|----------------------------|-----------|-------------------------------------------------------|
| Authorize      | /api/kounta/authorize      | POST      | Use to fetch the access token in Kounta               |
| Refresh Token  | /api/kounta/refresh-token  | POST      | Refresh the token in order to connect again in Kounta |
| Get Products   | /api/kounta/products       | GET       | Get products from Kounta                              |
| Get Staff      | /api/kounta/staff          | GET       | Get Staff from Kounta    	                            |
| Get All Orders | /api/kounta/orders         | GET       | Get Orders from Kounta                         	      |

* After run backend server, you can see backend routes on [**Swagger UI**](http://localhost:8082/swagger-ui.html)

**Frontend:**

| Name | Path            | HTTP Verb | Purpose                                     	 |
|------|-----------------|-----------|-----------------------------------------------|
| TBD  | /               | GET       | ---                               	           |


### Installation

1. After cloning process, in project directory run `mvn clean install`, it will install all necessary dependencies.
4. To run only backend, run `mvn --projects backend spring-boot:run`
5. Check the swagger ui on [**Swagger UI**](http://localhost:8082/swagger-ui.html)
6. [OPTIONAL] To run Vue CLI, run `npm run serve` command in frontend directory. This will work on `localhost:8080`
