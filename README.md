# course-registration-system
Created a course registration system in Java. The functionalities the program allows are the following: Admin and students can log in with username/password. Students can add/drop/view courses. Admin can create/delete/edit courses, register new students. The initial data is provided in a csv file, but this is only read in once; future runs of the program use serialization to read the updated values, so changes to the data are saved in between runs of the program.

Classes/Interfaces used:
The classes being used in this project are: User, Admin, Student, Course, School, and CRS. The user class is a super class that extends to Admin and Student. Admin and Student are the children of User. AdminInt and StudentInt are abstract interfaces.  AdminInt is the interface of Admin, and StudentInt is the interface of Student. Admin implements its methods. The CRS is the main. Everything runs inside the CRS. 

Flow of the program: 
This program uses serialization to store changes to the data in between runs of the program. At the start of the program, if the .ser file exists, then the byte stream is deserialized to return it back to the object. If it does not exist, then the program reads from the CSV file, which contains the initial data about the courses and students.

The program itself has a menu area, which is designed with do-while loops. The options in the menu are printed with numbers, and the user selects an option with a number. The do while loops keep repeating themselves until the user selects to log out. In this case the Boolean variable runs becomes false, and the most external do-while exits. This is the point at which the serialization happens, and the changes to the program are recorded.

