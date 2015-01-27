package org.jboss.soa.qa.checkstyle.checks;

public class InputValidWhitespaces {

	public InputValidWhitespaces() {
	}

	/**
	 * Method   documentation.
	 */
	public void method() {
		final int i = 0;
		// line comment
		System.out.println("Print   more spaces");
		System.out.println(2 + "" + 1);
		i++;
	}
}
