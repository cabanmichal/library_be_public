# Library backend

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
  * Book detail
    * id 
    * authorFirstName
    * authorLastName
    * title
    * isbn
    * count (number of a given title in library)
    * categoryIds (One book can be listed in multiple categories)
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
package sk.umb.example.library.category.controller;

public class CategoryController {
}
```

**Book**

```java 
package sk.umb.example.library.book.controller;

public class BookController {
}
```

**Borrowing**

```java 
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