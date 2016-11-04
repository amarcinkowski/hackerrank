package io.github.amarcinkowski.hackerrank.advanced;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface FamilyBudget {
	String userRole() default "GUEST";
	int budgetLimit();
}

class FamilyMember {
	@FamilyBudget(userRole="SENIOR", budgetLimit = 100)
	public void seniorMember(Integer budget, Integer moneySpend) {
		System.out.println("Senior Member");
		System.out.println("Spend: " + moneySpend);
		System.out.println("Budget Left: " + (budget - moneySpend));
	}

	@FamilyBudget(userRole="JUNIOR", budgetLimit = 50)
	public void juniorUser(Integer budget, Integer moneySpend) {
		System.out.println("Junior Member");
		System.out.println("Spend: " + moneySpend);
		System.out.println("Budget Left: " + (budget - moneySpend));
	}
}


public class Annotations extends Solution {
	public Annotations(String name) {
		super(name);
	} /*
		 * overloaded log:
		 * 
		 * private void log(String msg) {} /*
		 */


	Scanner in;

	public void execute() {
		log("execute");
		in = new Scanner(System.in);

		int testCases = Integer.parseInt(in.nextLine());
		log("cases: " + testCases);
		while (testCases > 0) {
			String role = in.next();
			int spend = in.nextInt();
			log(role + spend);
			try {
				Class<FamilyMember> annotatedClass = FamilyMember.class;
				Method[] methods = annotatedClass.getMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(FamilyBudget.class)) {
						FamilyBudget family = method.getAnnotation(FamilyBudget.class);
						String userRole = family.userRole();
						int budgetLimit = family.budgetLimit(); // ~~Complete this line~~;
						if (userRole.equals(role)) {
							if (spend <= budgetLimit /*~~Complete this line~~ */) {
								log(method.getName() + role + " " + budgetLimit + " " + spend);
								method.invoke(FamilyMember.class.newInstance(), budgetLimit, spend);
//								new FamilyMember().getMethod(method.getName(),Integer.class,Integer.class).invoke(budgetLimit,spend);
							} else {
								System.out.println("Budget Limit Over");
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			testCases--;
		}
	}

	// hr:
//	 public static void main(String[] args) {new Solution().execute();}
}
