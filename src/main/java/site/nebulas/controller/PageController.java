package site.nebulas.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import site.nebulas.service.UserService;



@Controller
public class PageController {
	@Resource
	UserService userService;
	
	@RequestMapping("login")
	public ModelAndView login(){
		ModelAndView model = new ModelAndView("login");
		//System.out.println(userService.findByUserAccount("admin").getUserAccount());
		return model;
	}
	@RequestMapping("home")
	public ModelAndView home(){
		ModelAndView model = new ModelAndView("home");
		return model;
	}
}
