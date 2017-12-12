package rs5253;

import java.util.ArrayList;

public interface StudentInterface {
	
	//COURSE MANAGEMENT 
	
		public static void viewAll(ArrayList<Course> list){
			
		}
		
		public static void viewNotFull(ArrayList<Course> list){
			
		}
		
		public static void registerCourse(String courseName, int courseSectionNum, Student tempStudent, ArrayList<Student> studentList, ArrayList<Course> list){
			
		}
		
		public static void withdrawCourse(String courseName, int courseSectionNum, Student tempStudent, ArrayList<Course> list){
			
		}
		
		public static void viewStudentCourses(Student tempStudent, ArrayList<Student> studentList, ArrayList<Course> list){
			
		}
		
		public static boolean exitStudent(){
			return false;
		}
}
