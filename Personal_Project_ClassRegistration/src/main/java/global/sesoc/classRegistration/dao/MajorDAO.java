package global.sesoc.classRegistration.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.classRegistration.mapper.MajorMapper;
import global.sesoc.classRegistration.mapper.UserMapper;
import global.sesoc.classRegistration.vo.Major;
import global.sesoc.classRegistration.vo.User;

@Repository
public class MajorDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	
	public ArrayList<String> getMajors() {
		ArrayList<String> list = null;
		try{
			MajorMapper mapper = sqlSession.getMapper(MajorMapper.class);
			list = mapper.getMajors();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public int addMajor(Major major) {
		int result = 0;
		try {
			MajorMapper mapper = sqlSession.getMapper(MajorMapper.class);
			result = mapper.addMajor(major);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

}
