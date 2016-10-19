package com.hackerrank.java.advanced;

import java.util.Scanner;
import com.hackerrank.java.Solution;

public class Singleton extends Solution {
public Singleton(String name) {super(name);}	/*
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
//	public static void main(String[] args) {new Solution().execute();}
}
