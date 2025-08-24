package com.tvf.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

@SpringBootApplication
public class SpringBootAwsSecretsManagerExampleApplication implements CommandLineRunner {
    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;
    // Autowire the Environment object itself so we can inspect it.
    @Autowired
    private ConfigurableEnvironment environment;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAwsSecretsManagerExampleApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- AWS Secrets ---");
        System.out.println("Username successfully loaded: " + user);
        System.out.println("Password successfully loaded: " + password);
        System.out.println("-------------------------------");

        // --- DEBUG CODE STARTS HERE ---
        System.out.println("\n--- PROPERTY SOURCE DEBUGGING ---");
        // Check both the new and old property names.
        findPropertySource("Username");
        findPropertySource("password");
        System.out.println("-------------------------------\n");
    }

    /**
     * This helper method will find and print the source of a given property.
     *
     * @param propertyName the name of the property to find.
     */
    private void findPropertySource(String propertyName) {
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            if (propertySource.containsProperty(propertyName)) {
                System.out.println("Found property '" + propertyName + "' in source: " + propertySource.getName());
                System.out.println("    --> with value: " + propertySource.getProperty(propertyName));
                return; // Stop after finding the highest-priority source.
            }
        }
        System.out.println("Property '" + propertyName + "' was NOT FOUND in any source.");
    }
}
