package io.github.amarcinkowski.hackerrank.javaadvanced;

import java.util.Scanner;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class Singleton extends Solution {
public Singleton(TestInfo ti) {super(ti);}	/*
* overloaded log:

	private void log(String msg) {}
/* */

	Scanner scanner;
	
	static Singleton INSTANCE;

	public String str;
		
	private Singleton() {
	}
		
	public static Singleton getSingleInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Singleton();
		}
		return INSTANCE;
	}
		
	public void execute() {
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String str = String.format("Hello I am a singleton! Let me say %s to you", line); 
		log(str);
		System.out.println(str);
	}

// hr:
	public static void main(String[] args) {new Singleton().execute();}
}
