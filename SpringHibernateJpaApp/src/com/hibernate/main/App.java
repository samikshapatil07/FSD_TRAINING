package com.hibernate.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hibernate.main.dto.ContainerDto;
import com.hibernate.main.model.Course;
import com.hibernate.main.model.Learner;
import com.hibernate.main.service.CourseService;
import com.hibernate.main.service.LearnerService;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		LearnerService learnerService =  context.getBean(LearnerService.class);
		CourseService courseService = context.getBean(CourseService.class);
		
		Scanner sc = new Scanner (System.in);
		while(true) {
			System.out.println("_________ Learner Menu_________");
			System.out.println("1.Add Learner");
			System.out.println("2.Get All Learner");
			System.out.println("3.Get Learner By ID");
			System.out.println("4.Edit Learner");
			System.out.println("5.Delete Learner By ID");
			System.out.println("6. Fetch Course by ID");
			System.out.println("0. Exit");
			System.out.println("Enter your choice:");

			int choice=sc.nextInt();
			
			if (choice == 0) break;
			
			switch(choice) {
			case 1: //add learner
				learnerService.insert("samiksha", "samiksha@gmail.com", "201020031");
				System.out.println("Record Inserted...");
				break;
			case 2://get all learners
				List<Learner> list=learnerService.getAll();
				list.stream().forEach(l->System.out.println(l));
				break;
			case 3:// get learner by id
				System.out.println("Enter ID:");
				try {
					Learner learner = learnerService.getById(sc.nextInt());
				}
				catch(RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4://edit learner
			    System.out.println("Enter ID to update:");
			    try {
			        int id = sc.nextInt();
			        sc.nextLine(); 
			        Learner learner = learnerService.getById(id); 

			        System.out.println("Enter new name:");
			        String name = sc.nextLine();

			        System.out.println("Enter new email:");
			        String email = sc.nextLine();

			        System.out.println("Enter new contact:");
			        String contact = sc.nextLine();

			        learner.setName(name);
			        learner.setEmail(email);
			        learner.setContact(contact);

			        learnerService.updateLearner(learner);
			        System.out.println("Record Updated Successfully.");
			    } catch (RuntimeException e) {
			        System.out.println(e.getMessage());
			    }
				break;
			case 5://delete learner by id
				System.out.println("Enter iD:");
				try {
					learnerService.deleteLearner(sc.nextInt());
					System.out.println("Record Deleted");
				}
				catch(RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.out.println("Enter ID");
				int id = sc.nextInt();
				try {
					ContainerDto dto = courseService.getById(id);
				 
					 System.out.println(dto);
				}
				catch(RuntimeException e) {
					System.out.println(e.getMessage());
				}	
				break; 
			default:
			 	break; 
			}
			
		}
		sc.close();
		context.close();
	}
}