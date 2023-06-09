# Peiso Exam

### Description
This is a mini project for Peiso Exam. 
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
        * *mysql-connector-java*
        * *redis*
    * MySQL
    * Redis


> REST Routes

**Backend:**

| Name                       | Path                                                                  | HTTP Verb | Purpose                                     	                                                       |
|----------------------------|-----------------------------------------------------------------------|-----------|-----------------------------------------------------------------------------------------------------|
| Authorize                  | /api/kounta/authorize                                                 | POST      | Use to fetch the access token in Kounta                                                             |
| Refresh Token              | /api/kounta/refresh-token                                             | POST      | Refresh the token in order to connect again in Kounta                                               |
| Get All Products           | /api/kounta/products/db                                               | GET       | Get all products from db                                                                            |
| Fetch Products from Kounta | /api/kounta/products/kounta                                           | GET       | Fetch products from kounta and save into db    	                                                    |
| Get All Orders             | /api/kounta/orders/db                                                 | GET       | Get all orders from db                	                                                             |
| Get Complete Orders        | /api/kounta/orders/kounta?startDate='YYYY-MM-DD'&endDate='YYYY-MM-DD' | GET       | Get Complete Orders from Kounta by date range save it database                	 |

* After run backend server, check [**Swagger UI**](http://localhost:8082/swagger-ui.html) for easy access on endpoints

### How to use
1. For Authorization, use the following request in the request body
```json
{
  "code":"a6ff0fcf4635d3f0caed66bf924e1be3e71aaae4",
  "client_id":"jwEFtDj4ztUBqHtP",
  "client_secret":"0d0Sb7PSmjJsz01ayjfADo8lJGgi81ZWGOTJ8Owj",
  "redirect_uri":"http://your-redirection.uri",
  "grant_type":"authorization_code"
}
```
#### Note:
In order to get code, you need to login to Kounta and authorize the app.
You can use the following link to authorize the app.
```
https://my.kounta.com/authorize?client_id=jwEFtDj4ztUBqHtP&redirect_uri=http://your-redirection.uri&state=a1b2c3d4&response_type=code
```

It will return the code in the redirection uri. You can use that code to get the access token.
```
Sample : 
http://your-redirection.uri/?code=d10f93d08cb08cb016f0db98977663835652b321&state=a1b2c3d4
```

2. Once you get the access token, you can use it to get the products, staff and orders.
   'Authorization' header is required in order to get the data from Kounta API.
```
Sample :
Bearer 1a2b3c4d5e6f7g8h9i0j
```

3. If token is expired, use refresh token to get the new access token.
```json
{
  "refresh_token":"a6ff0fcf4635d3f0caed66bf924e1be3e71aaae4",
  "client_id":"jwEFtDj4ztUBqHtP",
  "client_secret":"0d0Sb7PSmjJsz01ayjfADo8lJGgi81ZWGOTJ8Owj",
  "grant_type":"refresh_token"
}
```

4. To insert the data into database, use the following endpoints
```
To add products in database, use the following endpoint
http://localhost:8082/api/kounta/products/kounta  (SWAGGER) getAllProductsFromKounta

To add orders in the database, use the following endpoint
http://localhost:8082/api/kounta/orders/kounta?startDate='YYYY-MM-DD'&endDate='YYYY-MM-DD' (SWAGGER) getCompleteOrdersFromKounta

```
**Frontend:**

| Name             | Path     | HTTP Verb | Purpose                                     	            |
|------------------|----------|-----------|----------------------------------------------------------|
| GET ALL PRODUCTS | /product | GET       | display products from db                               	 |
| GET ALL ORDERS   | /order   | GET       | display orders from db                               	   |


### Installation

1. After cloning process, in project directory run `mvn clean install`, it will install all necessary dependencies.
2. create a database named `peiso`
3. Change the database username and password in `application.properties` file
```
spring.datasource.username=your-username
spring.datasource.password=your-password
```

```
4. To run only backend, run `mvn --projects backend spring-boot:run`
5. Check the swagger ui on [**Swagger UI**](http://localhost:8082/swagger-ui.html)
6. [OPTIONAL] To run Vue CLI, run `npm run serve` command in frontend directory. This will work on `localhost:8080`