import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementierung des Database-Interfaces, die Objekte über Listen des Java
 * Collection-Framework direkt im Speicher verwaltet
 */
public class InMemoryDatabase implements Database {

	private List<Student> students = new LinkedList<>();
	private List<Course> courses = new LinkedList<>();

	@Override
	public void insertStudent(Student student) {
		// Aufgabe
		this.students.add(student);
	}

	@Override
	public void insertStudents(List<Student> students) {
		// Aufgabe
		this.students.addAll(students);
	}

	@Override
	public int countStudents() {
		// Aufgabe
		return this.students.size();
	}

	@Override
	public List<Student> getStudents() {
		// Aufgabe
		return new ArrayList<>(this.students);
	}

	@Override
	public List<Student> getStudentsBornAfter(Calendar date) {
		// Aufgabe
		List<Student> foundStudents = new ArrayList<>();
		for (Student student : this.students) {
			if (student.getDateOfBirth().after(date)) {
				foundStudents.add(student);
			}
		}
		return foundStudents;
	}

	@Override
	public List<Student> getGoodStudents(double gradeThreshold) {
		// Aufgabe
		List<Student> foundStudents = new ArrayList<>();
		for (Student student : this.students) {
			if (student.getAverageGrade() < gradeThreshold) {
				foundStudents.add(student);
			}
		}
		return foundStudents;
	}

	@Override
	public List<Student> getGoodStudentsOrderedByGrade(double gradeThreshold) {
		// Aufgabe
		List<Student> goodStudents = this.getGoodStudents(gradeThreshold);
		goodStudents.sort(new GradeComparator());
		return goodStudents;
	}

	@Override
	public List<Student> getStudentsAttendingCourse(Course course) {
		// Aufgabe
		List<Student> foundStudents = new ArrayList<>();
		for (Student student : this.students) {
			if (student.getAttendedCourses().contains(course)) {
				foundStudents.add(student);
			}
		}
		return foundStudents;
	}

	@Override
	public void insertCourse(Course course) {
		// Aufgabe
		this.courses.add(course);
	}

	@Override
	public List<Course> getCourses() {
		// Aufgabe
		return new ArrayList<>(this.courses);
	}

	@Override
	public List<Course> getCoursesInTerm(Term term) {
		// Aufgabe
		List<Course> foundCourses = new ArrayList<>();
		for (Course course : this.courses) {
			if (course.getTerm() == term) {
				foundCourses.add(course);
			}
		}
		return foundCourses;
	}

	@Override
	public int countCourses() {
		// Aufgabe
		return this.courses.size();
	}
}
