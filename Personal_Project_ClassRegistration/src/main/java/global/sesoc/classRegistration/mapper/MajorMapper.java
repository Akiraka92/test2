package global.sesoc.classRegistration.mapper;

import java.util.ArrayList;

import global.sesoc.classRegistration.vo.Major;
import global.sesoc.classRegistration.vo.User;

public interface MajorMapper {
	
	public ArrayList<String> getMajors();
	public int addMajor(Major major);
	
	
}
