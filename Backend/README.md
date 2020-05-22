# Development

The following tools were used for development.
- JDK 11+ (Java Development Kit 11+)
- IDE: Eclipse or IntelliJ 
- Java Framework: Springboot
- Postman: Generate JSON requests (for testing purposes)
- PostgreSQL: Data storage
- pgAdmin4: Admin console for PostgreSQL
- Docker: Contains image of postgresql and pgAdmin4

# Java Development Kit(JDK)
It is recommended to use JDK 11+.
Download and [install JDK](https://www.oracle.com/java/technologies/javase-downloads.html).

> Important: Make sure to [set up the environment variables](https://www.java.com/en/download/help/path.xml).

# IDE: Eclipse or IntelliJ
We recommend using one of the following IDEs:
- [Eclipse](https://www.eclipse.org/downloads/packages/release/luna/sr2/eclipse-ide-java-developers): Eclipse IDE for Java Developers 
- [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows): Community, Education or Ultimate edition. Important: Use version 2020.1 or later!

# Postman (optional)
[Postman](https://www.postman.com/downloads/) is a tool to create GET/POST/... requests. We recommend using it in order to debug the API.

# Docker

We use Docker to run our development database.
- [Download](https://docs.docker.com/docker-for-windows/install/) for Windows 
- [Download](https://docs.docker.com/docker-for-mac/install/) for Mac

# How to use

- Install the below in-order
  - JDK 11+ and set environment variables
  - Install IDE and [set the JDK path](https://www.jetbrains.com/help/idea/sdk.html) if need. 
  - Install Docker
- Clone and pull the code
- Run `docker-compose up`
- Run the Digitact/Backend/Application.java file using your preferred IDE

# Example
Execute the following commands in Postman to verify your server is working correctly.

## POST request

Send a POST request to the following URL:
```
http://localhost:9090/api/controller/createApplicant
```
Example body of the request (important: send it as JSON)
``` json
{
  "firstName":"Lionel",
  "lastName":"Messi"
}
```

## GET request

After you have added at least one applicant using a POST request, you can retrieve them from the following URL:

```
http://localhost:9090/api/controller/getusers
```


The response should look like this:
``` json
[
  {
    "firstName": "Lionel",
    "lastName": "Messi"
  }
]
```

# pgAdmin4

In order to view the content of the database, we ship pgAdmin4 with our docker-compose file. Open `localhost:8080` to open pgAdmin4. If you want to access the database, you are most likely prompted for a password. It is set to `postgres` by default.

