package join_table.bidirectional.app;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.utils.HibernateUtil;

import join_table.bidirectional.Course;
import join_table.bidirectional.Discipline;

public class App {

	public static void main(String[] args) {
		App app = new App();

		app.insertion();    // insertion can also be done using insertion.txt file commands
                            // do not forget to update mapping class in hibernate.cfg.xml file

		//app.loadCourse();
		//app.loadDiscipline();
		//app.deleteDiscipline();
		//app.deleteCourse();
	}

	public void insertion() {

		Discipline discipline1 = new Discipline(1, "Software Engineering", 30);
		Discipline discipline2 = new Discipline(2, "Database", 30);
		Discipline discipline3 = new Discipline(3, "Calculus", 30);

		Course course1 = new Course(2142, "Computer Science", 1500);
		Course course2 = new Course(2143, "Computer Engineering", 1500);

		course1.addDiscipline(discipline1);
	    course1.addDiscipline(discipline2);
	    course2.addDiscipline(discipline2);
	    course2.addDiscipline(discipline3);

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

	public void loadDiscipline() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Discipline discipline = (Discipline) session.load(Discipline.class, 2);
		System.out.println("Discipline name: " + discipline.getName());

		discipline.getCourses().stream()
		                       .forEach(course -> System.out.println("Course name: " + course.getName()));

		transaction.commit();
		session.close();
	}

	public void deleteDiscipline() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
			
		Discipline discipline = (Discipline) session.load(Discipline.class, 2);

		// Before delete the discipline, remove the reference from every course.
		Iterator<Course> iterator = discipline.getCourses().iterator();
		Course course;

		while(iterator.hasNext()) {
			course = iterator.next();
			course.getDisciplines().remove(discipline);
			session.update(course);
		}

		session.delete(discipline);

		transaction.commit();
		session.close();
	}

	public void deleteCourse() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Course course = (Course) session.load(Course.class, 2142);

		// Before delete the course, remove the reference from every discipline.
		Iterator<Discipline> iterator = course.getDisciplines().iterator();
		Discipline discipline;

		while(iterator.hasNext()) {
			discipline = iterator.next();
			discipline.getCourses().remove(course);
			session.update(discipline);
		}

		session.delete(course);

		transaction.commit();
		session.close();
	}

}