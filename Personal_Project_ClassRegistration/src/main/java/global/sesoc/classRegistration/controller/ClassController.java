package global.sesoc.classRegistration.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import global.sesoc.classRegistration.dao.LectureDAO;
import global.sesoc.classRegistration.vo.Application;
import global.sesoc.classRegistration.vo.Lecture;
import global.sesoc.classRegistration.vo.Regist;
import global.sesoc.classRegistration.vo.User;

@Controller
@RequestMapping("class")
public class ClassController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	LectureDAO lectureDAO;

	@RequestMapping(value = "getLectureList", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Lecture> getLectureList(HttpSession session) {
		ArrayList<Lecture> list = null;
		String major = ((User)session.getAttribute("user")).getMajor();
		list = lectureDAO.getLectureList(major);
		
		return list;
	}
	
	@RequestMapping(value= "addLecture", method = RequestMethod.POST)
	@ResponseBody
	public int addLecture(String subject, int credit, HttpSession session) {
		int result = 0;
		User user = (User)session.getAttribute("user");
		if(user.getJob().equals("professor")) {
			Lecture lecture = new Lecture();
			lecture.setSubject(subject);
			lecture.setCredit(credit);
			lecture.setMajor(user.getMajor());
			result = lectureDAO.addLecture(lecture);			
		}
		return result;
	}
	
	@RequestMapping(value = "addClass", method = RequestMethod.GET)
	public String addclass() {
		return "class/addClass";
	}
	
	
	@RequestMapping(value = "getLecture", method = RequestMethod.POST)
	@ResponseBody
	public Lecture getLecture(int lectureId, HttpSession session) {
		Lecture lecture = new Lecture();
		User user = (User)session.getAttribute("user");
		lecture.setMajor(user.getMajor());
		lecture.setLectureId(lectureId);
		if(user.getJob().equals("professor")||user.getJob().equals("executive")) {
			lecture = lectureDAO.getLecture(lecture);			
		}
		
		return lecture;
	}
	
	
	@RequestMapping(value = "insertClass", method = RequestMethod.POST)
	@ResponseBody
	public int insertClass(String schedule_day, int schedule_start, int quota, int lectureId, HttpSession session) {
		int result = 0;
		ArrayList<Regist> list = null;
		User user = (User)session.getAttribute("user");
		if(!user.getJob().equals("professor")) {
			return result;
		}
		Lecture lecture = new Lecture();
		lecture.setLectureId(lectureId);
		lecture.setMajor(user.getMajor());
		lecture = lectureDAO.getLecture(lecture);
		if(lecture == null) {
			return result;
		}
		int schedule_end = schedule_start - 1 + lecture.getCredit();
		Regist regist = new Regist();
		if(user.getJob().equals("professor")) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("job", user.getJob());
			map.put("id", user.getId());
			list = lectureDAO.myClass(map);
			for(Regist l : list) {
				if(l.getSchedule_day().equals(schedule_day)) {
					for(int i = l.getSchedule_start(); i <=l.getSchedule_end(); i++) {
						if(schedule_start==i||schedule_end==i) {
							logger.info("시간표 중복 ERROR");
							return result;
						}
					}
				}
			}
			
			
			regist.setSchedule_day(schedule_day);
			regist.setSchedule_start(schedule_start);
			regist.setSchedule_end(schedule_end);
			regist.setLecture_id(lectureId);
			regist.setQuota(quota);
			regist.setProfessor_id(user.getId());
			result = lectureDAO.insertClass(regist);
		}	
		return result;
	}
	
	
	@RequestMapping(value = "myClass", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Regist> myClass(HttpSession session) {
		ArrayList<Regist> list = null;
		User user = (User)session.getAttribute("user");
		
		String job = user.getJob();
		String id = user.getId();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("job", job);
		map.put("id", id);
		
		list = lectureDAO.myClass(map);
		/*
		if(user.getJob().equals("professor")) {
			list = lectureDAO.professorMy(user.getId());
		}
		else if(user.getJob().equals("student")) {
			list = lectureDAO.studentMy(user.getId());
		}
		*/
		return list;
	}
	
	
	@RequestMapping(value = "allClass", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> allClass(HttpSession session) {
		User user = (User)session.getAttribute("user");
		ArrayList<Regist> list = null;
		
		String job = user.getJob();
		String major = user.getMajor();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("job", job);
		map.put("major", major);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		list = lectureDAO.allClass(map);
		resultMap.put("user", user);
		resultMap.put("list", list);
		
		
		
		
		return resultMap;
	}
	
	
	@RequestMapping(value = "applyClass", method = RequestMethod.POST)
	@ResponseBody
	public int applyClass(int class_no, HttpSession session) {
		int result = 0;
		ArrayList<Regist> list = null;
		Regist regist = null;
		User user = (User)session.getAttribute("user");
		
		regist = lectureDAO.getRegist(class_no);
		if(!(regist.getLecture_major().equals(user.getMajor())||regist.getLecture_major().equals("교양"))) {
			logger.info("학적 오류");
			return result;
		}
		if(regist.getQuota()<=regist.getApplicant()) {
			logger.info("정원 초과");
			return result;
		}
		list = this.myClass(session);
		for(Regist r : list) {
			if(r.getSchedule_day().equals(regist.getSchedule_day())) {
				for(int i = r.getSchedule_start(); i <=r.getSchedule_end(); i++) {
					if(regist.getSchedule_start()==i||regist.getSchedule_end()==i) {
						logger.info("시간표 중복 ERROR");
						return result;
					}
				}
			}
		}
		Application application = new Application(class_no, user.getId());
		
		result = lectureDAO.applyClass(application);
		lectureDAO.classApplicant(class_no);
		logger.info("등록 : " + result);
		return result; 
	}
	
	
	@RequestMapping(value = "deleteClass", method = RequestMethod.POST)
	@ResponseBody
	public int deleteClass(int class_no, HttpSession session) {
		int result = 0;
		User user = (User)session.getAttribute("user");
		if(user.getJob().equals("professor")) {
			Application application = new Application(class_no, "drop_class");
			lectureDAO.deleteClassStudent(application);
			result = lectureDAO.deleteClassProfessor(class_no);
		}
		else if(user.getJob().equals("student")) {
			Application application = new Application(class_no, user.getId());
			result = lectureDAO.deleteClassStudent(application);
			lectureDAO.classApplicant(class_no);
			
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "searchClass", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Regist> searchClass(String searchSubject, HttpSession session) {
		ArrayList<Regist> list = null;
		String userJob = ((User)session.getAttribute("user")).getJob();
		if(userJob.equals("student")) {
			list = lectureDAO.searchClass(searchSubject);			
		}
		
		return list;
	}
	
}
