package best.practice.bidirectional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "discipline_course")
public class DisciplineCourse {

	@Id
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Duration")
	private int duration;
	
	@Column(name = "Title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "CourseId", nullable = false, updatable = false)
	private Course course;

	@ManyToOne
	@JoinColumn(name = "DiscIdId", nullable = false, updatable = false)
	private Discipline discipline;
	
	public DisciplineCourse() {}
	
	public DisciplineCourse(int id, int duration, String title) {
		this.id = id;
		this.duration = duration;
		this.title = title;
	}
	
	public int getId() {
		return id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
		course.getDiscCourse().add(this);
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
		discipline.getDiscCourse().add(this);
	}

}