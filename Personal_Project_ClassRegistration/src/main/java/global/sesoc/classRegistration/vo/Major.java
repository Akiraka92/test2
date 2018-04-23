package global.sesoc.classRegistration.vo;

public class Major {
	private String major;
	private String building;
	
	public Major() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Major(String major, String building) {
		super();
		this.setMajor(major);
		this.setBuilding(building);
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major.replaceAll("<", "&lt;");
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building.replaceAll("<", "&lt;");
	}
	
}
