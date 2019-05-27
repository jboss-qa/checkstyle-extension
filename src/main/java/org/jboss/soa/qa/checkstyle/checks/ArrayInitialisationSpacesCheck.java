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

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;

/**
 * CheckStyle extension to check space before one-line array initialisation closing brace.
 */
public class ArrayInitialisationSpacesCheck extends AbstractCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[0]; // no interest
	}

	@Override
	public int[] getAcceptableTokens() {
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

	@Override
	public void beginTree(DetailAST aRootAST) {
		final String[] lines = getLines();
		boolean inString = false;
		boolean inBlockComment = false;

		for (int i = 0; i < lines.length; i++) {
			final String line = lines[i];
			char prev = 'x'; // unimportant char
			boolean inChar = false;
			boolean inLineComment = false;

			for (int j = 0; j < line.length() && !inLineComment; j++) {
				final char ch = lines[i].charAt(j);

				if (ch == '*') {
					if (prev == '/') {
						inBlockComment = true;
						prev = 'x'; // reset
						continue;
					}
					prev = '*';
					continue;
				}

				// line comments
				if (ch == '/') {
					if (prev == '/') {
						inLineComment = true;
						continue;
					} else if (inBlockComment && prev == '*') {
						inBlockComment = false;
						prev = 'x';
						continue;
					}
					prev = ch;
					continue;
				}

				if (inBlockComment) {
					continue;
				}

				if (ch == '\'' && !inString) {
					inChar = !inChar;
				}

				if (ch == '"' && !inChar) {
					if (inString) {
						if (prev != '\\') {
							inString = false;
						}
						continue;
					}
					inString = true;
					continue;
				}

				if (ch == '\\') {
					if (prev == '\\') {
						prev = 'x';
						continue;
					}
					prev = '\\';
					continue;
				}

				if (ch == ' ' || ch == '\t') {
					prev = ch;
					continue;
				}

				if (ch == '}' && !inString && !line.trim().startsWith("}") && (prev == ' ' || prev == '\t')) {
					log(i + 1, j, "array.init.space");
				}

				prev = 'x'; // all steps done, reset prev
			}
		}
	}
}
