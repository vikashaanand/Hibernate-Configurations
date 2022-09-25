package com.learn.learn_hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.learn.learn_hibernate.entities.Address;
import com.learn.learn_hibernate.entities.Student;

public class App {
	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		if (sessionFactory.isOpen()) {

			Session session = sessionFactory.openSession();

			saveToDB(session);
			
//			Student student = (Student)session.get(Student.class, 3);
//			System.out.println(student.getRollNo() + ". " + student.getName());
//			
//			System.out.println(student.getAddresses().size());
			
			session.close();
		}
	}

	private static void saveToDB(Session session) {

		Student s1 = new Student();
		s1.setName("Vikash Kumar");

		Student s2 = new Student();
		s2.setName("Neha Nanhi");

		Student s3 = new Student();
		s3.setName("Anju Kumari");

		Address a1 = new Address();
		a1.setVillage("Ranchi");

		Address a2 = new Address();
		a2.setVillage("Munger");

		Address a3 = new Address();
		a3.setVillage("Ramgarh");

		List<Address> s1Add = new ArrayList<Address>();
		s1Add.add(a1);
		s1Add.add(a2);
		s1Add.add(a3);

		List<Address> s2Add = new ArrayList<Address>();
		s2Add.add(a1);
		s2Add.add(a2);

		List<Address> s3Add = new ArrayList<Address>();
		s3Add.add(a1);
		s3Add.add(a3);

		List<Student> students = new ArrayList<Student>();
		students.add(s1);
		students.add(s2);
		students.add(s3);

		s1.setAddresses(s1Add);
		s2.setAddresses(s2Add);
		s3.setAddresses(s3Add);

		a1.setStudents(students);

		Transaction transaction = session.beginTransaction();

		session.save(s1);
		session.save(s2);
		session.save(s3);

		session.save(a1);
		session.save(a2);
		session.save(a3);
		
		s3.setName("Radhika Kumari"); //Save dto DB

		transaction.commit();
		
		s3.setName("Holi");	//Not saved in DB

	}
}
