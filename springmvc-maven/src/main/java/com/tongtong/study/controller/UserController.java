package com.tongtong.study.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import com.tongtong.study.common.UserException;
import com.tongtong.study.model.User;


@Controller
@RequestMapping("/user")
public class UserController {
	private Map<String, User> users = new HashMap<String, User>();
	
	public UserController(){
		users.put("zhangsan", new User("张三", "男", "1234567890", "zhangsan", "123456"));
		users.put("lisi", new User("李四", "男", "1234567890", "lisi", "123456"));
		users.put("ahu", new User("阿花", "女", "1234567890", "ahu", "123456"));
	}
	
	/**
	 * 
	 * 获取用户列表信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", users);
		return "user/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model) {
		// 开启modelDriven
		model.addAttribute(new User());
		return "user/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@Validated User user, BindingResult br, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws IOException {
		if(br.hasErrors()){
			return "user/add";
		}
		
		String realpath = request.getSession().getServletContext().getRealPath("/resources/upload");
		System.out.println(realpath);
		for (MultipartFile file : files) {
			if(file.isEmpty()) continue;
			File f = new File(realpath+"/"+file.getOriginalFilename());
			FileUtils.copyInputStreamToFile(file.getInputStream(), f);
		}
		
		users.put(user.getName(), user);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value="/{account}", method=RequestMethod.GET)
	public String show(@PathVariable String account, Model model) {
		model.addAttribute(users.get(account));
		return "user/show";
	}
	
	@RequestMapping(value="/{account}", method=RequestMethod.GET, params="json")
	@ResponseBody
	public User show(@PathVariable String account) {
		return users.get(account);
	}
	
	@RequestMapping(value="/{account}/update", method=RequestMethod.GET)
	public String update(@PathVariable String account, Model model) {
		model.addAttribute(users.get(account));
		return "user/update";
	}
	
	@RequestMapping(value="/{account}/update", method=RequestMethod.POST)
	public String update(@PathVariable String account, @Validated User user, BindingResult br) {
		if(br.hasErrors()){
			return "user/update";
		}
		users.put(account, user);
		return "redirect:/user/users";
	}
	
	/**
	 * 删除用户
	 * @param name 用户名称
	 * @return
	 */
	@RequestMapping(value="/{account}/delete", method=RequestMethod.GET)
	public String delete(@PathVariable String account) {
		users.remove(account);
		return "redirect:/user/users";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String account, String password, HttpSession session) {
		if(!users.containsKey(account)){
			throw new UserException("用户名不存在！");
		}
		
		User user = users.get(account);
		if(!user.getPassword().equals(password)){
			throw new UserException("用户密码不正确！");
		}
		
		session.setAttribute("loginUser", user);
		return "redirect:/user/users";
	}
	
}
