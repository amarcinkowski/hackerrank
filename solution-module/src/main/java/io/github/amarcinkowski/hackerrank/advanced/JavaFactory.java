package io.github.amarcinkowski.hackerrank.advanced;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;

public class JavaFactory extends Solution {
	public JavaFactory(String name) {
		super(name);
	} /*
		 * overloaded log:
		 * 
		 * private void log(String msg) {} /*
		 */

	interface Food {
		public String getType();
	}

	class Pizza implements Food {
		public String getType() {
			return "Someone ordered a Fast Food!";
		}
	}

	class Cake implements Food {

		public String getType() {
			return "Someone ordered a Dessert!";
		}
	}

	class FoodFactory {
		public Food getFood(String order) {
			if (order.equals("pizza")) {
				return new Pizza();
			} else if (order.equals("cake")) {
				return new Cake();
			}
			return null;
		}
	}

	Scanner scanner;

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		// creating the factory
		FoodFactory foodFactory = new FoodFactory();

		// factory instantiates an object
		Food food = foodFactory.getFood(scanner.nextLine());

		System.out.println("The factory returned " + food.getClass());
		System.out.println(food.getType());
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
