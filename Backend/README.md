# Development

The following tools were used for development.
- Java Development kit(software development environment used for developing Java applications and applets): Recommended to use JDK 11+ 
- IDE: Eclipse or IntelliJ 
- Java Framework: Springboot
- Gradle
- Postman: Generate JSON requests (For testing purposes)
- PostgreSQL: Data storage

# Java Development Kit(JDK)
It is recommended to use JDK 11+ version.
Download and [install JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
> Important: Make sure to [set up the environmental variables](https://www.java.com/en/download/help/path.xml)

# IDE: Eclipse or IntelliJ
Install either one listed below
- [Eclipse](https://www.eclipse.org/downloads/packages/release/luna/sr2/eclipse-ide-java-developers): Eclipse IDE for Java Developers 
- [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows): Community, Education or ultimate editions 

# Java Framework: Spring boot
Download the project framework from [Spring Initializer](https://start.spring.io/) : Initializr offers a fast way to pull in all the dependencies you need for an application and does a lot of the setup.

# Gradle
[Install gradle](https://gradle.org/install/): Gradle is a build automation tool

# Postman
[Install Postman](https://www.postman.com/downloads/): Postman is a popular API client that makes it easy for developers to create, share, test and document APIs

# PostgreSQL
[Download](https://www.postgresql.org/download/) and install postgre.

# How to use

- Install the below in-order
  - JDK and set environment variables
  - Install IDE and [set the JDK path](https://www.jetbrains.com/help/idea/sdk.html) if need. 
  - Install Gradle
  - Install PostgreSQL and [set up a simple database](https://www.postgresqltutorial.com/)
  - Install Postman 
- Clone and pull the code
- Apply neccesary settingscin the application.properties file
  - Open the file in Digitact/Backend/src/main/resources/application.properties 
      ```
      server.port=<Specify a port>
      spring.jpa.database=POSTGRESQL
      spring.datasource.platform=postgres
      spring.datasource.url=jdbc:postgresql://localhost:5432/<Database Name>
      spring.datasource.username=<Username of the database>
      spring.datasource.password=<Password of the database>
      spring.jpa.show-sql=true
      spring.jpa.generate-ddl=true
      spring.jpa.hibernate.ddl-auto=<Options: create, create-drop, validate, and update>
      spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
     ```
- Run the Digitact/Backend/Application.java file
- Execute the below commands in the postman
  - Post request
    ```
    http://localhost:<port specified>/api/controller/createapplicant
    ```
    In the body:
    ```
    {
    "firstName":"Lionel",
    "lastName":"Messi"
    }
    ```
  - Get request
    ```
    http://localhost:9090/api/controller/getusers
    ```
    ```
    [
      {
        "firstName": "Lionel",
        "lastName": "Messi"
      }
    ]
    ```


