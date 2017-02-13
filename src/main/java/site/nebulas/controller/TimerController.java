package site.nebulas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TimerController {
	@RequestMapping("timer")
	public ModelAndView operationLog(){
		ModelAndView modelAndView = new ModelAndView("timer");
		return modelAndView;
	}
}
