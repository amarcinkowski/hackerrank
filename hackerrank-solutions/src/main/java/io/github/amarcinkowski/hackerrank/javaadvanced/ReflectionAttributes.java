package io.github.amarcinkowski.hackerrank.javaadvanced;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import io.github.amarcinkowski.solutionframework.Solution;
import io.github.amarcinkowski.solutionframework.TestInfo;

public class ReflectionAttributes extends Solution {
	public ReflectionAttributes(TestInfo ti) {
		super(ti);
	} /*
		 * overloaded log:
		 * 
		 * private void log(String msg) {} /*
		 */

	class Student {
		private String name;
		private String id;
		private String email;

		public String getName() {
			return name;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void anothermethod() {
			int x = id.length() +  email.length();
			id = "" + x;
		}

	}

	public void execute() {
		log("execute");

		Class<Student> student = Student.class;
		Method[] methods = student.getDeclaredMethods();

		ArrayList<String> methodList = new ArrayList<>();
		for (Method method : methods) {
			methodList.add(method.getName());
		}
		Collections.sort(methodList);
		for (String name : methodList) {
			System.out.println(name);
		}
	}

	// hr:
	// public static void main(String[] args) {new Solution().execute();}
}
