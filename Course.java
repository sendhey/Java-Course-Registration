package rs5253;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Course implements Serializable{
	//Basic class for all the courses
	String courseName;
	String courseID;
	int maxNumStudents;
	int currentNumStudents;
	
	//CHECK TO MAKE SURE THIS IS OK
	ArrayList<Student> currentStudents;
	String courseInstructor;
	int courseSection;
	String courseLocation;	

	Course(){
	}
	
	Course(String courseName, String courseID, int maxNumStudents,int currentNumStudents,String courseInstructor,int courseSectionNum,String courseLocation){
		this.courseName = courseName;
		this.courseID = courseID;
		this.maxNumStudents = maxNumStudents;
		this.currentNumStudents = currentNumStudents;
		ArrayList<Student> currentStudents = new ArrayList<Student>();
		this.currentStudents = currentStudents;
		this.courseInstructor = courseInstructor;
		this.courseSection = courseSectionNum;
		this.courseLocation = courseLocation;
	}	
	
	//Getters and setters
	public String getCourseName() {
		return courseName;
	}

	public String getCourseID() {
		return courseID;
	}
	
	public int getMaxNumStudents() {
		return maxNumStudents;
	}

	public int getCurrentNumStudents() {
		return currentNumStudents;
	}

	public ArrayList<Student> getCurrentStudents() {
		return currentStudents;
	}
	
	//Overloaded method
	public Student getCurrentStudents (int i) {
		return currentStudents.get(i);
	}

	public String getCourseInstructor() {
		return courseInstructor;
	}

	public int getCourseSectionNum() {
		return courseSection;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void setMaxNumStudents(int maxNumStudents) {
		this.maxNumStudents = maxNumStudents;
	}

	public void setCurrentNumStudents(int currentNumStudents) {
		this.currentNumStudents = currentNumStudents;
	}

	public void setCurrentStudents(ArrayList<Student> currentStudents) {
		this.currentStudents = currentStudents;
	}

	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public void setCourseSectionNum(int courseSectionNum) {
		this.courseSection = courseSectionNum;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}
	
	

	
}
