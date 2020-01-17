package com.corn.sso.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A simpler way to solve the problem is to declare a KeycloakSpringBootConfigResolver in a separate configuration class.
 * This option will fix issues with both Spring Boot and Spring Security.
 *
 * see https://stackoverflow.com/questions/57787768/issues-running-example-keycloak-spring-boot-app
 *
 * @author Oleg Z. (cornknight@gmail.com)
 */
@Configuration
public class KeycloakConfig {
	@Bean
	public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
}
