# Spring Boot & AWS Secrets Manager Example

A concise example demonstrating how to securely load secrets from **AWS Secrets Manager** into a Spring Boot application.
This repository is designed as a reference and a starting point for developers looking to externalize their application's sensitive configuration, such as database credentials, API keys, and other secrets, instead of hardcoding them.
---

## Quick Setup Guide

This project uses Spring Cloud AWS to treat secrets in AWS as if they were local configuration properties. Here’s the short version of how it works.

### 1. Key Dependencies (`pom.xml`)

The integration is handled by one primary dependency. The versions are managed by a Bill of Materials (BOM) to ensure compatibility.

-   **`spring-cloud-starter-aws-secrets-manager-config`**: This is the key dependency that enables the auto-configuration.
-   **`spring-cloud-aws-dependencies`**: This BOM is added to your `<dependencyManagement>` section to control the versions of all AWS libraries.

```xml
<!-- Manages versions for all AWS dependencies -->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.awspring.cloud</groupId>
            <artifactId>spring-cloud-aws-dependencies</artifactId>
            <version>3.1.1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<!-- The actual dependency that enables the feature -->
<dependencies>
    <dependency>
        <groupId>io.awspring.cloud</groupId>
        <artifactId>spring-cloud-starter-aws-secrets-manager-config</artifactId>
    </dependency>
</dependencies>

 ```

### 2. Configuration (application.properties)

You only need to add two properties to your configuration file to connect to AWS Secrets Manager.

-   **`spring.config.import`**: This tells Spring Boot to fetch configuration from AWS Secrets Manager on startup. The value should be the name of your secret.
-   **`spring.cloud.aws.region.static`**: This sets the AWS region where your secret is stored.


### 3. Injecting Secrets (Java Code)
   Once the secrets are loaded, you can inject them directly into your Spring components using the @Value annotation, just like any other property.

```xml

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    // Spring injects the 'username' key from your secret here
    @Value("${username}")
    private String dbUser;

    // Spring injects the 'password' key from your secret here
    @Value("${password}")
    private String dbPassword;

    // You can now use dbUser and dbPassword in your code
}