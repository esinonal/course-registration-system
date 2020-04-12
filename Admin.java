import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;
import java.util.*;

public class Admin extends User implements AdminInt {

	Admin() {
	}
	
	// Part 1: Course management
	public void createNewCourse() throws IOException {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter course name:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine(); 
		
		System.out.println("Enter course ID:");
		String id = br.readLine(); 
		
		System.out.println("Enter max number of students:");
		int maxstu = Integer.parseInt(br.readLine()); 

		int currentstu = 0;
		
		ArrayList<Student> stus = new ArrayList<Student>();
		
		System.out.println("Enter name of instructor:");
		String ins = br.readLine(); 
		
		System.out.println("Enter section number:");
		int section = Integer.parseInt(br.readLine()); 
		
		System.out.println("Enter location:");
		String loc = br.readLine();
		
		System.out.println("Note: There will initially be no students in your class.");
		
		System.out.println("Creating your course. . .");
		
		
		Course newCourse = new Course(name, id, maxstu, currentstu, stus, ins, section, loc); 
		School.courses.add(newCourse);
		
		System.out.println("Your course has been added.");
	}  

	public void deleteCourse() throws IOException { 
		
		System.out.println("Enter name of course to delete:  ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine(); 
		
		int k = 0;
		for (int i = 0; i < School.courses.size(); i++) {
			if ((School.courses.get(i).courseName).equals(name)) {
				k++; //first loop is just to count how many such classes exist
			}
		}
		if (k == 0) {
			System.out.println("There is no such course found.");
		}
		else if (k == 1) {
			int a = 0; //just initializing to 0 (but normally will contain index of course to delete)
			
			//Find the index
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseName).equals(name)) {
					a = i; //now we have index
				}
			}
			
			System.out.println("There was one such course found. Did you mean: ");
			System.out.println(School.courses.get(a).courseName);
			System.out.println(School.courses.get(a).courseID);
			System.out.println(School.courses.get(a).instructor);
			System.out.println("Print '1' to delete this course, '2' to exit.");
			
			int num = Integer.parseInt(br.readLine()); 
			while ((num != 1) && (num != 2)) { //Keep taking number until enter 1 or 2
				System.out.println("Please enter '1' or '2'.");
				num = Integer.parseInt(br.readLine());
			}
			
			if (num == 1) {
				School.courses.remove(a);
				System.out.println("The course was deleted.");
			}
			else {
				//Exit function.
			}
		}
		else if (k == 2) { // Here I am assuming the maximum number of exactly named classes will be 2.
			int a = 0; //just initializing to 0 (but normally will contain index of course to delete)
			int b = 0;
			//Find the index
			
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseName).equals(name)) { 
					a = i; //now we have index a
					break; // forcing the break prevents loop from continuing to next values.
				}
			}
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseName).equals(name)) { 
					b = i; //now we have index b. The very first value gets read over so it does not get saved.
				}
			}
			
			System.out.println("There were two such courses. Did you mean: ");
			System.out.println("1:");
			System.out.println(School.courses.get(a).courseName);
			System.out.println(School.courses.get(a).courseID);
			System.out.println(School.courses.get(a).instructor);
			
			System.out.println("2:");
			System.out.println(School.courses.get(b).courseName);
			System.out.println(School.courses.get(b).courseID);
			System.out.println(School.courses.get(b).instructor);
			
			System.out.println("Print '1' to delete course 1, '2' to delete course 2, and '3' to exit.");
			
			int num = Integer.parseInt(br.readLine()); 
			while ((num != 1) && (num != 2) && (num != 3)) { //Keep taking number until enter 1 or 2
				System.out.println("Please enter '1' or '2' or '3'.");
				num = Integer.parseInt(br.readLine());
			}
			if (num == 1) {
				School.courses.remove(a);
			}
			else if (num == 2) {
				School.courses.remove(b);
			}
			else {
				//Exit function.
			}
		}
		else {
			System.out.println("Cannot accomodate navigating deleting 3 or more exactly named classes");
		}
	}

	public void editCourse() throws IOException {
		System.out.println("Enter name of course to edit:  ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String course = br.readLine(); 
		
		int k = 0;
		for (int i = 0; i < School.courses.size(); i++) {
			if ((School.courses.get(i).courseName).equals(course)) {
				k++; //Check if the course exists, if yes how many.
			}
		}
		
		if (k == 0) {  //If no course is found
			System.out.println("Sorry, no such course was found.");
		}
		
		else if (k == 1) { //if just one course is found
			
			int a = 0; //just initializing to 0 (but normally will contain index of course to delete)
			//Find the index
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseName).equals(course)) {
					a = i; //now we have index
				}
			}
			System.out.println("There was one such course found. Is this the course you would like to edit? ");
			System.out.println(School.courses.get(a).courseName);
			System.out.println(School.courses.get(a).courseID);
			System.out.println(School.courses.get(a).instructor);
			System.out.println("Print '1' to edit this course, '2' to exit.");
			
			int num = Integer.parseInt(br.readLine()); 
			
			if (num == 1) {
				
				System.out.println("What would you like to edit: Enter '1' for Instructor. '2' for Course Section. '3' for Course Location.");
				num = Integer.parseInt(br.readLine()); 
				if (num == 1) {
					System.out.println("Enter new instructor: ");
					String instructor = br.readLine(); 
					School.courses.get(a).instructor = instructor;
					
				}
				else if (num == 2) {
					System.out.println("Enter new course section: ");
					num = Integer.parseInt(br.readLine()); 
					School.courses.get(a).sectionNumber = num;
					
				} else {
					System.out.println("Enter new location: ");
					String location = br.readLine(); 
					School.courses.get(a).location = location;
				}
			
				System.out.println("Success. Your changes have been made.");
			}
			else {
				//Exit function.
			}
		}
		else if (k == 2) { //if there are two courses with such a name.
		
			System.out.println("There were two such courses found: ");
			
			int a = 0; //just initializing to 0 (but normally will contain index of course to delete)
			int b = 0;
			//Find the index
			
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseName).equals(course)) { 
					a = i; //now we have index a
					break; // forcing the break prevents loop from continuing to next values.
				}
			}
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseName).equals(course)) { 
					b = i; //now we have index b. The very first value gets read over so it does not get saved.
				}
			}
			
			System.out.println("1:");
			System.out.println(School.courses.get(a).courseName);
			System.out.println(School.courses.get(a).courseID);
			System.out.println(School.courses.get(a).instructor);
			
			System.out.println("2:");
			System.out.println(School.courses.get(b).courseName);
			System.out.println(School.courses.get(b).courseID);
			System.out.println(School.courses.get(b).instructor);
			
			System.out.println("Print '1' to edit course 1. Print '2' to edit course 2. Print '3' to exit.");
			
			int num = Integer.parseInt(br.readLine()); 
			
			if (num == 1) {
				System.out.println("What would you like to edit: Enter '1' for Instructor. '2' for Course Section. '3' for Course Location.");
				num = Integer.parseInt(br.readLine()); 
				if (num == 1) {
					System.out.println("Enter new instructor: ");
					String instructor = br.readLine(); 
					School.courses.get(a).instructor = instructor;
					
				}
				else if (num == 2) {
					System.out.println("Enter new course section: ");
					num = Integer.parseInt(br.readLine()); 
					School.courses.get(a).sectionNumber = num;
					
				} else {
					System.out.println("Enter new location: ");
					String location = br.readLine(); 
					School.courses.get(a).location = location;
				}
				System.out.println("Success. Your changes have been made.");	
			}
			else if (num == 2) {
				System.out.println("What would you like to edit: Enter '1' for Instructor. '2' for Course Section. '3' for Course Location.");
				num = Integer.parseInt(br.readLine()); 
				if (num == 1) {
					System.out.println("Enter new instructor: ");
					String instructor = br.readLine(); 
					School.courses.get(b).instructor = instructor;
				}
				else if (num == 2) {
					System.out.println("Enter new course section: ");
					num = Integer.parseInt(br.readLine()); 
					School.courses.get(b).sectionNumber = num;
					
				} else {
					System.out.println("Enter new location: ");
					String location = br.readLine(); 
					School.courses.get(b).location = location;
				}
				System.out.println("Success. Your changes have been made.");
			}
			else {
				//exit
			}
		}
		else {
			System.out.println("The program does not support functionality for 3 or more courses with the same name.");
		}
	}

	public void displayInfo() throws IOException {
		
		System.out.println("Enter course ID:  ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String courseID = br.readLine(); 
		
		int k = 0;
		for (int i = 0; i < School.courses.size(); i++) {
			if ((School.courses.get(i).courseID).equals(courseID)) {
				k++; //Check if the course exists, if yes how many.
			}
		}
		
		if (k == 0) {  //If no course is found
			System.out.println("Sorry, no such course was found.");
		}
		
		else if (k == 1) { //There is just one such course. 
			int a = 0; //just initializing to 0 (but normally will contain index of course to delete)
			//Find the index
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseID).equals(courseID)) {
					a = i; //now we have index
				}
			}
			System.out.println("Information for this course: ");
			System.out.println("Course name: " + School.courses.get(a).courseName);
			System.out.println("Course ID: "+ School.courses.get(a).courseID);
			System.out.println("Instructor: "+ School.courses.get(a).instructor);
			System.out.println("Location: "+ School.courses.get(a).location);
			System.out.println("Course section: "+ School.courses.get(a).sectionNumber);
			System.out.println("Maximum number of students: "+ School.courses.get(a).maxNumStu);
			System.out.println("Current number of students: "+ School.courses.get(a).currentNumStu);
		}
		
		
		else if (k == 2) { //There are two such courses, so prompt which one they would like.
			System.out.println("There were two such courses found: ");
			
			int a = 0; //just initializing to 0 (but normally will contain index of course to delete)
			int b = 0;
			//Find the index
			
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseID).equals(courseID)) { 
					a = i; //now we have index a
					break; // forcing the break prevents loop from continuing to next values.
				}
			}
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseID).equals(courseID)) { 
					b = i; //now we have index b. The very first value gets read over so it does not get saved.
				}
			}

			System.out.println("1:");
			System.out.println(School.courses.get(a).courseName);
			System.out.println(School.courses.get(a).courseID);
			System.out.println(School.courses.get(a).instructor);
			
			System.out.println("2:");
			System.out.println(School.courses.get(b).courseName);
			System.out.println(School.courses.get(b).courseID);
			System.out.println(School.courses.get(b).instructor);
			
			System.out.println("Print '1' to view course 1. Print '2' to view course 2. Print '3' to exit.");
			
			int num = Integer.parseInt(br.readLine()); 
			
			if (num == 1) {

				System.out.println("Information for this course: ");
				System.out.println("Course name: " + School.courses.get(a).courseName);
				System.out.println("Course ID: "+ School.courses.get(a).courseID);
				System.out.println("Instructor: "+ School.courses.get(a).instructor);
				System.out.println("Location: "+ School.courses.get(a).location);
				System.out.println("Course section: "+ School.courses.get(a).sectionNumber);
				System.out.println("Maximum number of students: "+ School.courses.get(a).maxNumStu);
				System.out.println("Current number of students: "+ School.courses.get(a).currentNumStu);
			}
			else if (num == 2) {
				System.out.println("Information for this course: ");
				System.out.println("Course name: " + School.courses.get(b).courseName);
				System.out.println("Course ID: "+ School.courses.get(b).courseID);
				System.out.println("Instructor: "+ School.courses.get(b).instructor);
				System.out.println("Location: "+ School.courses.get(b).location);
				System.out.println("Course section: "+ School.courses.get(b).sectionNumber);
				System.out.println("Maximum number of students: "+ School.courses.get(b).maxNumStu);
				System.out.println("Current number of students: "+ School.courses.get(b).currentNumStu);
			}
			else { //exit
			}
		}
		else {
			System.out.println("No functionality supported for 3 or more courses with same name.");
		}
	}
	
	public void registerStudent() throws IOException {
		System.out.println("Enter first name of student:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String firstName = br.readLine(); 
		
		System.out.println("Enter last name of student:");
		String lastName = br.readLine(); 
		
		//Username and password has to be created by student themselves. So we don't create these.
		//But we do create an ID number. 
		char firstInitial = firstName.charAt(0);
		char lastInitial = lastName.charAt(0);
		
		int a = (int)(Math.random()*10);
		int b = (int)(Math.random()*10);
		int c = (int)(Math.random()*10);
		
		String idNumber = "";
		idNumber += firstInitial;
		idNumber += lastInitial;
		idNumber += a;
		idNumber += b;
		idNumber += c;
		
		System.out.println("The ID number of this student will be: " + idNumber); 
		
		//We can assume that the student will be communicated their ID number which is required to log in.
	
		Student newStudent = new Student();
		newStudent.firstName = firstName;
		newStudent.lastName = lastName;
		newStudent.idNumber = idNumber; // So here, when Admin adds the student, they are adding their first name, their last name , and their id number. To the student object
		                                // So when the student tries to create a username, they have to match to this person whos spot is ready for them.
		School.students.add(newStudent);
		
		System.out.println("Success. Student has been registered in system.");
	}
	
	// Part 2: Reports 
	public void viewAllCourses() {
		System.out.println("View All Courses: ");
		
		//For all the courses that are in the School class, we will loop through and have a print statement for each.
		for (int i=0; i< School.courses.size(); i++) {
			
			System.out.println();
			System.out.println("Course name: " + School.courses.get(i).courseName);
			System.out.println("Course ID: "+ School.courses.get(i).courseID);
			System.out.println("Instructor: "+ School.courses.get(i).instructor);
			System.out.println("Maximum number of students: "+ School.courses.get(i).maxNumStu);
			System.out.println("Current number of students: "+ School.courses.get(i).currentNumStu);
			
			if (School.courses.get(i).currentNumStu == 0) {
				System.out.println("(Student names and IDs cannot be printed. (No students in course))");
			}
			else {
				System.out.println("Names and IDs of students enrolled: (Username in brackets)");  // Should check this later. (1) !!!!
				for (int k=0; k< School.courses.get(i).studentsInCourse.size(); k++) {
					System.out.println(School.courses.get(i).studentsInCourse.get(k).firstName + " " + School.courses.get(i).studentsInCourse.get(k).lastName + "  (" + School.courses.get(i).studentsInCourse.get(k).username + ")");
				}
			}
			System.out.println();		
		}
	}

	public void viewFullCourses() {
		//First, find out how many courses are full.
		int k = 0;
		for (int i=0; i<School.courses.size(); i++) {
			if (School.courses.get(i).maxNumStu == School.courses.get(i).currentNumStu) {
				k++;
			}
		}
		if (k == 0) {
			System.out.println("There are currently no courses that are full.");
		}
		else { //There is some number of courses that are full.
			System.out.println("These are the courses that are currently full: ");         /// (2)!!!! This is another thing to test later when students are functioning
				
			// So we know that we have K number of courses that are full.
			// However unfortunately we don't know the indices at which they occur.
			//So we can do the loop again, and each time we have an eqivalency, we do a print statement, and no equivalency, we discard.
			
			for (int i=0; i<School.courses.size(); i++) {
				if (School.courses.get(i).maxNumStu == School.courses.get(i).currentNumStu) {
					// We have equivalency so we do a print statement. (we like index i)
					
					System.out.println();
					System.out.println("Course name: " + School.courses.get(i).courseName);
					System.out.println("Course ID: "+ School.courses.get(i).courseID);
					System.out.println("Instructor: "+ School.courses.get(i).instructor);
					System.out.println("Maximum number of students: "+ School.courses.get(i).maxNumStu);
					System.out.println("Current number of students: "+ School.courses.get(i).currentNumStu);
					
					System.out.println("Names and IDs of students enrolled: (Username in brackets)"); 
					for (int j=0; j< School.courses.get(i).studentsInCourse.size(); j++) {
						System.out.println(School.courses.get(i).studentsInCourse.get(j).firstName + " " + School.courses.get(i).studentsInCourse.get(j).lastName + "  (" + School.courses.get(i).studentsInCourse.get(j).username + ")");
						
					}
				}
			}
		}
	}

	public void writeToFileCoursesThatAreFull() throws IOException {    /// (6) !!!!!!    check that this works too.  !!! for the file part. when some courses are full
		
		int k = 0;
		for (int i=0; i<School.courses.size();i++) { //Just to get number of courses that are full (check how many courses are full)
			if (School.courses.get(i).currentNumStu == School.courses.get(i).maxNumStu) {
				k ++;
			}
		}
		if (k == 0) {
			System.out.println("There are currently no courses that are full. (No file has been made.) ");
			// No file is made if there are no courses that are full.
		}
		else {
			// k is num of courses that are full.
			PrintWriter writer = new PrintWriter("ListOfFullCourses.txt", "UTF-8");
			for (int i=0; i<School.courses.size();i++) {
				if (School.courses.get(i).currentNumStu == School.courses.get(i).maxNumStu) {
					writer.println(School.courses.get(i).courseName);
				}
			}
			writer.close();
			System.out.println("Success. Saved to text file the list of courses that are currently full.");
		}
	}

	public void viewStudentsInCourse() throws IOException {
		
		System.out.println("Enter course name:  ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String course = br.readLine(); 
		
		int k = 0;
		for (int i = 0; i < School.courses.size(); i++) {
			if ((School.courses.get(i).courseName).equals(course)) {
				k++; //Check if the course exists, if yes how many.
			}
		}
		if (k == 0) {  //If no course is found
			System.out.println("Sorry, no such course was found.");
		}
		else if (k == 1) { //There is only one such course.
			int a = 0; //just initializing to 0 (but normally will contain index of the course)
			//Find the index
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseName).equals(course)) {
					a = i; //now we have index at which course occurs
				}
			}
			int stus = School.courses.get(a).studentsInCourse.size();
			if (stus ==  0) {
				System.out.println("There are no students in this course.");
			}
			else {
				System.out.println("The students in this course are: ");
				for (int i=0; i< School.courses.get(a).studentsInCourse.size(); i++) {
					System.out.println(School.courses.get(a).studentsInCourse.get(i).firstName + " " + School.courses.get(a).studentsInCourse.get(i).lastName + "  (" + School.courses.get(a).studentsInCourse.get(i).username + ")");
				}
			}		
		}
		else if (k == 2) {
			System.out.println("There are two such courses. Did you mean to view the students in '1' or '2'? Print '1' or '2', or '3' to exit. ");
			
			int a = 0; //just initializing to 0 (but normally will contain index of the course)
			int b = 0;
			//Find the index
			
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseName).equals(course)) { 
					a = i; //now we have index a
					break; // forcing the break prevents loop from continuing to next values.
				}
			}
			for (int i = 0; i < School.courses.size(); i++) {
				if ((School.courses.get(i).courseName).equals(course)) { 
					b = i; //now we have index b. The very first value gets read over so it does not get saved.
				}
			}
			System.out.println("1:");
			System.out.println(School.courses.get(a).courseName);
			System.out.println(School.courses.get(a).courseID);
			System.out.println(School.courses.get(a).instructor);
			
			System.out.println("2:");
			System.out.println(School.courses.get(b).courseName);
			System.out.println(School.courses.get(b).courseID);
			System.out.println(School.courses.get(b).instructor);
			
			int num = Integer.parseInt(br.readLine()); 
			
			if (num == 1) {
				int stus = School.courses.get(a).studentsInCourse.size();
				if (stus ==  0) {
					System.out.println("There are no students in this course.");
				}
				else {
					System.out.println("The students in this course are: ");
					for (int i=0; i< School.courses.get(a).studentsInCourse.size(); i++) { ///(3)!!!!! Should check this too once the students are functioning. 
						System.out.println(School.courses.get(a).studentsInCourse.get(i).firstName + " " + School.courses.get(a).studentsInCourse.get(i).lastName + "  (" + School.courses.get(a).studentsInCourse.get(i).username + ")");
					}
				}
			}
			else if (num == 2) {
				int stus = School.courses.get(b).studentsInCourse.size();
				if (stus ==  0) {
					System.out.println("There are no students in this course.");
				}
				else {
					System.out.println("The students in this course are: ");
					for (int i=0; i< School.courses.get(b).studentsInCourse.size(); i++) {
						System.out.println(School.courses.get(b).studentsInCourse.get(i).firstName + " " + School.courses.get(b).studentsInCourse.get(i).lastName + "  (" + School.courses.get(b).studentsInCourse.get(i).username + ")");
					}
				}
			}
			else {
				//Exit
			}
		}
		else {
			System.out.println("Program does not support functionality for 3 or more exactly named courses.");
		}
	}
	
	public void viewCoursesOfStudent() throws IOException {
		System.out.println("Enter first name of student: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String firstName = br.readLine(); 
		
		System.out.println("Enter last name of student: ");
		String lastName = br.readLine(); 
		
		int k = -1;
		for(int i=0; i < School.students.size(); i++) {
			if ((School.students.get(i).firstName).equals(firstName)) {
				if (School.students.get(i).lastName.equals(lastName)) {
					k = i; // If we do get to this stage, this means that k will be the index at which we have our student in the student array
				}
			}
		}
		if (k == -1) { // If k is still -1, this means that the student we are searching for does not exist.
			System.out.println("The student you are looking for does not exist.");
		}
		else { // k is the index at which we have our student in the student arraylist in school
			int num = 0;
			
			num = School.students.get(k).enrolledCourses.size();
			
			if (num == 0) {
				System.out.println("This student is currently not enrolled in any courses.");
			}
			else {
				System.out.println("This student's courses are: ");
				for (int i=0; i<School.students.get(k).enrolledCourses.size();i++) {
					System.out.println(School.students.get(k).enrolledCourses.get(i).courseName);   //  (4) !!! Check that this works too once you have some actual students. 
				}
			}
		}
	}

	public void sortCourses() { // (5) !!!! Test this one later too. Just to make sure that -1 and +1 are comparing in correct order.
		Collections.sort(School.courses);
		System.out.println("Success. Your courses have been sorted. (According to current number of students registered).");
		for (int i=0; i< School.courses.size(); i++) {
			System.out.println();
			System.out.println("Course name: " + School.courses.get(i).courseName);
			System.out.println("Current number of students: "+ School.courses.get(i).currentNumStu);
		}
	}
}
