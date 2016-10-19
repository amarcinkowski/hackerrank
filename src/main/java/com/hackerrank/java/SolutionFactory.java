package com.hackerrank.java;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class SolutionFactory {

	@SuppressWarnings("unchecked")
	private Class<Solution> getSolutionClass(String name) throws IOException {
		try {
			return (Class<Solution>) Class.forName(name);
		} catch (ClassNotFoundException e) {
			return (Class<Solution>) new SolutionBuilder().fromCanonical(name).createFile(true).fromTemplate(true)
					.build().getClass();
		}
	}

	private Solution getInstance(String name)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		return getSolutionClass(name).getDeclaredConstructor(String.class).newInstance(name);
	}

	public Solution getSolution(String name) {
		try {
			return getInstance(name);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
