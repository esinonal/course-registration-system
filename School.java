import java.util.ArrayList;

public class School implements java.io.Serializable {
	
	static ArrayList<Course> courses;
	static ArrayList<Student> students = new ArrayList<Student>();
	
	protected static ArrayList<Course> getCourses() {
		return courses;
	}
	
	protected static ArrayList<Student> getStudents() {
		return students;
	}
	
	protected static void setCourses(ArrayList<Course> cours) {
		courses = cours;
	}
	
	protected static void setStudents(ArrayList<Student> studs) {
		students = studs;
	}
	
	protected School getSchool() {
		return this;
	}
}
