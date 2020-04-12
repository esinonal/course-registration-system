import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class CRS {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		ArrayList<Course> list = new ArrayList<Course>(); // Must declare at top so is not local. This is used in reading courses from .ser file in try catch block.
                                                          // School also must be declare at top so not local. Used in try block as school.
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Student> students = new ArrayList<Student>();

		// Part 1: Read from .ser file if exists
		try{
			  //FileInputSystem recieves bytes from a file
		      FileInputStream fis = new FileInputStream("newCourseList.ser");
		      FileInputStream fis2 = new FileInputStream("newStudentList.ser");
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      ObjectInputStream ois2 = new ObjectInputStream(fis2);
		      
		      //Cast as Employee. readObject will take the object from ObjectInputStream
		      courses = (ArrayList<Course>)ois.readObject();
		      ois.close();
		      fis.close();
		      students = (ArrayList<Student>)ois2.readObject();
		      ois2.close();
		      fis2.close();
		      School.courses = courses;
		      School.students = students;
		}
		catch(IOException ioe) { //If the .ser file does not exist, read from the CSV.
		                         //ioe.printStackTrace();
			File file = new File(args[0]);
			if (!file.exists()) {
				System.err.println("File cannot be read.");
				System.exit(0);
			}
			Scanner scan = new Scanner(file);
			scan.nextLine();
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] array = line.split(",");
					
				Course course = new Course(array[0], array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]), new ArrayList<Student>(), array[5], Integer.parseInt(array[6]), array[7]);
				list.add(course);
			}
			//Now we have the list of courses that is available. (list)
			// Open up and make available the Courses and Students Arraylists
			//ArrayList<Course> courses = School.getCourses();
			courses = list; // So now, we have that our Courses Arraylist is filled up with our courses (have not updated School class yet)
			                // Whenever Courses or Students Arraylist is edited, we also have to Update our Arraylists so we are up to date.
			School.setCourses(courses);
			//ArrayList<Student> students = new ArrayList<Student>(); // We also have our students, for the moment, empty. 
			School.setStudents(students);
		}
		catch(ClassNotFoundException cnfe) {
		}
		
		// Part 2: All menus - Have everything in a Do-While that allows the user to go through all menus and select functions
		//
		System.out.println("Welcome to Course Registration System. ");
		Scanner myScanner = new Scanner(System.in);
		boolean runs = true;  // At the end of do-while loop, this will turn to false and loop will exit
		
		do { 
			System.out.println("Are you an Admin or a Student? Enter '1' for Admin, '2' for Student.");
			int num = myScanner.nextInt();
			
			if (num == 1) { //  ~~~ ADMIN SECTION ~~~

				Admin admin = new Admin();                                                      // Admin's username and password section
				System.out.println("Welcome admin. Input your username:");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String username = br.readLine(); 
				while (! username.equals("Admin")) { //Keep taking username until enter "Admin"
					System.out.println("Please enter correct username.");
					username = br.readLine(); 
				}
				
				System.out.println("Input password: "); //Keep taking password until enter "Admin001"
				String password = br.readLine(); 
				while (! password.equals("Admin001")) {
					System.out.println("Please enter correct password.");
					password = br.readLine();
				}
				// Have entered the main menu for the admin. Print Course management and Reports and allow user to choose.
				
				do { 
					System.out.println("Main menu. Select '1' for Course Management and '2' for Reports. Enter '3' to log out. ");
					num = myScanner.nextInt();
					if (num == 1) {
						//Have entered Course Management. Must print sub sections and allow user to choose.
						do {
							System.out.println("Course Management");
							System.out.println("1- Create new course");
							System.out.println("2- Delete course");
							System.out.println("3- Edit a course");
							System.out.println("4- Display info for a course");
							System.out.println("5- Register a student");
							System.out.println("6- Exit");
							System.out.println("Please enter a number: ");
							
							num = myScanner.nextInt();
							
							if (num == 1) {
								admin.createNewCourse();  //works
							}
							else if (num == 2) {
								admin.deleteCourse(); //works
							}
							else if (num == 3) {
								admin.editCourse(); //works
							}
							else if (num == 4) {
								admin.displayInfo(); //works
							}
							else if (num == 5) {
								admin.registerStudent(); //works
							}
							else {
								if (num != 6) {
									System.out.println("Please enter a correct number.");
								}
							}
							} while (num != 6);
					}
					else if (num == 2){
						//Have entered Reports section. Must print sub sections and allow user to choose.
						do {
							System.out.println("Reports");
							System.out.println("1- View all courses");
							System.out.println("2- View all courses that are full");
							System.out.println("3- Write to file list of courses that are full");
							System.out.println("4- View names of students in specific course");
							System.out.println("5- View list of courses specific student is in");
							System.out.println("6- Sort courses based on current number of students in course");
							System.out.println("7- Exit");
							System.out.println("Please enter a number: ");
							num = myScanner.nextInt();
							
							if (num == 1) {
								admin.viewAllCourses();  // works
							}
							else if (num == 2) {
								admin.viewFullCourses(); // works
							}
							else if (num == 3) {
								admin.writeToFileCoursesThatAreFull();  // works
							}
							else if (num == 4) {
								admin.viewStudentsInCourse();   // works
							}
							else if (num == 5) {
								admin.viewCoursesOfStudent(); // works
							}
							else if (num == 6) {
								admin.sortCourses(); // works
							}
							else {
								if (num != 7) {
									System.out.println("Please enter a correct number.");
								}
							}
						} while(num != 7);
					}
					else {
						if (num != 3) {
							System.out.println("Please enter a correct number."); 
						}
					}
				} while (num != 3); // If num == 3, then log out happens to the "are you admin or student" main menu.
			}
			else { //        ~~~ STUDENT SECTION ~~~
				
				Student student = null;
				System.out.println("Welcome student. Enter '1' to create a new username/password and '2' if you already have them.");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				num = myScanner.nextInt();
				
				while ((num != 1) && (num != 2)) {
					System.out.println("Please enter '1' or '2': ");                         // Student's username and password section
					num = myScanner.nextInt();
				}
				if (num == 1) {
					String ID = null;
					System.out.println("Please enter your ID: "); //We need to have an ID num to match the student to a valid Student Object.
					ID = br.readLine(); 
					student = getStudentByID(School.students, ID); //We find the student object
					
					if(student == null) { //This is the only case where the student object doesnt exist, for all other parts, we can assume Student is now valid.
						System.out.println("Sorry, this ID is not valid. Please contact your advisor. ");  // And the program terminates here, no menu appears afterwards.
					}
					else {
						student.createUsernameAndPassword(student);  // The student creates a username and password.
						
						do {
							System.out.println("Course management: (Select 6 to log out of account)"); // And then the do while menu appears.
							System.out.println("1- View all courses");
							System.out.println("2- View all courses not full");
							System.out.println("3- Register in a course");
							System.out.println("4- Withdraw from a course");
							System.out.println("5- View all current courses");
							System.out.println("6- Exit (log out))");
							System.out.println("Please enter a number: ");
							num = myScanner.nextInt();
							
							if (num == 1) {
								student.viewAllCourses();    // works
							}
							else if (num == 2) {
								student.viewCoursesNotFull(); //works
							}
							else if (num == 3) {
								student.registerInCourse();  // works
							}
							else if (num == 4) {
								student.withdrawFromCourse(); // works
							}
							else if (num == 5) {
								student.viewCurrentCourses(); //works
							}
							else {
								if (num != 6) {
									System.out.println("Please enter a correct number.");
								}
							}
						} while(num!=6);	
					}
				}
				else {
					String username = null;
					System.out.println("Please enter your username: ");
					username = br.readLine(); 
					student = getStudentbyUsername(School.students, username);
					if(student == null) {
						System.out.println("Sorry, this username is not valid. Please contact your advisor. "); 
					}
					else {
						student.logIn(student, username);  // The student logs in. 
						do {
							System.out.println("Course management: (Select 6 to log out of account)"); // And the do-while happens
							System.out.println("1- View all courses");
							System.out.println("2- View all courses not full");
							System.out.println("3- Register in a course");
							System.out.println("4- Withdraw from a course");
							System.out.println("5- View all current courses");
							System.out.println("6- Exit (log out))");
							System.out.println("Please enter a number: ");
							num = myScanner.nextInt();
							
							if (num == 1) {
								student.viewAllCourses();    // works
							}
							else if (num == 2) {
								student.viewCoursesNotFull(); //works
							}
							else if (num == 3) {
								student.registerInCourse();  // works
							}
							else if (num == 4) {
								student.withdrawFromCourse(); // works
							}
							else if (num == 5) {
								student.viewCurrentCourses(); //works
							}
							else {
								if (num != 6) {
									System.out.println("Please enter a correct number.");
								}
							}
						} while(num!=6);
					}
				}
			}
			
		runs = false; //at the end we want to exit the program.
		System.out.println("Exiting Course registration system. Goodbye!");
		} while (runs);

		// Part 4: Serialize the two arraylists
		//School school = new School(students, courses);
		ArrayList<Course> newCourseList = School.courses;
		ArrayList<Student> newStudentList = School.students;

		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("newCourseList.ser");
			FileOutputStream fos2 = new FileOutputStream("newStudentList.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			
			//Writes the specific object to the OOS
			oos.writeObject(newCourseList);
			oos2.writeObject(newStudentList);
			
			//Close both streams
			oos.close();
			oos2.close();
			fos.close();
			fos2.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}		
	
	// ~~Some methods used in main are put at the end: ~~
	public static Student getStudentByID(ArrayList<Student> students, String ID) {
		for (int i=0; i<School.students.size();i++) {
			if (School.students.get(i).idNumber.equals(ID)) {
				return School.students.get(i);
			}
		}
		return null;
	}
	
	public static Student getStudentbyUsername(ArrayList<Student> students, String username) {
		for (int i=0; i<School.students.size();i++) {
			if (School.students.get(i).username.equals(username)) {
				return School.students.get(i);
			}
		}
		return null;
	}
}		
