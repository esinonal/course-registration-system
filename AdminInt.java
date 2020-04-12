import java.io.IOException;

public abstract interface AdminInt {

	// Course management
	public abstract void createNewCourse() throws IOException;
	
	public abstract void deleteCourse() throws IOException;
	
	public abstract void editCourse() throws IOException;
	
	public abstract void displayInfo() throws IOException;
	
	public abstract void registerStudent() throws IOException;
	
	// Reports
	public abstract void viewAllCourses();
	
	public abstract void viewFullCourses();
	
	public abstract void writeToFileCoursesThatAreFull() throws IOException;
	
	public abstract void viewStudentsInCourse() throws IOException ;
	
	public abstract void viewCoursesOfStudent() throws IOException ;
	
	public abstract void sortCourses();
}
