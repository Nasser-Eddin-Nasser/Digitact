# HR Monitor

A desktop application built for HR members to monitor and review the student applications received through digitact during the job fair. 

## Development

The following tools were used for development.
- JDK 1.8  (Java Development Kit 1.8)
- IDE: Eclipse or IntelliJ
- Postman: Generate JSON requests (for testing purposes)
- PostgreSQL: Data storage
-  SceneBuilder

## Java Development Kit(JDK)
It is recommended to use JDK 1.8 as it has JavaFX builtin. 
Download and [install JDK](https://www.oracle.com/java/technologies/javase-downloads.html).

> Important: Make sure to [set up the environment variables](https://www.java.com/en/download/help/path.xml).

## IDE: Eclipse or IntelliJ
We recommend using one of the following IDEs:
- [Eclipse](https://www.eclipse.org/downloads/packages/release/luna/sr2/eclipse-ide-java-developers): Eclipse IDE for Java Developers 
- [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows): Community, Education or Ultimate edition. Important: Use version 2020.1 or later!

## SceneBuilder (optional)
[SceneBuilder](https://gluonhq.com/products/scene-builder/) is a visual layout tool that lets users quickly design JavaFX application user interfaces without coding.

## Postman (optional)
[Postman](https://www.postman.com/downloads/) is a tool to create GET/POST/... requests. We recommend using it in order to debug the API.

## HTTPS Setup
We are using HTTPS for connection so its important to import and trust the certificate to run the application

Export certificate
- Open terminal and go to Digitact directory and run the follwing command
```
keytool -export -keystore Backend/src/main/resources/keystore.p12 -alias tomcat -file myCertificate.crt
```
- myCertificate.crt file will be generated 

Import myCertificate.crt into jdk
```
keytool -importcert -file myCertificate.crt -alias tomcat -keystore $JDK_HOME/jre/lib/security/cacerts
```
- Password is "changeit"
- $JDK_HOME - path where jdk is installed in your local machine

## How to use

- Install the below in-order
- JDK 1.8 and set environment variables
- Install IDE and [set the JDK path](https://www.jetbrains.com/help/idea/sdk.html) if needed
- Clone this project
- [Start the server](https://github.com/Nasser-Eddin-Nasser/Digitact/blob/master/Backend/README.md)
- set configurations in HR-Monitor/Main/Configuration.java (absoluteFileSystemPath should be set different than in server)
- Do HTTPS setup
- Run the HR-Monitor/Main/App.java file using your preferred IDE

## Test connection
The following command could be executed to verify the connection with server 

```
https://localhost:9090/api/HRController/gutenMorgen
```
This will return a long number which means application is up and running successfully.
