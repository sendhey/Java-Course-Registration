package rs5253;

import java.util.ArrayList;

interface AdminInterface {
	
	//COURSES MANAGEMENT
	
	public static void newCourse(String adminCourseName, String adminCourseID, int adminMaxNumStudents,int adminCurrentNumStudents,String adminCourseInstructor,int adminCourseSectionNum,String adminCourseLocation, ArrayList<Course> list) {
	}
	
	public static void deleteCourse(String courseID, int courseSectionNum, ArrayList<Course> list) {
	}
	
	public static void editCourse(String courseID, int courseSectionNum, String editRequestField, String editStringRequestInput, ArrayList<Course> list){
		
	}
	
	public static void editCourse(String courseID, int courseSectionNum, String editRequestField, int editIntRequestInput, ArrayList<Course> list) {
		
	}
	
	public static void editCourse(String courseID, int courseSectionNum, String editRequestField, String addOrRemove, Student editStudent, ArrayList<Course> list) {
	
	}
	
	public static void displayInfoCourse(String courseID, int courseSectionNum, ArrayList<Course> list){
		
	}
	
	public static void registerStudent(String adminFirstName, String adminLastName, String adminUsername, String adminPassword, ArrayList<Student> studentList, ArrayList<Course> list){
		
	}
	
	//REPORTS
	
	public static void viewAll(ArrayList<Course> list){
		
	}
	
	public static void viewFull(ArrayList<Course> list){
		
	}
	
	public static void writeFull(ArrayList<Course> list){
		
	}
	
	public static void viewStudentsInCourse(String courseName, int courseSectionNum, ArrayList<Course> list){
		
	}
	
	public static void viewCoursesForStudent(String studentFirstName, String studentLastName, ArrayList<Student> studentList){
		
	}
	
	//does this want us to print out the sorted courses or change the arraylist to sort them
	public static void sortCourses(ArrayList<Course> list){
		
	}
	
	public static boolean exitAdmin(){
		return false;
	}

}
	