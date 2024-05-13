/**
 * 
 */
package com.brijframework.docs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author omnie
 */
@RestController
@RequestMapping("/api/")
public class ApiController {

	@GetMapping
	public String hello() {
		return "Hi";
	}
}
