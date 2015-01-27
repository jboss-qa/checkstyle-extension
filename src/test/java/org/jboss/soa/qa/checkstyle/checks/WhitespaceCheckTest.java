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
 * Test cases for {@link WhitespaceCheck}.
 */
public class WhitespaceCheckTest extends BaseTest {

	/**
	 * Tests trailing whitespaces check for valid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testValidWhitespacesWithoutMultipleConsecutiveSpacesCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(WhitespaceCheck.class.getName());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputValidWhitespaces.java");
		final String[] expected = {};

		verify(checker, fileName, expected);
	}

	/**
	 * Tests trailing whitespaces check and multi consecutive spaces for valid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testValidWhitespacesWithMultipleConsecutiveSpacesCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(WhitespaceCheck.class.getName());
		checkConfig.addAttribute("checkMultipleSpaces", Boolean.TRUE.toString());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputValidWhitespaces.java");
		final String[] expected = {};

		verify(checker, fileName, expected);
	}

	/**
	 * Tests trailing whitespaces check for invalid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testInvalidWhitespacesWithoutMultipleConsecutiveSpacesCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(WhitespaceCheck.class.getName());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputInvalidWhitespaces.java");
		final String[] expected = {
				"2:5: Trailing whitespaces.",
				"3:41: Trailing whitespaces.",
				"4:4: Trailing whitespaces.",
				"5:43: Trailing whitespaces.",
				"16:15: Trailing whitespaces.",
				"18:5: Trailing whitespaces."
		};

		verify(checker, fileName, expected);
	}

	/**
	 * Tests trailing whitespaces check and multi consecutive spaces for invalid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testInvalidWhitespacesWithMultipleConsecutiveSpacesCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(WhitespaceCheck.class.getName());
		checkConfig.addAttribute("checkMultipleSpaces", Boolean.TRUE.toString());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputInvalidWhitespaces.java");
		final String[] expected = {
				"2:5: Trailing whitespaces.",
				"3:41: Trailing whitespaces.",
				"4:4: Trailing whitespaces.",
				"5:13: Consecutive spaces (2).",
				"5:41: Consecutive spaces (3).",
				"5:43: Trailing whitespaces.",
				"11:26: Consecutive spaces (2).",
				"12:27: Consecutive spaces (5).",
				"13:20: Consecutive spaces (4).",
				"15:33: Consecutive spaces (2).",
				"15:37: Consecutive spaces (2).",
				"16:15: Trailing whitespaces.",
				"18:5: Trailing whitespaces."
		};

		verify(checker, fileName, expected);
	}
}
