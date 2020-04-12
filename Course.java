import java.util.ArrayList;

public class Course implements Comparable<Course>, java.io.Serializable{

	protected String courseName;
	protected String courseID;
	protected int maxNumStu;
	protected int currentNumStu;
	protected ArrayList<Student> studentsInCourse;
	protected String instructor;
	protected int sectionNumber;
	protected String location;

	public Course(String name, String id, int maxstu, int currentstu, ArrayList<Student> stus, String ins, int section,
			String loc) {
		courseName = name;
		courseID = id;
		maxNumStu = maxstu;
		currentNumStu = currentstu;
		studentsInCourse = stus;
		instructor = ins;
		sectionNumber = section;
		location = loc;
	}

	public int compareTo(Course other) {
		if (this.currentNumStu > other.currentNumStu)
			return -1;
		else if (this.currentNumStu == other.currentNumStu)
			return 0;
		else
			return 1;
	}

	public boolean addStudent(Student s) {

		if (currentNumStu == maxNumStu) {
			System.out.println("Sorry, this class is full. You cannot add it. Wait for someone to withdraw.");
			return false;
		} else {
			studentsInCourse.add(s);
			return true;
		}
	}

	public void removeStudent(Student s) {
		studentsInCourse.remove(s);
	}

	// a method is necessary for Course! To update the students arraylist. So that
	// when a student adds a course, the students in course arraylist increases
	// can do this directly from the Register in course method from User. and call
	// itself. so that gets done automatically.

}
