package site.nebulas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import site.nebulas.beans.Digest;
import site.nebulas.service.DigestService;
import javax.annotation.Resource;


@Controller
public class DigestController {
	@Resource
	DigestService digestService;
	
	@RequestMapping("digest")
	public ModelAndView digest(){
		return new ModelAndView("digest");
	}
	@RequestMapping("queryDigests")
	@ResponseBody
	public Object queryDigests(Digest digest){
		return digestService.queryByParam(digest);
	}
	@RequestMapping("insertDigests")
	@ResponseBody
	public Object insertDigests(Digest digest){
		digestService.insert(digest);
		return null;
	}
	@RequestMapping("updateDigests")
	@ResponseBody
	public Object updateDigests(Digest digest){
		digestService.update(digest);
		return null;
	}
}
