import java.io.IOException;

public abstract interface StudentInt {
	
	// Course management
	public abstract void viewAllCourses();
	
	public abstract void viewCoursesNotFull();
	
	public abstract void registerInCourse() throws IOException;
	
	public abstract void withdrawFromCourse() throws IOException;
	
	public abstract void viewCurrentCourses();
}
