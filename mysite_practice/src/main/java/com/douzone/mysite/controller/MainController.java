package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping({"","/main"})
	public String index() {
		System.out.println("asdasdasdasd");
		return "main/index";
	}

}