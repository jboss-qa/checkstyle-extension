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
 * CheckStyle extension check for identifying unnecessary blank lines before block end.
 */
public class BlankLineBeforeBlockEndCheck extends Check {

	@Override
	public int[] getDefaultTokens() {
		return new int[0]; // no interest
	}

	@Override
	public void beginTree(DetailAST aRootAST) {
		final String[] lines = getLines();
		boolean blank = false;

		for (int i = 0; i < lines.length; i++) {
			if ("".equals(lines[i].trim())) { // is blank line
				blank = true;
			} else {
				if ("}".equals(lines[i].trim()) && blank) { // block end
					log(i, 0, "unnecessary.blank.line");
				}
				blank = false;
			}
		}
	}
}
