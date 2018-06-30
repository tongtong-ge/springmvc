package com.tongtong.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	String message = "Welcome to Spring MVC!";
	
	@RequestMapping("/hello")
	public ModelAndView Hello(@RequestParam(value = "name", required = false, defaultValue = "Spring") String name) {
		// 指定视图
		ModelAndView modelAndView = new ModelAndView("hello");
		
		// 向视图中添加所要展示或使用的内容，将在页面中使用
		modelAndView.addObject("message", message);
		modelAndView.addObject("name", name);
		return modelAndView;
	}
}
