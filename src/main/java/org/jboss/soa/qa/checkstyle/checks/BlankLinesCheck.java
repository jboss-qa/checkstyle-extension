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
 * CheckStyle extension check for identifying multiple consecutive blank lines.
 */
public class BlankLinesCheck extends AbstractCheck {

	/**
	 * Maximum number of consecutive blank lines.
	 */
	private int max = 1;

	/**
	 * Sets the maximum number of consecutive blank lines to check.
	 *
	 * @param max the maximum
	 */
	public void setMax(int max) {
		this.max = max;
	}

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
		int blankLines = 0;
		for (int i = 0; i < lines.length; i++) {
			if ("".equals(lines[i].trim())) { // is blank line
				blankLines++;
			} else {
				if (blankLines > max) {
					log(i, 0, "blank.lines", blankLines);
				}
				blankLines = 0;
			}
		}
	}
}
