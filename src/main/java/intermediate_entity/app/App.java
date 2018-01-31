package intermediate_entity.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.utils.HibernateUtil;
import intermediate_entity.Course;
import intermediate_entity.Discipline;
import intermediate_entity.DisciplineCourse;

public class App {

	public static void main(String[] args) {
		App app = new App();

		app.insertion();     // insertion can also be done using insertion.txt file commands
                             // do not forget to update mapping class in hibernate.cfg.xml file
	}

	public void insertion() {

		Discipline discipline1 = new Discipline(1, "Software Engineering", 30);
		Discipline discipline2 = new Discipline(2, "Database", 30);
		Discipline discipline3 = new Discipline(3, "Calculus", 30);

		Course course1 = new Course(2142, "Computer Science", 1500);
		Course course2 = new Course(2143, "Computer Engineering", 1500);

		DisciplineCourse discCourse1 = new DisciplineCourse(10, "Bacharel", course1, discipline1);
		DisciplineCourse discCourse2 = new DisciplineCourse(10, "Bacharel", course1, discipline2);
		DisciplineCourse discCourse3 = new DisciplineCourse(20, "Master", course2, discipline2);
		DisciplineCourse discCourse4 = new DisciplineCourse(20, "Master", course2, discipline3);

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

}