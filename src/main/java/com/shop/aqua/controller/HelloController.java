package com.shop.aqua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

//	@RequestMapping("/")
//	public @ResponseBody String root() throws Exception {
//		return "Hello World";
//	}
//
	@RequestMapping("/test1")	//localhost:9090/test1
	public String test1() {
		return "test1";	// 실제 호출 밑/WEB-INF/views/test1.jsp
		
	}
	
	
//	@GetMapping("/test1")
//	public String test1(Model model,
//		@RequestParam(value ="name" , required = false) String name){
//			model.addAttribute("greeting", "안녕하세요," +name);
//			return "test1";
//		}
//	
//	
	
}
