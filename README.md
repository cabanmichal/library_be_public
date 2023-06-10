# Library backend

## Week 3
### Objective
* Docker and docker compose introduction
  * Explain docker and docker usecase 
  * Install docker on local machine
  * Test docker installation
    * > ```docker run hello-world```
  * Install docker-compose on local machine
* Introduce database IDE
  * Install DBeaver
  * Connect DBeaver to docker database instance. 

![DBeaver Postgres](images/dbeaver-postgres.png)
  
![DBeaver Config](images/dbeaver-config.png)

### Prerequisites
* None

### Homework
* Install docker and docker-compose on a local machine

#### Homework acceptance criteria
* docker and docker compose is running on a local machine
* Postgres DB is running in Docker using docker-compose
* DBeaver can connect to Docker Postgress instance

## Week 2

### Objective
* Basic Maven explanation.
  * Maven repository concept. Local and remote.
  * Basic build example with dependencies download.
  * mvn clean install
  * mvn spring-boot:run
  * Maven relation to IntelliJ IDE
* Explain application layering.
* Spring:
  * Introduce @Service annotation.
  * Explain Autowiring.
* Introduce and explain Swagger.

### Prerequisites
* Working WEEK1 homework code

### Homework
####  Using Customer service as an example, implement business logic (service) for following functionalities:
**Category**

```java 
package sk.umb.example.library.category.service;

public class CategoryService {
}
```

**Book**

```java 
package sk.umb.example.library.book.service;

public class BookService {
}
```

**Borrowing**

```java 
package sk.umb.example.library.borrowing.service;

public class BorrowingService {
}
```

#### For each functionality implement:
* Service backing methods for each of the Controller's API method.
* *DetailDto - placed in a service package.
* *RequestDto - placed in a service package.
* DTO properties: (If not mentioned, the request class does not contain an 'id' attribute)
  * Book Status (Java Enum)
    * AVAILABLE
    * NOT_AVAILABLE
    * TEMPORARY_NOT_AVAILABLE
  * Book detail
    * id 
    * author
    * title
    * isbn
    * count (number of a given title in library)
    * categoryIds (One book can be listed in multiple categories)
    * status (Book Status Enum)
  * Category detail
    * id
    * name
  * Borrowing detail
    * id
    * BookDetailDto
    * CustomerDetailDto
    * date (represent date of borrowing)
  * Borrowing request
    * bookId
    * customerId

#### Other
* Enable swaggers

#### Homework acceptance criteria
* For each service exists corresponding feature branch.
* Each feature branch is merged into main branch. (Use pull request for merging. Not mandatory however.).
* Application compiles and starts properly.
* Swagger is accessible.
* All services are implemented and functional using REST Api calls.

**Branching hint** 

Since borrowing detail partially depends on the implementation of Category, Book and Customer there are two options for dedicated borrowing feature branch.
  * Wait until the implementation of all dependencies are present in main branch, then fork the borrowing feature branch from the main.
  * Fork borrowing feature branch at the begining of the implementation, implement borrowing request (requestDto is independent of Customer and Book implementations), wait until the Customer and Book implementation are merged into the main branch, then backmerge the main branch into the borrowing feature branch.

## Week 1

### Objective
* Implement minimal Spring REST application
* Spring:
  * Describe Spring Framework
  * Describe Maven
  * Describe REST Api
  * Implement Customer Controller CRUD methods
  * update .gitignore
* Tools:
  * Introduce Postman.
    * Install Postman
    * Create Postman account
    * Demo Postman - Spring interaction

### Prerequisites
* Knowledge of Git and GitHub
* Installed software
  * Java 17
  * Maven

### Notes
* Put emphasis on code organization into packages.
* Put emphasis on naming convention

**Customer example:**

```java 
package sk.umb.example.library.customer.controller;

public class CustomerController {
}
```

### Homework
####  Using Customer controller as an example, implement controllers for following functionalities:

**Category**

```java 
// url: /api/categories

package sk.umb.example.library.category.controller;

public class CategoryController {
}
```

**Book**

```java 
// url: /api/books

package sk.umb.example.library.book.controller;

public class BookController {
}
```

**Borrowing**

```java 
// url: /api/borrowings

package sk.umb.example.library.borrowing.controller;

public class BorrowingController {
}
```

#### For each functionality implement methods

* List resource   / GET
* Retrieve detail / GET
* Create resource / POST 
* Update resource / PUT
* Delete resource / DELETE

#### Homework acceptance criteria

* Controller with correct name exist and is placed in corresponding package.
* For each controller exists corresponding feature branch.
* Each feature branch is merged into main branch. (Use pull request for merging. Not mandatory however.).
* Application starts without exceptions.
* Application logs (SYSOUT) request for each of the above REST API endpoints.
