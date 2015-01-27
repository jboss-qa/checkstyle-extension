package org.jboss.soa.qa.checkstyle.checks;

public class InputValidBlankLines {

	private static final int X;

	static {
		X = 1;

	}

	public  InputValidBlankLines() {

	}

	public void method() {
		final int i = 0;
		i++;

		for (; i < 10; i++) {
			System.out.println(i);

		}

	}

}
