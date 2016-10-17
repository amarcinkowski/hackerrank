package io.github.amarcinkowski;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInfo {

	String hackerrankDescription() default "";

	String solutionClass();

	String domain() default "";

	String group();

	boolean done() default false;

}