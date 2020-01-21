package com.corn.sso.rest;

import com.corn.sso.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Oleg Z. (cornknight@gmail.com)
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class TestController {

	private static final String ROLE_USER1 = "'ROLE_USER1'";
	private static final String ROLE_USER2 = "'ROLE_USER2'";
	private static List<String> roles   = new ArrayList<>();

	static {
		roles.add("ROLE_USER1");
		roles.add("ROLE_USER2");
	}

	@GetMapping(value = "/user1", produces = "application/text")
	@PreAuthorize("hasAnyRole("+ROLE_USER1+")")
	public ResponseEntity<String> user1Endpoint() {
		return getCredentials();
	}

	@GetMapping(value = "/user2", produces = "application/text")
	@PreAuthorize("hasAnyRole("+ROLE_USER2+")")
	public ResponseEntity<String> user2Endpoint() {
		return getCredentials();
	}

	@GetMapping(value = "/nonauth", produces = "application/text")
	public ResponseEntity<String> nonauth() {
		System.out.println("got here");
		return ResponseEntity.of(Optional.of("got nonauth response"));
	}

	private ResponseEntity<String> getCredentials() {
		String role = "unknown role";

		for (String r: roles) {
			if (SecurityUtils.isCurrentUserInRole(r)) {
				role = r.substring(5);
				break;
			}
		}

		String user = SecurityUtils.getCurrentUserJWT();

		return ResponseEntity.of(Optional.of("Backend role: "+role+", backend user: " + user));
	}
}
