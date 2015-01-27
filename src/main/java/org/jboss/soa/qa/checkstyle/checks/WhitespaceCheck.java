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

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

/**
 * CheckStyle extension check for identifying white spaces.
 * Check is able to identify multiple consecutive spaces (optional) and trailing whitespaces.
 */
public class WhitespaceCheck extends Check {

	/**
	 * Whether to test multiple consecutive spaces.
	 */
	private boolean checkMultipleSpaces = false;

	/**
	 * Sets whether to test multiple consecutive spaces.
	 * @param checkMultipleSpaces flag to test multiple consecutive spaces
	 */
	public void setCheckMultipleSpaces(boolean checkMultipleSpaces) {
		this.checkMultipleSpaces = checkMultipleSpaces;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[0]; // no interest
	}

	@Override
	public void beginTree(DetailAST aRootAST) {
		final String[] lines = getLines();
		for (int i = 0; i < lines.length; i++) {
			checkTrailingWhitespaces(lines[i], i + 1);
		}
		if (checkMultipleSpaces) {
			checkMultipleConsecutiveSpaces(lines);
		}
	}

	private void checkMultipleConsecutiveSpaces(String[] lines) {
		boolean inString = false;
		boolean inBlockComment = false;

		for (int i = 0; i < lines.length; i++) {
			final String line = lines[i];
			char prev = 'x'; // unimportant char
			int spaces = 0;

			for (int j = 0; j < line.length(); j++) {
				final char ch = lines[i].charAt(j);

				if (ch == '*') {
					if (prev == '/') {
						inBlockComment = true;
						prev = 'x'; // reset
						spaces = 0;
						continue;
					}
					prev = '*';
					spaces = 0;
					continue;
				}

				if (ch == '/') {
					if (inBlockComment && prev == '*') {
						inBlockComment = false;
						prev = 'x';
						spaces = 0;
						continue;
					}
					prev = ch;
					spaces = 0;
					continue;
				}

				if (inBlockComment) {
					continue;
				}

				if (ch == '"') {
					if (inString) {
						if (prev != '\\') {
							inString = false;
						}
						continue;
					}
					inString = true;
					if (spaces > 1) {
						log(i + 1, j, "consecutive.spaces", spaces);
					}
					prev = 'x';
					spaces = 0;
					continue;
				}

				if (ch == '\\') {
					if (prev == '\\') {
						prev = 'x';
						spaces = 0;
						continue;
					}
					prev = '\\';
					spaces = 0;
					continue;
				}

				if (!inString && ch == ' ') {
					prev = ch;
					spaces++;
					continue;
				}

				if (spaces > 1) {
					log(i + 1, j, "consecutive.spaces", spaces);
				}

				prev = 'x';  // all steps done, reset prev
				spaces = 0;
			}
		}
	}

	private void checkTrailingWhitespaces(String line, int nr) {
		if (line.length() > 0 && (line.endsWith(" ") || line.endsWith("\t"))) {
			log(nr, line.length(), "trailing.whitespaces");
		}
	}
}
