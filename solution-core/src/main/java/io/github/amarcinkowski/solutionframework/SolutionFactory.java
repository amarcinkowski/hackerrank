package io.github.amarcinkowski.solutionframework;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import io.github.amarcinkowski.utils.StringUtils;

public class SolutionFactory {

	// TODO mv io.github.amarcinkowski to 1 constant across all the code
	private final static String CANONICAL_CLASS = "io.github.amarcinkowski.%s.%s.%s";

	@SuppressWarnings("unchecked")
	private Class<Solution> getSolutionClass(TestInfo ti) throws IOException, ClassNotFoundException {
		return (Class<Solution>) Class.forName(getCanonicalClass(ti));
	}

	private Solution getInstance(TestInfo ti)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		return getSolutionClass(ti).getDeclaredConstructor(TestInfo.class).newInstance(ti);
	}

	public Solution getSolution(TestInfo ti) {
		try {
			return getInstance(ti);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getCanonicalClass(TestInfo ti) {
		return String.format(CANONICAL_CLASS, ti.platform(), StringUtils.packagify(ti.subdomain()), ti.classname());
	}

}
