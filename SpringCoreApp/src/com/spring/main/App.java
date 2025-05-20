package com.spring.main;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.spring.service.CustomerService;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		CustomerService customerService = context.getBean(CustomerService.class);
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("--------------OPS MENU------------");
			System.out.println("1. Create Customer Table");
			System.out.println("2. Insert Customer");
			System.out.println("3. Update Customer");
			System.out.println("4. Get Customer by Id");
			System.out.println("5. Delete Customer");
			System.out.println("6. Get All Customers");
			System.out.println("0. EXIT");
			int choice = sc.nextInt();
			if (choice == 0) {
				System.out.println("Exiting..... Thank You!!!");
				break; // <-- comes out of while loop
			}
			switch (choice) {
				case 1:
					/* Create Customer table */
					customerService.createTable();
					System.out.println("Table created ");
					break; 
				case 2:
					System.out.println("Enter Name: ");
					String name = sc.next();
					System.out.println("Enter City: ");
					String city = sc.next();
					try {
						customerService.insertCustomer(name, city);
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}
					
					break;
				case 3:
					break;
	
				case 4:
					System.out.println("Get by Id");
					break;
	
				case 5:
					System.out.println("Delete");
					break;
				case 6:
					System.out.println("Get All");
					break;
	
				default:
					System.out.println("INVALID CHOICE");
					break;
				}
			}
		sc.close();
		context.close();
	}
}