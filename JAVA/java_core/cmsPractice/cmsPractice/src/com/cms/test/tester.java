package com.cms.test;

import java.time.LocalDate;
import java.util.Scanner;

import com.cms.core.Customer;
import com.cms.core.ServicePlan;
import com.cms.custom_exception.CMSHandlingException;
import com.cms.service.CMSService;
import com.cms.service.CMSServiceImpl;

public class tester {

	public static void main(String[] args) throws CMSHandlingException {
		
		try(Scanner sc = new Scanner(System.in))
		{
			boolean flag=false;
			
			//upcasting: direct call
			CMSService service = new CMSServiceImpl();
			
			while(!flag)
			{
				try {
					System.out.println("enter choice :\n1.Register Customer\n2.Display all customer\n3.Sign-In");
					switch(sc.nextInt())
					{
					case 1: {
						
							service.registerCustomer("Raj", "Sharma", "amit1@gmail.com", "amit@123", 1000,LocalDate.parse("1990-05-12"),ServicePlan.BASIC);
						}
					break;
					
					case 2:{
						System.out.println("Details of all customer");
						service.display();
					}
					break;
					
					
					//sign-in
					case 3:{
						System.out.println("Sign-in");
						//here the service reurns Customer so it is returned to the Customer
						System.out.println("Enter email and password");
						Customer cust = service.signin(sc.next(), sc.next());
						System.out.println("Signed-in, Welcome " + cust.getFirstName());
					}
					break;
					
					
					//change password
					case 4: {
						System.out.println("Change your password");
						System.out.println("Enter email and old password");
						service.changePassword(sc.next(), sc.next(), sc.next());
					}
					
					
					//un-subscribe
					case 5:{
						System.out.println("Unsubscribe");
						System.out.println("Enter email to unsubscribe");
						service.unsubscribeCustomer(sc.next());
					}
					
						
					}
				}catch(Exception e){
					throw new CMSHandlingException("Invalid");
				}
				
			}
		}
}}
