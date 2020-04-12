import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements StudentInt {

	String idNumber;
	ArrayList<Course> enrolledCourses = new ArrayList<Course>();
	// Each student should also have an arraylist of courses
	// But for each student it's different because each student's enrolled courses
	// are different (not static)

	Student() {
	}
	

	
	// Course management

	public void viewAllCourses() {
		System.out.println("These are the courses offered at the university: ");

		for (int i = 0; i < School.courses.size(); i++) {
			System.out.println();
			System.out.println(School.courses.get(i).courseName);
			System.out.println(School.courses.get(i).courseID);
			System.out.println(School.courses.get(i).instructor);
		}
	}

	public void viewCoursesNotFull() {
		System.out.println("These are the courses that are not full:");
		for (int i = 0; i < School.courses.size(); i++) {
			if (School.courses.get(i).maxNumStu != School.courses.get(i).currentNumStu) {
				System.out.println();
				System.out.println(School.courses.get(i).courseName);
				System.out.println(School.courses.get(i).courseID);
				System.out.println(School.courses.get(i).instructor);
				System.out.println("Max num stu: " + School.courses.get(i).maxNumStu);
				System.out.println("Current num stu: " + School.courses.get(i).currentNumStu);
			}
		}
	}

	public void registerInCourse() throws IOException {
		System.out.println("Enter name of course you would like to register in:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String course = br.readLine();

		System.out.println("Enter number of section you would like to register in:");
		Scanner scan = new Scanner(System.in);

		int sectionNum = scan.nextInt(); // If the scanner is an int value we can directly take the value into the
											// scanner and then move on.

		int k = -1;
		for (int i = 0; i < School.courses.size(); i++) {
			if (School.courses.get(i).courseName.equals(course)) {
				if (sectionNum == School.courses.get(i).sectionNumber) {
					k = i;
				}
			}
		}

		if (k == -1) { // the course they want to register in was not found.
			System.out.println("Sorry, the course you were looking for was not found.");
	
		} else if (School.courses.get(k).currentNumStu == School.courses.get(k).maxNumStu) {
			System.out.println("Sorry, you cannot enroll in this course. It is full.");
		
		} else { // the index of the course they want to register in is k.

			// School.courses.get(k).studentsInCourse.add(this); //Update the school
			// directory. Courses arraylist gets an additional student in that one course.
			boolean ableToEnroll = School.courses.get(k).addStudent(this);

			if (ableToEnroll) {
				School.courses.get(k).currentNumStu++;
				this.enrolledCourses.add(School.courses.get(k));
				System.out.println("Success. You have been registered in the course.");
			}
		}
	}

	public void withdrawFromCourse() throws IOException {

		System.out.println("Enter name of course you would like to withdraw from:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String course = br.readLine();

		int k = -1;

		for (int i = 0; i < this.enrolledCourses.size(); i++) {
			if (this.enrolledCourses.get(i).courseName.equals(course)) {
				k = i; // we now have index where we have the course.
			}
		}

		if (k == -1) {
			System.out.println("Sorry, you are not enrolled in this course.");
		} else {
			// School.courses.get(k).studentsInCourse.remove(this); //Update school
			// directory. In the school directory say, remove this student.
			School.courses.get(k).removeStudent(this); // Inside Course, we have to declare to Course that this student
														// is no longer inside the course
			School.courses.get(k).currentNumStu--;
			this.enrolledCourses.remove(School.courses.get(k));

			System.out.println("Success. You have been removed from your course.");
		}

	}

	public void viewCurrentCourses() {
		int num = enrolledCourses.size();
		if (num == 0) {
			System.out.println("You are currently not enrolled in any courses.");
		} else {
			System.out.println("These are the courses you are currently registered in: "); /// (7) !!! Check that this
																							/// part actually works. If
																							/// they are enrolled
			for (int i = 0; i < num; i++) { /// then this has to work
				System.out.println(enrolledCourses.get(i).courseName);

			}
		}
	}

	public void createUsernameAndPassword(Student student) throws IOException {
		
		//First of all, use the argument of the student object to get the index of the student object.
		int k=0;
		for (int i=0;i<School.students.size();i++) {
			if (School.students.get(i).idNumber.equals(student.idNumber)) {
				k=i;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		System.out.println("First you will create a username. Please enter a username: ");
		String username = br.readLine();

		boolean isPossible = true;
		for (int i = 0; i < School.students.size(); i++) {
			if (School.students.get(i).username.equals(username)) { 
																		
				isPossible = false;
			}
		}
		if (isPossible) {
			// If this username is not already taken.

			this.username = username; // if we just update the username of the student object, this should
										// automatically update everything
			School.students.get(k).username = username; // we also have to update the school directory;

			System.out.println("This username is possible. Now, please enter a password: ");

			String password = br.readLine();

			this.password = password;
			School.students.get(k).password = password;

			System.out.println("Success. Your username and password settings have been saved.");

		} else {
			while (isPossible == false) {
				// If this username is already taken.
				System.out.println("Unfortunately this username is already taken. Usernames have to be unique. Please try again: ");

				username = br.readLine();
				
				isPossible = true;
				for (int i = 0; i < School.students.size(); i++) {
					if (School.students.get(i).username.equals(username)) {
						isPossible = false;
					}
				}
			}
			// Exiting while loop means we found a good username.

			this.username = username;
			School.students.get(k).username = username;

			System.out.println("This username is possible. Now, please enter a password: ");

			String password = br.readLine();

			this.password = password;
			School.students.get(k).password = password;

			System.out.println("Success. Your username and password settings have been saved.");

			}
		}

	

	public void logIn(Student student, String username) throws IOException {
		//Likewise, use argument student to get the index of the student object.
		int k=0;
		for (int i=0;i<School.students.size();i++) {
			if (School.students.get(i).idNumber.equals(student.idNumber)) {
				k=i;
			}
		}//Now we know that the student's index is k.
		
		
		//Username section:
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	

		//boolean validUsername = true;
		//int k = -1;
		//for (int i = 0; i < School.students.size(); i++) {
		//	if (School.students.get(i).username.equals(username)) {
		//		validUsername = true;
		//		k = i;
		//	}
		//}

		//if (validUsername) { // such a username does exist, meaning such a student does indeed exist, (and we
								// know that they are at the index k)

		//} else {
//
//			while (!validUsername) {
//				System.out.println("Invalid username. Please try again: ");
//				username = br.readLine();
//				for (int i = 0; i < School.students.size(); i++) {
//					if (School.students.get(i).username.equals(username)) {
//						validUsername = true;
//						k = i;
//					}
//				}
//			}
			// Now that we have exited the loop, we have a valid username., user at index k.
		//}

		// Do nothing in the above if-else brackets, wait till we get here to proceed to
		// password.

		
		//Password section:
		System.out.println("Enter password:");
		String password = br.readLine();

		while (!School.students.get(k).password.equals(password)) { // Keep prompting if false.
			System.out.println("Incorrect password, try again: ");
			password = br.readLine();
		}

		System.out.println("Correct password. Successful entry to system.");

	}

}
