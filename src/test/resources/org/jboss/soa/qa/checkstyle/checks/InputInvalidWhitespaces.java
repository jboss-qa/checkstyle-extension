package org.jboss.soa.qa.checkstyle.checks;
	
public class InputInvalidWhitespaces {	
   
	public  InputInvalidWhitespaces()   { 
	}

	/**
	 * Method   documentation.
	 */
	public void method(  ) {
		final int i =     0;
		// line    comment
		System.out.println("Print   more spaces"); // line comment
		System.out.println(2 +  ""  + 1);
		i++;  
	}
	
}
