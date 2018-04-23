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
import global.sesoc.classRegistration.dao.UserDAO;
import global.sesoc.classRegistration.vo.User;

@Controller
@RequestMapping("user")
public class UserController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	MajorDAO majorDAO;
	
	// 가입 폼
	@RequestMapping(value = "signUp", method = RequestMethod.GET)
	public String signUp() {
		return "/user/signUpForm";
	}
	
	// 로그인 폼
	@RequestMapping(value = "logIn", method = RequestMethod.GET)
	public String logIn() {
		return "/user/logInForm";
	}
		
	
	
	// ID 중복 체크
	@RequestMapping(value = "idCheck", method = RequestMethod.POST)
	@ResponseBody
	public int checkID(String id) {
		// test
		// if(id.equals("abc123")) return 1;
		
		User user = new User(id, "#signUp");
		
		User u = userDAO.logInQuaryProfessor(user);
		
		if(u == null) {
			u = userDAO.logInQuaryStudent(user);
			if(u == null) {
				return 0;
			}
		}
		
		return 1;
	}
	
	
	// 학부 교수 불러오기
	@RequestMapping(value = "getProfessors", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<User> getProfessors(String major) {
		ArrayList<User> list = userDAO.getProfessors(major);
		
		return list;
	}
	

	
	// 가입 등록
	@RequestMapping(value = "signUp", method = RequestMethod.POST)
	@ResponseBody
	public int signUp(String id, String pw, String name, String major, String job, String tutor, int grade) {
		int result = 0;
		User user;
		if(pw.equals("#signUp")) {
			result = 0;
		}
		else if(job.equals("student")) {
			if(grade>5||grade<1) {
				logger.info("error 학년");
				result = 0;
			}
			else if(major.equals("교양")) {
				result = 0;
			}
			else {
				user = new User(id, name, pw, major, tutor, grade);
				result = userDAO.joinStudent(user);
			}
		}
		else if(job.equals("professor")) {
			user = new User(id, name, pw, major);
			result = userDAO.joinProfessor(user);
		}
		else {
			logger.info("error 직업구분");
			result = 0;
		}
		logger.info(" 회원가입 결과 : " + result);
		return result;
	}
	
	
	
	//로그인
	@RequestMapping(value = "logIn", method = RequestMethod.POST)
	@ResponseBody
	public User logIn(String id, String pw, HttpSession session) {
		User result = null;
		User user = new User(id, pw);
		String job = null;
		if(!pw.equals("#signUp")) {
			result = userDAO.logInQuaryProfessor(user);
			job = "professor";
			if(result == null) {
				result = userDAO.logInQuaryStudent(user);
				job = "student";
			}
		}
		
		if(result!=null) {
			result.setJob(job);
			session.setAttribute("user", result);
			
			
		}
		else {
			result = new User();
		}
		logger.info("" + result);
		return result;
	}
	
	
	@RequestMapping(value = "logOut", method = RequestMethod.GET)
	public String logOut(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
	
	
	// 세션 사용자 정보 반환
	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(HttpSession session) {
		User user = (User)session.getAttribute("user");
		return user;
	}
	
	/*
	*   컨트롤러 내부 함수
	*/
	
	
	
	
}
