package com.corn.sso.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Oleg Z. (cornknight@gmail.com)
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class TestController {

	@GetMapping(value = "/test", produces = "application/text")
	public ResponseEntity<String> test() {
		return ResponseEntity.of(Optional.of("ok"));
	}
}
