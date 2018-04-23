package global.sesoc.classRegistration.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.classRegistration.mapper.MajorMapper;
import global.sesoc.classRegistration.mapper.UserMapper;
import global.sesoc.classRegistration.vo.User;

@Repository
public class UserDAO {
	
	@Autowired
	SqlSession sqlSession;

	
	
	// 로그인
	public User logInQuaryStudent(User user) {
		User student = null;
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			student = mapper.logInQuaryStudent(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	public User logInQuaryProfessor(User user) {
		User professor = null;
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			professor = mapper.logInQuaryProfessor(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return professor;
	}
	
	public int joinStudent(User student) {
		int result = 0;
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			result = mapper.joinStudent(student);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int joinProfessor(User professor) {
		int result = 0;
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			result = mapper.joinProfessor(professor);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<User> getProfessors(String major) {
		ArrayList<User> list = null;
		try{
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			list = mapper.getProfessors(major);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	

}
