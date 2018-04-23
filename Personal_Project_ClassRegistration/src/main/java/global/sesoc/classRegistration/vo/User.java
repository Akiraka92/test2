package global.sesoc.classRegistration.vo;

public class User {
	
	private String id;
	private String name;
	private String password;
	private String major;
	private String job;
	private String tutor;
	private int grade;
	
	public User() {
		super();
	}
	
	public User(String id, String name, String password, String major, String job, String tutor, int grade) {
		super();
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
		this.setMajor(major);
		this.setJob(job);
		this.setTutor(tutor);
		this.setGrade(grade);
	}
	
	public User(String id, String name, String password, String major, String tutor, int grade) {
		super();
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
		this.setMajor(major);
		this.setTutor(tutor);
		this.setGrade(grade);
	}
	
	public User(String id, String password) {
		super();
		this.setId(id);
		this.setPassword(password);
	}
	
	public User(String id, String name, String password, String major) {
		super();
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
		this.setMajor(major);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id.replaceAll("<", "&lt;");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.replaceAll("<", "&lt;");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password.replaceAll("<", "&lt;");
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getTutor() {
		return tutor;
	}
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", major=" + major + ", job=" + job
				+ ", tutor=" + tutor + ", grade=" + grade + "]";
	}
	
}
