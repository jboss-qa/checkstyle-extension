/*
 * Copyright 2015 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.soa.qa.checkstyle.checks;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;

/**
 * Test cases for {@link org.jboss.soa.qa.checkstyle.checks.ArrayInitialisationSpacesCheck}.
 */
public class ArrayInitialisationSpacesCheckTest extends BaseTest {

	/**
	 * Tests spaces before array initialisation closing brace for valid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testValidSpacesBeforeArrayInitClosingBraceCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(
				ArrayInitialisationSpacesCheck.class.getName());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputValidSpacesBeforeArrayInitClosingBrace.java");
		final String[] expected = {};

		verify(checker, fileName, expected);
	}

	/**
	 * Tests spaces before array initialisation closing brace for invalid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testInvalidSpacesBeforeArrayInitClosingBraceCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(
				ArrayInitialisationSpacesCheck.class.getName());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputInvalidSpacesBeforeArrayInitClosingBrace.java");
		final String[] expected = {
				"5:32: Space before array initialisation closing brace.",
				"7:33: Space before array initialisation closing brace.",
				"7:42: Space before array initialisation closing brace.",
				"7:51: Space before array initialisation closing brace.",
				"7:53: Space before array initialisation closing brace.",
				"11:32: Space before array initialisation closing brace.",
				"15:43: Space before array initialisation closing brace.",
				"15:62: Space before array initialisation closing brace.",
				"15:81: Space before array initialisation closing brace.",
				"15:83: Space before array initialisation closing brace.",
				"65:19: Space before array initialisation closing brace.",
				"67:37: Space before array initialisation closing brace.",
				"67:46: Space before array initialisation closing brace.",
				"67:56: Space before array initialisation closing brace.",
				"68:85: Space before array initialisation closing brace.",
				"68:90: Space before array initialisation closing brace.",
				"70:31: Space before array initialisation closing brace.",
				"73:36: Space before array initialisation closing brace.",
				"78:25: Space before array initialisation closing brace.",
				"82:33: Space before array initialisation closing brace.",
				"96:32: Space before array initialisation closing brace.",
				"106:32: Space before array initialisation closing brace.",
		};

		verify(checker, fileName, expected);
	}
}
