package io.github.amarcinkowski.solutionframework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInfo {

	String platform() default "hackerrank";
	
	String taskDescription() default "";

	String solutionClass();

	String domain() default "";

	String group();

}