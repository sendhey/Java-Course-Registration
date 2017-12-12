package rs5253;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Admin extends User implements AdminInterface, Serializable{
	
	//CONSTRUCTORS
	
	//Main constructor for a base admin
	public Admin(){
		firstName = null;
		lastName = null;
		username = "Admin";
		password = "Admin001";
	}
	
	//In case an extra admin needs to be made
	public Admin(String firstName, String lastName, String username, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		
	}
	
	//METHODS
	//COURSES MANAGEMENT
	String adminCourseName;
	String adminCourseID;
	int adminMaxNumStudents;
	int adminCurrentNumStudents;
	String adminCourseInstructor;
	int adminCourseSectionNum;
	String adminCourseLocation;
	
	public static void newCourse(String adminCourseName, String adminCourseID, int adminMaxNumStudents,int adminCurrentNumStudents,String adminCourseInstructor,int adminCourseSectionNum,String adminCourseLocation, ArrayList<Course> list){	
		Course adminCourse = new Course(adminCourseName,adminCourseID, adminMaxNumStudents,adminCurrentNumStudents,adminCourseInstructor,adminCourseSectionNum,adminCourseLocation);
		list.add(adminCourse);
	}
	
	public static void deleteCourse(String courseID, int courseSectionNum, ArrayList<Course> list){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCourseID().equals(courseID) && (list.get(i).getCourseSectionNum() == courseSectionNum)){	
				list.remove(i);
			}
		}
	}
	
	//for a string edit request input
	public static void editCourse(String courseID, int courseSectionNum, String editRequestField, String editStringRequestInput, ArrayList<Course> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCourseID().equals(courseID) && (list.get(i).getCourseSectionNum() == courseSectionNum)){	
				//possible string cases	
				switch(editRequestField){
				case "name":
					list.get(i).setCourseName(editStringRequestInput);
					break;
				case "id":
					list.get(i).setCourseID(editStringRequestInput);
					break;
				case "instructor":
					list.get(i).setCourseInstructor(editStringRequestInput);
					break;
				case "location":
					list.get(i).setCourseLocation(editStringRequestInput);
					break;
				}	
			}
		}
	}
	//for an int edit request input
	public static void editCourse(String courseID, int courseSectionNum, String editRequestField, int editIntRequestInput, ArrayList<Course> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCourseID().equals(courseID) && (list.get(i).getCourseSectionNum() == courseSectionNum)){
				//possible int cases
				switch(editRequestField){
				case "max students":
					list.get(i).setMaxNumStudents(editIntRequestInput);
					break;
				case "section number":
					list.get(i).setCourseSectionNum(editIntRequestInput);
					break;
				
				}
			}
		}
	}
	
	//for a student edit request input (when editRequestField equals "current students")
	public static void editCourse(String courseID, int courseSectionNum, String editRequestField, String addOrRemove, Student editStudent, ArrayList<Student> studentList, ArrayList<Course> list) {
		if (addOrRemove.equals("add")){
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCourseID().equals(courseID) && (list.get(i).getCourseSectionNum() == courseSectionNum)) {
					//ERROR IS COMING FROM HERE
					list.get(i).getCurrentStudents().add(editStudent);
				}
			}
		}
		else if (addOrRemove.equals("remove")){
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCourseID().equals(courseID) && (list.get(i).getCourseSectionNum() == courseSectionNum)) {
					list.get(i).getCurrentStudents().remove(editStudent);
				}
			}
		}
	}
	
	public static void displayInfoCourse(String courseID, int courseSectionNum, ArrayList<Course> list){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getCourseID().equals(courseID) && (list.get(i).getCourseSectionNum() == courseSectionNum)){
				System.out.println("Course Name: " + list.get(i).getCourseName() + "; Course Instructor: " + list.get(i).getCourseInstructor() + ".");
				System.out.println("Course ID: " + list.get(i).getCourseID() + "; Course Section: " + list.get(i).getCourseSectionNum() + ".");
				System.out.println("Course Location: " + list.get(i).getCourseLocation() + ".");
				System.out.println("Capacity: " + list.get(i).getCurrentNumStudents() + " out of " + list.get(i).getMaxNumStudents() + " spaces are filled.");
			}
		}
	}
	
	
	String adminFirstName;
	String adminLastName;
	String adminUsername;
	String adminPassword;
	
	
	public static void registerStudent(String adminFirstName, String adminLastName, String adminUsername, String adminPassword, ArrayList<Student> studentList){
		Student adminStudent = new Student(adminFirstName, adminLastName, adminUsername, adminPassword);
		studentList.add(adminStudent);
	}
	
	public static void viewAll(ArrayList<Course> list){
		System.out.println("Showing you all of the courses, Admin.");
		for (int i=0; i < list.size(); i++){
			System.out.println((i+1) + ". Course Name: " + list.get(i).getCourseName() + "; Course Instructor: " + list.get(i).getCourseInstructor() + ".");
			System.out.println("Course ID: " + list.get(i).getCourseID() + "; Course Section: " + list.get(i).getCourseSectionNum() + ".");
			System.out.println("Course Location: " + list.get(i).getCourseLocation() + ".");
			System.out.println("Capacity: " + list.get(i).getCurrentNumStudents() + " out of " + list.get(i).getMaxNumStudents() + " spaces are filled.");
		}
	}
	
	
	
	public static void viewFull(ArrayList<Course> list) {
		ArrayList<Course> fullCourses = new ArrayList<Course>();
		for (int i = 0; i < list.size(); i++){
			list.get(i);
			if (list.get(i).getMaxNumStudents() == list.get(i).getCurrentNumStudents()){
				fullCourses.add(list.get(i));
			}
		}
		if (fullCourses.isEmpty()){
			System.out.println("No classes are full!");
		}
		else {
			System.out.println("The following courses are full:");
		}
		for (int i = 0; i < fullCourses.size(); i++){
			System.out.println((i+1) + ". " + fullCourses.get(i).getCourseName()+ " is full, with " + fullCourses.get(i).getCurrentNumStudents() + " out of " + fullCourses.get(i).getMaxNumStudents() + " spaces filled.");
			System.out.println("The Course ID is " + fullCourses.get(i).getCourseSectionNum() + " and the section number is " + fullCourses.get(i).getCourseSectionNum());
		}
	}
	
	public static void writeFull(ArrayList<Course> list){
		try {
		PrintWriter writer = new PrintWriter("FullClasses.txt", "UTF-8");
		for (Course c : list) {
			if(c.getCurrentStudents().size() == c.getMaxNumStudents()) {
				for (int i=0; i < list.size(); i++){
					writer.println((i+1) + ". Course Name: " + list.get(i).getCourseName() + "; Course Instructor: " + list.get(i).getCourseInstructor() + ".");
					writer.println("Course ID: " + list.get(i).getCourseID() + "; Course Section: " + list.get(i).getCourseSectionNum() + ".");
					writer.println("Course Location: " + list.get(i).getCourseLocation() + ".");
					writer.println("Capacity: " + list.get(i).getCurrentNumStudents() + " out of " + list.get(i).getMaxNumStudents() + " spaces are filled.");
				}
			}
		}
		writer.close();
		} catch (IOException e) {
			System.out.print("File not found.");
		}
	}
	
	
	public static void viewStudentsInCourse(String courseID, int courseSectionNum, ArrayList<Course> list){
		for (int i = 0; i < list.size(); i++){
			if(list.get(i).getCourseID().equals(courseID) && (list.get(i).getCourseSectionNum() == courseSectionNum)){
				for (int j = 0; j < list.get(i).currentStudents.size(); j++){
					System.out.println((i+1) + ". Name: " + list.get(i).getCurrentStudents(j).getFirstName() + list.get(i).getCurrentStudents(j).getLastName());
				}
			}
		}
	}
	
	public static void viewCoursesForStudent(Student tempStudent, ArrayList<Student> studentList, ArrayList<Course> list){
		//go through the courses
		for (int i = 0; i < list.size(); i++){
			//if the course contains tempstudent
			if (list.get(i).getCurrentStudents().contains(tempStudent)){
				//print out the course
				System.out.println("Course Name: " + list.get(i).getCourseName() + "; Course Instructor: " + list.get(i).getCourseInstructor() + ".");
				System.out.println("Course ID: " + list.get(i).getCourseID() + "; Course Section: " + list.get(i).getCourseSectionNum() + ".");
				System.out.println("Course Location: " + list.get(i).getCourseLocation() + ".");
				System.out.println("Capacity: " + list.get(i).getCurrentNumStudents() + " out of " + list.get(i).getMaxNumStudents() + " spaces are filled.");
				
			}		
			
		}
	}
	
	//CITATION: https://www.tutorialspoint.com/java/java_using_comparator.htm
	public static void sortCourses(ArrayList<Course> list) {
		
		Collections.sort(list, new Comparator<Course>() {
			
			@Override
			public int compare(Course c1, Course c2) {
				return c1.currentStudents.size() - c2.currentStudents.size();
			}
		});
	}

	
	public static boolean exitAdmin(){
		return false;
	}


}


	

