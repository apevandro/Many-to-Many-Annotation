package best.practice.bidirectional.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import best.practice.bidirectional.Course;
import best.practice.bidirectional.Discipline;
import best.practice.bidirectional.DisciplineCourse;

import hibernate.utils.HibernateUtil;

public class App {

	public static void main(String[] args) {
		App app = new App();

		app.insertion();     // insertion can also be done using insertion.txt file commands
                             // do not forget to update mapping class in hibernate.cfg.xml file

		//app.loadCourse();
		//app.loadDiscipline();
	}

	public void insertion() {

		Discipline discipline1 = new Discipline(1, "Software Engineering", 30);
		Discipline discipline2 = new Discipline(2, "Database", 30);
		Discipline discipline3 = new Discipline(3, "Calculus", 30);

		Course course1 = new Course(2142, "Computer Science", 1500);
		Course course2 = new Course(2143, "Computer Engineering", 1500);

		DisciplineCourse discCourse1 = new DisciplineCourse(1, 10, "Bacharel");
		DisciplineCourse discCourse2 = new DisciplineCourse(2, 10, "Bacharel");
		DisciplineCourse discCourse3 = new DisciplineCourse(3, 20, "Master");
		DisciplineCourse discCourse4 = new DisciplineCourse(4, 20, "Master");

		discCourse1.setCourse(course1);
		discCourse1.setDiscipline(discipline1);
		
		discCourse2.setCourse(course1);
		discCourse2.setDiscipline(discipline2);
		
		discCourse3.setCourse(course2);
		discCourse3.setDiscipline(discipline2);
		
		discCourse4.setCourse(course2);
		discCourse4.setDiscipline(discipline3);
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(discipline1);
		session.save(discipline2);
		session.save(discipline3);
		session.save(course1);
		session.save(course2);
		session.save(discCourse1);
		session.save(discCourse2);
		session.save(discCourse3);
		session.save(discCourse4);
		transaction.commit();
		session.close();
	}
	
	public void loadCourse() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Course course = (Course) session.load(Course.class, 2142);
		System.out.println("Course name: " + course.getName());

		course.getDiscCourse().stream()
		                      .forEach(discCourse -> System.out.println("Discipline name: " + discCourse.getDiscipline().getName()));

		transaction.commit();
		session.close();
	}

	public void loadDiscipline() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Discipline discipline = (Discipline) session.load(Discipline.class, 2);
		System.out.println("Discipline name: " + discipline.getName());

		discipline.getDiscCourse().stream()
		                          .forEach(discCourse -> System.out.println("Course name: " + discCourse.getCourse().getName()));

		transaction.commit();
		session.close();
	}

}