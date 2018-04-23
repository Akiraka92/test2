package global.sesoc.classRegistration.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import global.sesoc.classRegistration.vo.Application;
import global.sesoc.classRegistration.vo.Lecture;
import global.sesoc.classRegistration.vo.Regist;

public interface LectureMapper {

	public ArrayList<Lecture> getLectureList(String major);
	public int addLecture(Lecture lecture);
	public Lecture getLecture(Lecture lecture);
	public int insertClass(Regist regist);
//	public ArrayList<Regist> professorMy(String id);
//	public ArrayList<Regist> studentMy(String id);
	public ArrayList<Regist> allClass(HashMap<String, String> map);
	public int applyClass(Application application);
	public int classApplicant(int class_no);
	public Regist getRegist(int class_no);
	public ArrayList<Regist> myClass(HashMap<String, String> map);
	public int deleteClassStudent(Application application);
	public int deleteClassProfessor(int class_no);
	public ArrayList<Regist> searchClass(String searchSubject);
}
