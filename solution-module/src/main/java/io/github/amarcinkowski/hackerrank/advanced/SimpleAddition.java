package io.github.amarcinkowski.hackerrank.advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import io.github.amarcinkowski.hackerrank.Solution;

public class SimpleAddition extends Solution {
public SimpleAddition(String name) {super(name);}	/*
* COPY FROM LINE BELOW:

	private void log(String msg) {}
/* */

	class Add {
		public void add(int ... args) {
			StringBuilder result = new StringBuilder();
			int sum = 0;
			for (int i : args) {
				sum += i;
				result.append(i + "+");
			}
			result.replace(result.length() - 1, result.length(), "=");
			result.append(sum);
			log(result.toString());
			System.out.println(result.toString());
		}
	}

	public void execute() {
		try{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			int n1=Integer.parseInt(br.readLine());
			int n2=Integer.parseInt(br.readLine());
			int n3=Integer.parseInt(br.readLine());
			int n4=Integer.parseInt(br.readLine());
			int n5=Integer.parseInt(br.readLine());
			int n6=Integer.parseInt(br.readLine());
			Add ob=new Add();
			ob.add(n1,n2);
			ob.add(n1,n2,n3);
			ob.add(n1,n2,n3,n4,n5);	
			ob.add(n1,n2,n3,n4,n5,n6);
			Method[] methods=Add.class.getDeclaredMethods();
			Set<String> set=new HashSet<>();
			boolean overload=false;
			for(int i=0;i<methods.length;i++)
			{
				if(set.contains(methods[i].getName()))
				{
					overload=true;
					break;
				}
				set.add(methods[i].getName());
				
			}
			if(overload)
			{
				throw new Exception("Overloading not allowed");
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}

// uncomment in hr:
//	public static void main(String[] args) {new Solution().execute();}
}
