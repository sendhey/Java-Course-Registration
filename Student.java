package rs5253;

import java.io.Serializable;
import java.util.ArrayList;

class Student extends User implements StudentInterface, Serializable {

	
	//CONSTRUCTORS
	Student(){
	}
	
	//Main constructor (which will  be accessed through the admin class)
	Student (String firstName, String lastName, String username, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	} 
	
	
	//COURSE MANAGEMENT 
	
	public static void viewAll(ArrayList<Course> list){
		System.out.println("Showing you all of the courses, Student.");
		for (int i=0; i < list.size(); i++){
			System.out.println((i+1) + ". Course Name: " + list.get(i).getCourseName() + "; Course Instructor: " + list.get(i).getCourseInstructor() + ".");
			System.out.println("Course ID: " + list.get(i).getCourseID() + "; Course Section: " + list.get(i).getCourseSectionNum() + ".");
			System.out.println("Course Location: " + list.get(i).getCourseLocation() + ".");
			System.out.println("Capacity: " + list.get(i).getCurrentStudents().size() + " out of " + list.get(i).getMaxNumStudents() + " spaces are filled.");
		}
	}
	
	public static void viewNotFull(ArrayList<Course> list){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCurrentNumStudents() != list.get(i).getMaxNumStudents()) {
				System.out.println("Course Name: " + list.get(i).getCourseName() + "; Course Instructor: " + list.get(i).getCourseInstructor() + ".");
				System.out.println("Course ID: " + list.get(i).getCourseID() + "; Course Section: " + list.get(i).getCourseSectionNum() + ".");
				System.out.println("Course Location: " + list.get(i).courseLocation + ".");
				System.out.println("Capacity: " + list.get(i).getCurrentNumStudents() + " out of " + list.get(i).getMaxNumStudents() + " spaces are filled.");
			}
		}
	}
	
	public static void registerCourse(String courseName, int courseSectionNum, Student tempStudent, ArrayList<Student> studentList, ArrayList<Course> list){
		for (int i = 0; i < list.size(); i++){
			if (list.get(i).getCourseName().equals(courseName) && (list.get(i).getCourseSectionNum() == courseSectionNum)){	
				list.get(i).getCurrentStudents().add(tempStudent);
			}
		}
	}
	
	public static void withdrawCourse(String courseName, int courseSectionNum, Student tempStudent, ArrayList<Course> list){
		for (int i = 0; i < list.size(); i++){
			if (list.get(i).getCourseName().equals(courseName) && (list.get(i).getCourseSectionNum() == courseSectionNum)){
				list.get(i).getCurrentStudents().remove(tempStudent);
			}
		}
	}
	
	public static void viewStudentCourses(Student tempStudent,ArrayList<Student> studentList, ArrayList<Course> list){
		for (int i = 0; i < list.size(); i++){
			if (list.get(i).getCurrentStudents().contains(tempStudent)){
				System.out.println((i+1) + ". Course Name: " + list.get(i).getCourseName() + "; Course Instructor: " + list.get(i).getCourseInstructor() + ".");
				System.out.println("Course ID: " + list.get(i).getCourseID() + "; Course Section: " + list.get(i).getCourseSectionNum() + ".");
				System.out.println("Course Location: " + list.get(i).getCourseLocation() + ".");
			}
		}
	}
	
	public static boolean exitStudent(){
		return false;
	}
	
}
