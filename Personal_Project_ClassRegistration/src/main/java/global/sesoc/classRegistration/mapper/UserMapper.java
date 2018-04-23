package global.sesoc.classRegistration.mapper;


import java.util.ArrayList;

import global.sesoc.classRegistration.vo.User;

public interface UserMapper {
	
	
	public int joinStudent(User student);
	public int joinProfessor(User professor);
	
	public User logInQuaryStudent(User user);
	public User logInQuaryProfessor(User user);
	
	public ArrayList<User> getProfessors(String major);
	
	
}
