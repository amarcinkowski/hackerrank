package io.github.amarcinkowski.hackerrank.exception;

@SuppressWarnings("serial")
public class MissingAnnotationException extends Exception {

	public MissingAnnotationException(String name) {
		super(name);
	}
}