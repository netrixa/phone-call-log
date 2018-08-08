package com.ecobank.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	@RequestMapping("/login")
	public String home() {

		return "admin/uploads";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard() {

		return "admin/index2";
	}
}