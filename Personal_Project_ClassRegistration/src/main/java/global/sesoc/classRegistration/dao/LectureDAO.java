package global.sesoc.classRegistration.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.classRegistration.mapper.LectureMapper;
import global.sesoc.classRegistration.vo.Application;
import global.sesoc.classRegistration.vo.Lecture;
import global.sesoc.classRegistration.vo.Regist;


@Repository
public class LectureDAO {

	@Autowired
	SqlSession sqlSession;
	
	
	public ArrayList<Lecture> getLectureList(String major) {
		ArrayList<Lecture> list = new ArrayList<Lecture>();
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			list = mapper.getLectureList(major);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public int addLecture(Lecture lecture) {
		int result = 0;
		try{
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			result = mapper.addLecture(lecture);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public Lecture getLecture(Lecture lecture) {
		Lecture l = null;
		try{
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			l = mapper.getLecture(lecture);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	
	public int insertClass(Regist regist) {
		int result = 0;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			result = mapper.insertClass(regist);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	public ArrayList<Regist> professorMy(String id) {
		ArrayList<Regist> list = null;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			list = mapper.professorMy(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Regist> studentMy(String id) {
		ArrayList<Regist> list = null;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			list = mapper.studentMy(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	*/
	
	public ArrayList<Regist> myClass(HashMap<String, String> map) {
		ArrayList<Regist> list = null;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			list = mapper.myClass(map);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<Regist> allClass(HashMap<String, String> map) {
		ArrayList<Regist> list =null;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			list = mapper.allClass(map);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int applyClass(Application application) {
		int result = 0;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			result = mapper.applyClass(application);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int classApplicant(int class_no) {
		int result = 0;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			result = mapper.classApplicant(class_no);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public Regist getRegist(int class_no) {
		Regist regist = null;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			regist = mapper.getRegist(class_no);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return regist;
	}
	
	
	public int deleteClassStudent(Application application) {
		int result = 0;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			result = mapper.deleteClassStudent(application);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteClassProfessor(int class_no) {
		int result = 0;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			result = mapper.deleteClassProfessor(class_no);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Regist> searchClass(String searchSubject) {
		ArrayList<Regist> list = null;
		try {
			LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);
			list = mapper.searchClass(searchSubject);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
