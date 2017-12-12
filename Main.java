package rs5253;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
public class Main implements Serializable {
	public static void main(String[] args) throws IOException {
		//Make an array of admins
		ArrayList<Admin> adminList = new ArrayList<Admin>();
		Admin admin1 = new Admin();
		adminList.add(admin1);		
		//Make an array list of course objects
		ArrayList<Course> list = new ArrayList<Course>();
		
	//Deserialization		
	ArrayList<Student> studentList = new ArrayList<Student>();
	File file1 =new File("Course.ser");
	File file2=new File("Student.ser");
		

		
	if(file1.exists() && file2.exists()){

			FileInputStream fisCourse = new FileInputStream("Course.ser");

			ObjectInputStream oisCourse = new ObjectInputStream(fisCourse);
			
			System.out.println("Deserializing...");

		try{

			list=((ArrayList<Course>) oisCourse.readObject());

			oisCourse.close();
		           
			fisCourse.close();
	           
		}catch(Exception E){

			E.printStackTrace();
		}

		try{

			FileInputStream fisStudent = new FileInputStream("Student.ser");

			ObjectInputStream oisStudent = new ObjectInputStream(fisStudent);

			studentList = ((ArrayList<Student>) oisStudent.readObject());
			
			//test to see if the students are being saved, not necessary for the code
			//for (Student stu : studentList){
			//	System.out.println(stu.firstName + " " + stu.lastName);
			//}
			oisStudent.close();

			fisStudent.close();
		
		}catch(Exception E){

			E.printStackTrace();

			return;

		}

	}

	else{
		
	//CITATION: https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	String csvFile = "MyUniversityCourses.csv";
	String line = "";
	String csvSplit = ",";

			//go through the file and input the information into an arraylist
			try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
				//have the buffer reader skip the first line
				//CITATION: http://stackoverflow.com/questions/23236000/bufferedreader-to-skip-first-line
				br.readLine();
				//go through the rest of the lines
				while ((line = br.readLine()) != null) {
					//split the line in the csv file by commas
					String[] tempCourse = line.split(csvSplit);
					//assign each position in the array to a part of the temporary course object
					String tempCourseName = tempCourse[0];
					String tempCourseID = tempCourse[1];
					String StringTempMaxNumStudents = tempCourse[2];
					//convert to an int
					int TempMaxNumStudents = Integer.parseInt(StringTempMaxNumStudents);
					String StringTempCurrentNumStudents = tempCourse[3];
					//convert to an int
					int TempCurrentNumStudents = Integer.parseInt(StringTempCurrentNumStudents);
					String TempCourseInstructor = tempCourse[5];
					String StringTempCourseSectionNum = tempCourse[6];
					int TempCourseSectionNum = Integer.parseInt(StringTempCourseSectionNum);
					String TempCourseLocation = tempCourse[7];
					//make the course object
					Course tempCourseObject = new Course(tempCourseName, tempCourseID, TempMaxNumStudents, TempCurrentNumStudents, TempCourseInstructor, TempCourseSectionNum, TempCourseLocation);
					//add the course object to the array`
					list.add(tempCourseObject);
				}
			//exception if the file is not found
			} catch (IOException e){
				e.printStackTrace();
			}
	}

		

		//Start the interactions
		System.out.println("Hello, User! Welcome to the University Course Registration System!");
		int adminOrStudentFlagger = 0;
		boolean loginFlagger = true;
		Scanner input = new Scanner(System.in);
		String adminOrStudent;
		String tempUsername = "";
		String tempPassword;
		//check if the user is an admin or student
		while (adminOrStudentFlagger == 0) {
			System.out.println("Are you an admin or student?");
			adminOrStudent = input.nextLine().toLowerCase();
			//if the user is an admin
			if (adminOrStudent.equals("admin")){
				System.out.println("Great! Let's get you started, Admin");
				//make sure user is ok
				while (loginFlagger) {
					System.out.println("Please enter your username: ");
					tempUsername = input.nextLine();
					System.out.println("Please enter your password: ");
					tempPassword = input.nextLine();
					for (int i = 0; i < adminList.size(); i++) {
						adminList.get(i);
						if (adminList.get(i).getUsername().equals(tempUsername) && adminList.get(i).getPassword().equals(tempPassword)){
							System.out.println("Perfect! You are logged in, " + adminList.get(i).getUsername());
							loginFlagger = false;
							adminOrStudentFlagger = 1;
						}
					}
					if (tempUsername.toLowerCase().equals("back")){
						loginFlagger = false;
					}
					else if (loginFlagger) {
						System.out.println("The combination of Username and Password is not correct, please reenter the information.");
						System.out.println("To go back, input \"Back\" as your username.");
					}
				}
			}
			//if the user is a student
			else if (adminOrStudent.equals("student")){
				System.out.println("Great! Let's get you started, Student");
					while (loginFlagger) {
						System.out.println("Please enter your username: ");
						tempUsername = input.nextLine();
						System.out.println("Please enter your password: ");
						tempPassword = input.nextLine();
						for (int i = 0; i < studentList.size(); i++) {
							if (studentList.get(i).getUsername().equals(tempUsername) && studentList.get(i).getPassword().equals(tempPassword)){
								System.out.println("Perfect! You are logged in, " + studentList.get(i).getUsername());
								loginFlagger = false;
								adminOrStudentFlagger = 2;
							}
						}
						if (tempUsername.toLowerCase().equals("quit")){
							loginFlagger = false;
						}
						else if (loginFlagger){
							System.out.println("The combination of Username and Password is not correct, please reenter the information.");
							System.out.println("To go back, input \"Back\" as your username.");
						}
					}
			}
			//if the user wants to quit
			else if(adminOrStudent.equals("quit")){
				System.out.println("Ending the program...");
				adminOrStudentFlagger = 3;
			}
			//data validation
			else {
				System.out.println("Your input was not \"Admin\" or \"Student\", please enter one of those choices.");
				System.out.println("To quit the program, input \"Quit\"");
			}
		}

	String tempCommand;
	String cmOrReport;
	int cmOrReportFlagger = 0;
	//if admin
	if (adminOrStudentFlagger == 1){
		System.out.println("Now let's get started, " + tempUsername);
		while (cmOrReportFlagger == 0){
			System.out.println("Do you want to access Course Management commands or Reports commands?: ");
			cmOrReport = input.nextLine().toLowerCase();
			if (cmOrReport.equals("course management")) {
				cmOrReportFlagger = 1;
				System.out.println("Thanks! Loading up the Course Management Command interface...");
			}
			else if (cmOrReport.equals("reports")) {
				cmOrReportFlagger  = 2;
				System.out.println("Thanks! Loading up the Reports Command interface...");
			}
			else if (cmOrReport.equals("help")) {
				System.out.println("Great, here is the list of commands:");
				System.out.println("----- COURSES MANAGEMENT -----");
				System.out.println("1. Create a new course.");
				System.out.println("2. Delete a course.");
				System.out.println("3. Edit a course.");
				System.out.println("4. Display information for a given course.");
				System.out.println("5. Register a student.");
				System.out.println("6. Exit.");
				System.out.println("----- REPORTS -----");
				System.out.println("1. View all courses.");
				System.out.println("2. View all courses that are full.");
				System.out.println("3. Write to a file the list of courses that are full.");
				System.out.println("4. View the names of the students being registered in a specific course.");
				System.out.println("5. View the list of courses that a given student is being registered on.");
				System.out.println("6. Sort courses based on the current number of student register.");
				System.out.println("7. Exit.");
			}
			else {
				System.out.println("Invalid entry. Please enter \"Course Management\" or \"Reports\".");
				System.out.println("If you are unsure, enter \"Help\" for the list of commands.");
			}
			
		}	
	}
	boolean adminFlagger = true;
	if (cmOrReportFlagger == 1){
		//admin class
		while (adminFlagger){	
			
			System.out.println("What do you want to do? Enter \"Help\" if you want the commands.");
			tempCommand = input.nextLine().toLowerCase();
			//Courses management options
			switch (tempCommand) {
			case "help":
				System.out.println("Great, here is the list of commands:");
				System.out.println("----- COURSES MANAGEMENT -----");
				System.out.println("1. Create a new course. (command: \"New Course\")");
				System.out.println("2. Delete a course. (command: \"Delete Course\")");
				System.out.println("3. Edit a course. (command: \"Edit Course\")");
				System.out.println("4. Display information for a given course. (command: \"Display Course Info\")");
				System.out.println("5. Register a student. (command: \"Register Student\")");
				System.out.println("6. Exit (command: \"Exit\")");
				break;
			case "new course":
				System.out.println("All right, let's start building a new course.");
				//get the course name
				System.out.println("Enter the course name: ");
				String cmCourseName = input.nextLine();
				//get the course id
				System.out.println("Enter the course ID: ");
				String cmCourseID = input.nextLine();
				//get the course instructor
				System.out.println("Enter the course instructor: ");
				String cmCourseInstructor = input.nextLine();
				//get the course location
				System.out.println("Enter the course location: ");
				String cmCourseLocation = input.nextLine();
				//get the max number of students
				System.out.println("Enter the maximum number of students: ");
				while (!input.hasNextInt()){
					System.out.println("Bad input! Make sure your input is a number: ");	
					input.next();
				}
				int cmMaxNumStudents = input.nextInt();
				input.nextLine();
				//get the course section number
				System.out.println("Enter the course section number: ");
				while (!input.hasNextInt()){
						System.out.println("Bad input! Make sure your input is a number: ");
						input.next();
				}
				int cmCourseSectionNum = input.nextInt(); 
				input.nextLine();
				//the starting number of students will be 0
				int cmCurrentNumStudents = 0;
				Admin.newCourse(cmCourseName, cmCourseID, cmMaxNumStudents,cmCurrentNumStudents,cmCourseInstructor,cmCourseSectionNum,cmCourseLocation, list);
				System.out.println("Great! " + cmCourseName + " has been created.");
				break;
			case "delete course":
				System.out.println("All right, let's start deleting a course.");
				boolean nameFlagger = true;
				System.out.println("Enter the course ID: ");
				String cm2CourseID = "";
				while (nameFlagger) {
					cm2CourseID = input.nextLine();
					for (int i = 0; i < list.size(); i++){
						if (list.get(i).getCourseID().equals(cm2CourseID)) {
							nameFlagger = false;
						}
					}	
					if (!nameFlagger) System.out.println("Great, now enter the course's section number: ");
					
					else if (nameFlagger) System.out.println("No course under that ID exists! Please reenter the course ID: ");

				}
				boolean deleteFlag = true;
				while (deleteFlag){
					//make sure the user inputs an int
					while (!input.hasNextInt()){
						System.out.println("Bad input! Make sure your input is a number: ");
						input.next();
					}
					int cm2CourseSectionNum = input.nextInt(); 
					input.nextLine();
					//go through a for loop to make sure that the course section number exists
					for (int j = 0; j < list.size(); j++){
						if (list.get(j).getCourseID().equals(cm2CourseID) && (list.get(j).getCourseSectionNum() == cm2CourseSectionNum)){
							Admin.deleteCourse(cm2CourseID, cm2CourseSectionNum, list);
							System.out.println("Perfect. Deleted " + cm2CourseID + "'s Section Number " + cm2CourseSectionNum);
							deleteFlag = false;
						}
					}
					if (deleteFlag) {
						System.out.println("That combination of course ID and course section number does not exist.");
						System.out.println("Please reenter " + cm2CourseID + "'s section number:");
					}
				}
				break;
			case "edit course":
				System.out.println("All right, let's start editing a course.");
				boolean nameFlagger2 = true;
				System.out.println("Enter the course ID: ");
				String cm3CourseID = "";
				while (nameFlagger2) {
					cm3CourseID = input.nextLine();
					for (int i = 0; i < list.size(); i++){
						if (list.get(i).getCourseID().equals(cm3CourseID)) {
							nameFlagger2 = false;
						}
					}	
					if (!nameFlagger2) System.out.println("Great, now enter the course's section number: ");
					
					else if (nameFlagger2) System.out.println("No course under that ID exists! Please reenter the course ID: ");

				}
				boolean editFlag = true;
				int cm3CourseSectionNum = 0;
				while (editFlag){
					//make sure the user inputs an int
					while (!input.hasNextInt()){
						System.out.println("Bad input! Make sure your input is a number: ");
						input.next();
					}
					cm3CourseSectionNum = input.nextInt(); 
					input.nextLine();
					//go through a for loop to make sure that the course section number exists
					for (int j = 0; j < list.size(); j++){
						if (list.get(j).getCourseID().equals(cm3CourseID) && (list.get(j).getCourseSectionNum() == cm3CourseSectionNum)){
							System.out.println("Perfect. Let's start editing " + cm3CourseID + "'s Section Number " + cm3CourseSectionNum);
							editFlag = false;
						}
					}
					if (editFlag) {
						System.out.println("That combination of course ID and course section number does not exist.");
						System.out.println("Please reenter " + cm3CourseID + "'s section number:");
					}
				}
				boolean editerFlag = true;
				
				while (editerFlag) {
					String requestInput;
					int intRequestInput;
					System.out.println("What field would you like to edit? Type help for the options: ");
					String requestField = input.nextLine().toLowerCase();
					
					//start of switch
					
					while (requestField.equals("")){
						requestField = input.nextLine().toLowerCase();
						if (requestField.equals("")) System.out.println("Bad input!");
					}
					
					
					switch (requestField) {
					case "help" :
						System.out.println("Here are the options:");
						System.out.println("- Course name (input: \"Name\")");
						System.out.println("- Course ID (input: \"ID\")");
						System.out.println("- Maximum number of students (\"input: Max Students\")");
						System.out.println("- Current students (input: \"Current Students\")");
						System.out.println("- Course instructor (input: \"Instructor\")");
						System.out.println("- Course section number (input: \"Section Number\")");
						System.out.println("- Course location (input: \"Location\")");
						break;
					case "name" : 
						System.out.println("OK, what would you like to change the name to? :");
						requestInput = input.nextLine();
						System.out.println("Great! The course name has been changed to " + requestInput);
						Admin.editCourse(cm3CourseID, cm3CourseSectionNum, requestField, requestInput, list);			
						editerFlag = false;
						break;
					case "id" : 
						System.out.println("OK, what would you like to change the name to? :");
						requestInput = input.nextLine();
						System.out.println("Great! The course ID has been changed to " + requestInput);
						Admin.editCourse(cm3CourseID, cm3CourseSectionNum, requestField, requestInput, list);	
						editerFlag = false;
						break;
					case "max students" : 
						System.out.println("OK, what would you like to change the maximum number of students to? :");
						while (!input.hasNextInt()){
							System.out.println("Bad input! Make sure your input is a number: ");
							input.next();
						}
						intRequestInput = input.nextInt();
						input.nextLine();
						System.out.println("Great! The course's maximum number of students has been changed to " + intRequestInput);
						Admin.editCourse(cm3CourseID, cm3CourseSectionNum, requestField, intRequestInput, list);
						editerFlag = false;
						break;
					case "current students" : 
						boolean aorFlagger = true;
						System.out.println("OK, would you like to add or remove a student? :");
						while (aorFlagger) {
							String addOrRemove = input.nextLine().toLowerCase();
							if (addOrRemove.equals("add")) {
								System.out.println("Great! Let's start adding a student.");
								System.out.println("Please input the student's username: ");
								boolean usernameFlagger = true;
								while (usernameFlagger){
									String addUsername = input.nextLine();
									//go through students to see if that username exists
									for (int k = 0; k < studentList.size(); k++){
										if (studentList.get(k).getUsername().equals(addUsername)){
										
											Student tempStudent = studentList.get(k);
											Admin.editCourse(cm3CourseID, cm3CourseSectionNum, requestField, addOrRemove, tempStudent, studentList, list);
											System.out.println(tempStudent.getFirstName() + " " + tempStudent.getLastName() + " has been added to " + cm3CourseID + "'s section number " + cm3CourseSectionNum);
											usernameFlagger = false;
											aorFlagger = false;
											editerFlag = false;
											break;
										}									
									}
									if (usernameFlagger) {
										System.out.println("That username is not associated with a registered student. Please enter a valid username: ");
									}
								}
							}
							else if (addOrRemove.equals("remove")){
								System.out.println("Great! Let's start removing a student. ");
								System.out.println("Please input the student's username: ");
								boolean usernameFlagger = true;
								while (usernameFlagger){
									String addUsername = input.nextLine();
									for (int k = 0; k < studentList.size(); k++){
										//check if inputed username is a student
										if (studentList.get(k).getUsername().equals(addUsername)){
											//check if student is in the class
											for (int l = 0; l < list.size(); l ++){
												if(list.get(l).getCurrentStudents().contains(studentList.get(k))){
													//the student passed through edit course is the student object at variable k
													Student tempStudent = studentList.get(k);
													Admin.editCourse(cm3CourseID, cm3CourseSectionNum, requestField, addOrRemove, tempStudent, studentList, list);
													System.out.println(tempStudent.getFirstName() + " " + tempStudent.getLastName() + " has been deleted from " + cm3CourseID + "'s section number " + cm3CourseSectionNum);
													usernameFlagger = false;
													aorFlagger = false;
													editerFlag = false;
													break;
												}
											}
										}									
									}
									if (usernameFlagger) {
										System.out.println("That username is not associated with a registered student in the course. Please enter a valid username: ");
									}
								}
							}
							
							else {
								System.out.println("Please input \"add\" or \"remove\":");
							}
						}
						break;
						
					case "instructor" : 
						System.out.println("OK, who would you like to change the instructor to? :");
						requestInput = input.nextLine();
						System.out.println("Great! The course instructor has been changed to " + requestInput);
						Admin.editCourse(cm3CourseID, cm3CourseSectionNum, requestField, requestInput, list);	
						editerFlag = false;
						break;
					case "section number" :
						System.out.println("OK, what would you like to change the section number to? :");
						while (!input.hasNextInt()){
							System.out.println("Bad input! Make sure your input is a number: ");
							input.next();
						}
						intRequestInput = input.nextInt();
						input.nextLine();
						System.out.println("Great! The course section number has been changed to " + intRequestInput);
						Admin.editCourse(cm3CourseID, cm3CourseSectionNum, requestField, intRequestInput, list);
						editerFlag = false;
						break;
					case "location" :
						System.out.println("OK, what would you like to change the location to? :");
						requestInput = input.nextLine();
						System.out.println("Great! The course location has been changed to " + requestInput);
						Admin.editCourse(cm3CourseID, cm3CourseSectionNum, requestField, requestInput, list);	
						editerFlag = false;
						break;
					default :
						System.out.println("Bad input! Please make sure it is one of the options.");
						break;
					}
				}
				break;
			
			case "display course info":
				System.out.println("All right, let's start displaying a course's information.");
				boolean displayFlagger = true;
				System.out.println("Enter the course ID: ");
				String cm4CourseID = "";
				while (displayFlagger) {
					cm4CourseID = input.nextLine();
					for (int m = 0; m < list.size(); m++){
						if (list.get(m).getCourseID().equals(cm4CourseID)) {
							displayFlagger = false;
							System.out.println("Great, now enter the course's section number: ");
							break;
							
						}
					}	
					if (displayFlagger) System.out.println("No course under that ID exists! Please reenter the course ID: ");

				}
				boolean displayFlag = true;
				while (displayFlag){
					//make sure the user inputs an int
					while (!input.hasNextInt()){
						System.out.println("Bad input! Make sure your input is a number: ");
						input.next();
					}
					int cm4CourseSectionNum = input.nextInt(); 
					input.nextLine();
					//go through a for loop to make sure that the course section number exists
					for (int n = 0; n < list.size(); n++){
						if (list.get(n).getCourseID().equals(cm4CourseID) && (list.get(n).getCourseSectionNum() == cm4CourseSectionNum)){
							System.out.println("Perfect. Displaying the information for " + cm4CourseID + "'s Section Number " + cm4CourseSectionNum);
							Admin.displayInfoCourse(cm4CourseID, cm4CourseSectionNum, list);
							displayFlag = false;
						}
					}
					if (displayFlag) {
						System.out.println("That combination of course ID and course section number does not exist.");
						System.out.println("Please reenter " + cm4CourseID + "'s section number:");
					}
				}
				break;
			case "register student":
				System.out.println("All right, let's start registering a student.");
				System.out.println("Please enter the student's first name: ");
				String registerFirstName = input.nextLine();
				System.out.println("Please enter the student's last name: ");
				String registerLastName = input.nextLine();
				System.out.println("Please enter the student's username: ");
				String registerUsername = input.nextLine();
				System.out.println("Please enter the student's password: ");
				String registerPassword = input.nextLine();
				Admin.registerStudent(registerFirstName, registerLastName, registerUsername, registerPassword, studentList);
				System.out.println("Great! " + registerFirstName + " " + registerLastName + "'s account has been created!");
				break;
			case "exit":
				System.out.println("Exiting out of the Admin interface.");
				adminFlagger = false;
				
		}
	}

}
//reports

else if (cmOrReportFlagger == 2){
	boolean adminFlagger2 = true;
	String tempCommand2;
	while (adminFlagger2){	
		System.out.println("What do you want to do? Enter \"Help\" if you want the commands.");
		tempCommand2 = input.nextLine().toLowerCase();
		switch (tempCommand2) {
		case "help":
			System.out.println("Great, here is the list of commands:");
			System.out.println("----- REPORTS -----");
			System.out.println("1. View all courses. (command: \"view all\")");
			System.out.println("2. View all courses that are full. (command: \"view full\")");
			System.out.println("3. Write to a file the list of courses that are full. (command: \"write full\")");
			System.out.println("4. View the names of the students being registered in a specific course. (command: \"students in course\")");
			System.out.println("5. View the list of courses that a given student is being registered on. (command: \"courses for student\")");
			System.out.println("6. Sort courses based on the current number of student register. (command: \"sort\")");
			System.out.println("7. Exit. (command: \"exit\")");
			break;
		case "view all":
			System.out.println("Great, printing out all the courses.");
			Admin.viewAll(list);
			break;
		case "view full":
			System.out.println("Great, printing out all the full courses.");
			Admin.viewFull(list);
			break;
		case "write full":
			System.out.println("Great, writing all full courses to a file called \"FullCourses.txt\"");
			Admin.writeFull(list);
			
			break;
		case "students in course":
			System.out.println("Great, let's get started on printing out all of the students registered in a specific course.");
			boolean sicFlag = true;
			while(sicFlag){
				System.out.println("Enter the course ID: ");
				String rCourseID = input.nextLine();
				System.out.println("Enter the course section number: ");
				while (!input.hasNextInt()){
					System.out.println("Bad input! Make sure your input is a number: ");
					input.next();
				}
				int rCourseSectionNum = input.nextInt();
				input.nextLine();
				//check to see if that combination of course ID and section number exist
				for (int o = 0; o < list.size(); o++){
					if (list.get(o).getCourseID().equals(rCourseID) && (list.get(o).getCourseSectionNum()==rCourseSectionNum)){
						System.out.println("Printing out the students in this course.");
						Admin.viewStudentsInCourse(rCourseID, rCourseSectionNum, list);
						sicFlag = false;
					}
				}
				if (!sicFlag) System.out.println("That combination of course ID and course section number does not exit!");
				break;
			}
		case "courses for student":
			System.out.println("Great, let's get started on printing out all of the courses that a student is being registered on.");
			boolean cfsFlag = true;
			while (cfsFlag){
				System.out.println("Enter the student's first name: ");
				String rFirstName = input.nextLine();
				System.out.println("Enter the student's last name: ");
				String rLastName = input.nextLine();
				//check to see if the username exists
				for (int p = 0; p < studentList.size(); p++){
					if (studentList.get(p).getFirstName().equals(rFirstName) && studentList.get(p).getLastName().equals(rLastName)){
						System.out.println("Printing out the courses for " + rFirstName + " " + rLastName);
						Student tempStudent = studentList.get(p);
						Admin.viewCoursesForStudent(tempStudent, studentList, list);
						cfsFlag = false;
					}
				}
				if (!cfsFlag) System.out.println("That student username is not associated with a student!");
			}
			break;
		
		case "sort":
			System.out.println("Great, sorting the courses based on the current number of student registers.");
			Admin.sortCourses(list);
			
			break;
		case "exit":
			System.out.println("Exiting out of the Admin interface.");
			adminFlagger2 = Admin.exitAdmin();
			break;
		}
		
	}
		
		
}
	
//if student
else if (adminOrStudentFlagger == 2){
	System.out.println("Now let's get started, " + tempUsername);
	String stuCommand;
	boolean studentFlagger = true;
	while (studentFlagger){
		System.out.println("What do you want to do? Enter \"Help\" if you want the commands.");
		switch (stuCommand = input.nextLine().toLowerCase()) {
		case "help":
			System.out.println("Great, here is the list of commands:");
			System.out.println("----- COURSES MANAGEMENT -----");
			System.out.println("1. View all courses. (\"command: view all\")");
			System.out.println("2. View all courses that are not full. (\"command: view not full\")");
			System.out.println("3. Register on a course. (\"command: register course\")");
			System.out.println("4. Withdraw from a course. (\"command: withdraw course\")");
			System.out.println("5. View all courses that a student is being registered in. (\"command: view student courses\")");
			System.out.println("6. Exit.");
			break;
		case "view all":
			System.out.println("Great, printing out all the courses.");
			Student.viewAll(list);
			break;
		case "view not full":
			System.out.println("Great, printing out all the courses that are not full.");
			Student.viewNotFull(list);
			break;
		case "register course":
			String rcCourseName = "";
			int rcCourseSectionNum = 0;
			boolean courserFlag = true;
			System.out.println("Great, let's start registering for a course.");
			while (courserFlag){
				System.out.println("Enter the course's name: ");
				rcCourseName = input.nextLine();
				System.out.println("Enter the course's section number: ");
				while (!input.hasNextInt()){
					System.out.println("Bad input! Make sure your input is a number: ");
					input.next();
				}
				rcCourseSectionNum = input.nextInt();
				input.nextLine();
				//go through courses to see if the course exists
				for (int r = 0; r < list.size(); r++ ){
					if(list.get(r).getCourseName().equals(rcCourseName) && (list.get(r).getCourseSectionNum() == rcCourseSectionNum)){
						courserFlag = false;
						System.out.println("Cool, that course exists.");
					}
				}	
				if (courserFlag){
					System.out.println("That combination of course name and course section number does not exist!");
				}
			}
			boolean namerFlag = true;
			String rcFirstName;
			String rcLastName;
			while (namerFlag) {
				System.out.println("Enter your first name: ");
				rcFirstName = input.nextLine();
				System.out.println("Enter your last name: ");
				rcLastName = input.nextLine();
				//go through the students
				for (int q = 0; q < studentList.size(); q++) {
					if (studentList.get(q).getFirstName().equals(rcFirstName) && studentList.get(q).getLastName().equals(rcLastName)){
						Student tempStudent = studentList.get(q);
						Student.registerCourse(rcCourseName, rcCourseSectionNum, tempStudent, studentList, list);
						System.out.println("You are now registered for that course!");
						namerFlag = false;
						break;
					}
				}
				if(namerFlag){
					System.out.println("That combination of first name and last name does not exist!");
				}
			
			}
			break;
			
		case "withdraw course":
			boolean withdrawFlag = true;
			System.out.println("Great, let's start withdrawing you from a course.");
			while (withdrawFlag){
				//get and validate the course
				String wCourseName = "";
				int wCourseSectionNum = 0;
				boolean w1flag = true;
				while (w1flag){
					System.out.println("Enter the course's name: ");
					wCourseName = input.nextLine();
					System.out.println("Enter the course's section number: ");
					while (!input.hasNextInt()){
						System.out.println("Bad input! Make sure your input is a number: ");
						input.next();
					}
					wCourseSectionNum = input.nextInt();
					input.nextLine();
					//go through courses to see if the course exists
					for (int s = 0; s < list.size(); s++ ){
						if(list.get(s).getCourseName().equals(wCourseName) && (list.get(s).getCourseSectionNum() == wCourseSectionNum)){
							w1flag = false;
							System.out.println("Cool, that course exists.");
						}
					}	
					if (w1flag){
						System.out.println("That combination of course name and course section number does not exist!");
					}
				}
				boolean w2flag = true;
				String wFirstName;
				String wLastName;
				Student tempStudent = new Student();
				while (w2flag){
					System.out.println("Enter your first name: ");
					wFirstName = input.nextLine();
					System.out.println("Enter your last name: ");
					wLastName = input.nextLine();
					//go through the students
					for (int t = 0; t < studentList.size(); t++) {
						if (studentList.get(t).getFirstName().equals(wFirstName) && studentList.get(t).getLastName().equals(wLastName)){
							tempStudent = studentList.get(t);
							w2flag = false;
							System.out.println("Cool, your info is correct.");
							break;
						}
					}
					if (w2flag) {
						System.out.println("That combination of first name and last name does not exist!");
					}
				}
				//if the course and student exist, then add the student
				for (int u = 0; u < list.size(); u++){
					if (list.get(u).getCurrentStudents().contains(tempStudent)) {
						System.out.println("Withdrawing you from " + wCourseName);
						Student.withdrawCourse(wCourseName, wCourseSectionNum, tempStudent, list);
						withdrawFlag = false;
					}
				}
				if (!withdrawFlag){
					System.out.println("You are not enrolled in that class!");
				}
			}
			
			break;
		case "view student courses":
			boolean viewFlag = true;
			System.out.println("Great, lets begin showing you your courses.");
			Student tempStudent = new Student();
			while (viewFlag) {
				System.out.println("Enter your first name: ");
				String vFirstName = input.nextLine();
				System.out.println("Enter your last name: ");
				String vLastName = input.nextLine();
				for (int v = 0; v < studentList.size(); v++) {
					if (studentList.get(v).getFirstName().equals(vFirstName) && studentList.get(v).getLastName().equals(vLastName)) {
						tempStudent = studentList.get(v);
						viewFlag = false;
					}
				}
				if (viewFlag) {
					System.out.println("That combination of first name and last name is not associated with a student!");
				}
			}
			System.out.println("Printing out your courses...");
			Student.viewStudentCourses(tempStudent,studentList, list);
			break;
		case "exit":
			adminOrStudentFlagger = 0;
			studentFlagger = Student.exitStudent();
			break;
		}
	}
}

//FAILED ATTEMPT AT SERIALIZATION

try{

        FileOutputStream fosCourse= new FileOutputStream("Course.ser");

        ObjectOutputStream oosCourse= new ObjectOutputStream(fosCourse);

        oosCourse.writeObject(list);

        oosCourse.close();

        fosCourse.close();

}catch(Exception E){

		E.printStackTrace();

}

try{
	FileOutputStream fosStudent= new FileOutputStream("Student.ser");

	ObjectOutputStream oosStudent= new ObjectOutputStream(fosStudent);

	oosStudent.writeObject(studentList);

	oosStudent.close();

	fosStudent.close();

}catch(Exception E){
		E.printStackTrace();

}

System.out.println("Serialization Complete!");
	
}



}
	

