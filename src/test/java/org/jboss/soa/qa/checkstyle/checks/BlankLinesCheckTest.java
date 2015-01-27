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
 * Test cases for {@link BlankLinesCheck}.
 */
public class BlankLinesCheckTest extends BaseTest {

	/**
	 * Tests consecutive blank lines check for valid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testValidBlankLinesCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(BlankLinesCheck.class.getName());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputOneConsecutiveBlankLines.java");
		final String[] expected = {};

		verify(checker, fileName, expected);
	}

	/**
	 * Tests consecutive blank lines check for invalid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testInvalidBlankLinesCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(BlankLinesCheck.class.getName());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputManyConsecutiveBlankLines.java");
		final String[] expected = {
				"5:1: To many consecutive blank lines (2).",
				"11:1: To many consecutive blank lines (4).",
				"15:1: To many consecutive blank lines (3)."
		};

		verify(checker, fileName, expected);
	}

	/**
	 * Tests consecutive blank lines check for valid input with 2 consecutive blank lines allowed.
	 * @throws Exception test failure
	 */
	@Test
	public void testValidBlankLinesWithMaxConsecutiveBlankLinesSettingsCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(BlankLinesCheck.class.getName());
		checkConfig.addAttribute("max", Integer.valueOf(2).toString());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputTwoConsecutiveBlankLines.java");
		final String[] expected = {};

		verify(checker, fileName, expected);
	}
}
