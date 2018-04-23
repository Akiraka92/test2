package global.sesoc.classRegistration.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import global.sesoc.classRegistration.dao.MajorDAO;
import global.sesoc.classRegistration.vo.Major;
import global.sesoc.classRegistration.vo.User;

@Controller
public class MajorController {

	private static final Logger logger = LoggerFactory.getLogger(MajorController.class); 
	
	@Autowired
	MajorDAO majorDAO;
	
	
	@RequestMapping(value = "executive/majorMenu", method = RequestMethod.GET)
	public String majorMenu(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user.getMajor().equals("executive")) {			
			return "executive/majorMenu";
		}
		return "/";
	}
	
	
	// 전공 목록 불러오기
	@RequestMapping(value = "/major/getMajors", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<String> getMajors() {
		ArrayList<String> list = majorDAO.getMajors(); 
		
		for(String str : list) {
			logger.info(str);
		}
		
		return list;
	}
	
	
	
	
	@RequestMapping(value = "/executive/addMajor", method = RequestMethod.POST)
	@ResponseBody
	public int addMajor(Major major) {
		int result = 0;
		
		result = majorDAO.addMajor(major);
		logger.info("결과 : " + result);
		return result;
	}
	
}
