package intermediate_entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@Column(name = "CourseId")
    private int courseId;
	
	@Column(name = "Name")
    private String name;
	
	@Column(name = "TotalCredits")
    private int totalCredits;
	
	@OneToMany(mappedBy = "course")
    private Set<DisciplineCourse> discCourse = new HashSet<DisciplineCourse>();

	public Course() {}

    public Course(int courseId, String name, int totalCredits) {
    	this.courseId = courseId; 
        this.name = name;
        this.totalCredits = totalCredits;
    }

	public int getCourseId() {
		return courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	public Set<DisciplineCourse> getDiscCourse() {
		return discCourse;
	}

	public void setDiscCourse(Set<DisciplineCourse> discCourse) {
		this.discCourse = discCourse;
	}

}