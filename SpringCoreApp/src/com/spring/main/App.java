
package com.spring.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.model.Address;
import com.spring.model.Customer;
import com.spring.model.PolicyHolder;
import com.spring.service.CustomerService;
import com.spring.service.PolicyHolderService;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		CustomerService customerService = context.getBean(CustomerService.class);
		PolicyHolderService policyHolderService = context.getBean(PolicyHolderService.class);
		PolicyHolder policyHolder = context.getBean(PolicyHolder.class);
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("--------------OPS MENU------------");
			System.out.println("1. Create Customer Table");
			System.out.println("2. Insert Customer");
			System.out.println("3. Update Customer");
			System.out.println("4. Get Customer by Id");
			System.out.println("5. Delete Customer");
			System.out.println("6. Get All Customers");
			System.out.println("7. Create PolicyHolder account with address");
			System.out.println("8. Get all PolicyHolder with Address Info");
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
					System.out.println("Enter Customer ID to update");
					try {
						Customer customer =  customerService.getById(sc.nextInt());
						System.out.println("Existing Customer " +customer);
						System.out.println("Enter new Name or press 00 to skip");
						sc.nextLine();
						String nameVal = sc.nextLine();
						if(!nameVal.equals("00")) {
							customer.setName(nameVal);
						}
						System.out.println("Enter new City or press 00 to skip");
						String cityVal = sc.next();
						if(!cityVal.equals("00")) {
							customer.setCity(cityVal);
						}
						
						customerService.update(customer);
						System.out.println("Customer record Updated");
						
					}
					catch(Exception e) {
						System.out.println("Invalid ID Given!!!Could not fetch Record");
					}
					break;
	
				case 4:
					System.out.println("Enter Customer ID");
					try {
						Customer customer =  customerService.getById(sc.nextInt());
						System.out.println(customer);
					}
					catch(Exception e) {
						System.out.println("Invalid ID Given!!!Could not fetch Record");
					}
					
					break;
	
				case 5:
					System.out.println("Delete");
					break;
				case 6:
					System.out.println("Get All Customers");
					List<Customer> list =  customerService.getAllv2();
					list.stream().forEach(e->System.out.println(e));
					//list.stream().forEach(System.out :: println);
					break;
				case 7:
					
					System.out.println("Enter name:");
					sc.nextLine();
					policyHolder.setName(sc.nextLine());
					
					System.out.println("Enter Pan:");
					policyHolder.setPanNo(sc.next());
					System.out.println("Enter street:");
					Address address = new Address(); 
					sc.nextLine();
					address.setStreet(sc.nextLine());
					System.out.println("City: ");
					address.setCity(sc.next());
					sc.nextLine();
					System.out.println("State: ");
					address.setState(sc.nextLine());
					
					policyHolderService.insert(policyHolder,address);
					System.out.println("Policy holder record created....");
					break;
				case 8:
					List<PolicyHolder> listPh =  policyHolderService.getAllWithAddres();
					listPh.stream().forEach(e->{
						System.out.println(e.getName() + "**" 
								+ e.getPanNo() + "**" 
								+ e.getAddress().getStreet() + "**"
								+e.getAddress().getCity() + "**" 
								+e.getAddress().getState());
					});
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
