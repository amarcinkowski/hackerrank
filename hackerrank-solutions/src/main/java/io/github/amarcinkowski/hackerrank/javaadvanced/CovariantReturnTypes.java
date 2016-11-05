package io.github.amarcinkowski.hackerrank.javaadvanced;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;


class Flower {
	public String whatsYourName() {
		return "I have many names and types";
	}
}

class Jasmine extends Flower {
	@Override
	public String whatsYourName() {
		return "Jasmine";
	}
}

class Lily extends Flower {
	@Override
	public String whatsYourName() {
		return "Lily";
	}
}

class Lotus extends Flower {
	@Override
	public String whatsYourName() {
		return "Lotus";
	}
}

class State {
	public Flower yourNationalFlower() {
		return new Flower();
	}
}

class WestBengal extends State {
	@Override
	public Flower yourNationalFlower() {
		return new Jasmine();
	}
}

class Karnataka extends State {
	@Override
	public Flower yourNationalFlower() {
		return new Lotus();
	}
}

class AndhraPradesh extends State {
	@Override
	public Flower yourNationalFlower() {
		return new Lily();
	}
}

public class CovariantReturnTypes extends Solution { // hr (not public, no constructor): class CovariantReturnTypes 
	public CovariantReturnTypes(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private void log(String msg) {} /*
		 */

	Scanner scanner;

	public void execute() {
		log("execute");
		scanner = new Scanner(System.in);
		String s = scanner.next();
		try {
			String pack = this.getClass().getPackage().getName() + ".";
			if (pack != ".") {
				s = pack + s;
			}
			State state = (State) Class.forName(s).newInstance();
			String flower = state.yourNationalFlower().getClass().getName();
			flower = flower.replace(pack, "");
			log(flower);
			System.out.println(flower);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// hr:
	//	public static void main(String[] args) {new CovariantReturnTypes().execute();}
}
