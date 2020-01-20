package com.corn.sso.security;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author Oleg Z. (cornknight@gmail.com)
 */
public class SecurityUtils {

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user
	 */
	public static Optional<String> getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication())
				.map(authentication -> {
					if (authentication.getPrincipal() instanceof UserDetails) {
						UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
						return springSecurityUser.getUsername();
					} else if (authentication.getPrincipal() instanceof String) {
						return (String) authentication.getPrincipal();
					}
					return null;
				});
	}

	/**
	 * Get the JWT of the current user.
	 *
	 * @return the JWT of the current user
	 */
	public static String getCurrentUserJWT() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication  authentication  = securityContext.getAuthentication();
		Object details = authentication.getDetails();
		if (details instanceof SimpleKeycloakAccount) {
			SimpleKeycloakAccount account = (SimpleKeycloakAccount) details;
			return account.getKeycloakSecurityContext().getToken().getPreferredUsername();
		} else
			return "unknown user";
	}

	/**
	 * If the current user has a specific authority (security role).
	 * <p>
	 * The name of this method comes from the isUserInRole() method in the Servlet API
	 *
	 * @param authority the authority to check
	 * @return true if the current user has the authority, false otherwise
	 */
	public static boolean isCurrentUserInRole(String authority) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication())
				.map(authentication -> authentication.getAuthorities().stream()
						.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))
				.orElse(false);
	}
}
