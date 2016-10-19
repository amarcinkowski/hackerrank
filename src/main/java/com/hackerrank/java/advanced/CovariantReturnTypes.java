package com.hackerrank.java.advanced;

import java.util.Scanner;

import io.github.amarcinkowski.hackerrank.Solution;

class Flower {
	public String whats_Your_Name() {
		return "I have many names and types";
	}
}

class Jasmine extends Flower {
	@Override
	public String whats_Your_Name() {
		return "Jasmine";
	}
}

class Lily extends Flower {
	@Override
	public String whats_Your_Name() {
		return "Lily";
	}
}

class Lotus extends Flower {
	@Override
	public String whats_Your_Name() {
		return "Lotus";
	}
}

class State {
	public Flower your_National_Flower() {
		return new Flower();
	}
}

class WestBengal extends State {
	@Override
	public Flower your_National_Flower() {
		return new Jasmine();
	}
}

class Karnataka extends State {
	@Override
	public Flower your_National_Flower() {
		return new Lotus();
	}
}

class AndhraPradesh extends State {
	@Override
	public Flower your_National_Flower() {
		return new Lily();
	}
}

public class CovariantReturnTypes extends Solution {
	public CovariantReturnTypes(String name) {
		super(name);
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
			String flower = state.your_National_Flower().getClass().getName();
			flower = flower.replace(pack, "");
			log(flower);
			System.out.println(flower);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
