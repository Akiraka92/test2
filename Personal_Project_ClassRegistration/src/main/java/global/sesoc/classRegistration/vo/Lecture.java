package global.sesoc.classRegistration.vo;

public class Lecture {
	private int lectureId;
	private int credit;
	private String subject;
	private String major;
	
	public Lecture() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Lecture(int credit, String subject, String major) {
		super();
		this.credit = credit;
		this.setSubject(subject);
		this.major = major;
	}
	
	public Lecture(int lectureId, int credit, String subject, String major) {
		super();
		this.lectureId = lectureId;
		this.credit = credit;
		this.setSubject(subject);
		this.major = major;
	}

	public int getLectureId() {
		return lectureId;
	}

	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject.replaceAll("<", "&lt;");
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "lecture [lectureId=" + lectureId + ", credit=" + credit + ", subject=" + subject + ", major=" + major + "]";
	}
}
