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
 * Test cases for {@link BlankLineBeforeBlockEndCheck}.
 */
public class BlankLineBeforeBlockEndCheckTest extends BaseTest {

	/**
	 * Tests blank line before block end for valid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testValidBlankLineBeforeBlockEndCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(BlankLineBeforeBlockEndCheck.class.getName());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputNoBlankLineBeforeBlockEnd.java");
		final String[] expected = {};

		verify(checker, fileName, expected);
	}

	/**
	 * Tests blank line before block end for invalid input.
	 * @throws Exception test failure
	 */
	@Test
	public void testInvalidBlankLineBeforeBlockEndCheck() throws Exception {
		final DefaultConfiguration checkConfig = new DefaultConfiguration(BlankLineBeforeBlockEndCheck.class.getName());

		final Checker checker = createChecker(checkConfig);
		final String fileName = BaseTest.getPath("InputBlankLineBeforeBlockEnd.java");
		final String[] expected = {
				"9:1: Unnecessary blank line before block end.",
				"13:1: Unnecessary blank line before block end.",
				"22:1: Unnecessary blank line before block end.",
				"24:1: Unnecessary blank line before block end.",
				"26:1: Unnecessary blank line before block end."
		};

		verify(checker, fileName, expected);
	}
}
