package join_table.unidirectional.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.utils.HibernateUtil;

import join_table.unidirectional.Course;
import join_table.unidirectional.Discipline;

public class App {

	public static void main(String[] args) {
		App app = new App();

		app.insertion();    // insertion can also be done using insertion.txt file commands
                            // do not forget to update mapping class in hibernate.cfg.xml file
		
		//app.loadCourse();
		//app.deleteCourse();
	}

	public void insertion() {

		Discipline discipline1 = new Discipline(1, "Software Engineering", 30);
		Discipline discipline2 = new Discipline(2, "Database", 30);
		Discipline discipline3 = new Discipline(3, "Calculus", 30);

		Course course1 = new Course(2142, "Computer Science", 1500);
		Course course2 = new Course(2143, "Computer Engineering", 1500);

		course1.getDisciplines().add(discipline1);
		course1.getDisciplines().add(discipline2);
		course2.getDisciplines().add(discipline2);
		course2.getDisciplines().add(discipline3);

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(course1);
		session.save(course2);

		transaction.commit();
		session.close();
	}

	public void loadCourse() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Course course = (Course) session.load(Course.class, 2142);
		System.out.println("Course name: " + course.getName());

		course.getDisciplines().stream()
		                       .forEach(discipline -> System.out.println("Discipline name: " + discipline.getName()));

		transaction.commit();
		session.close();
	}

	public void deleteCourse() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Course course = (Course) session.load(Course.class, 2142);

		session.delete(course);

		transaction.commit();
		session.close();
	}

}