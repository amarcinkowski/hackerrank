package io.github.amarcinkowski.solutionframework;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputHandler {

	public int[] readIntArray(String line) {
		List<Integer> l = Arrays.asList(line.split(" ")).stream().map(Integer::valueOf).collect(Collectors.toList());
		return l.stream().mapToInt(i -> i).toArray();
	}

	public Integer[] readIntegerArray(String line) {
		List<Integer> l = Arrays.asList(line.split(" ")).stream().map(Integer::valueOf).collect(Collectors.toList());
		return l.stream().toArray(Integer[]::new);
	}

}
