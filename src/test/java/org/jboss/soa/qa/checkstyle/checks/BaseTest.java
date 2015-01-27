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

import static org.junit.Assert.assertEquals;

import com.google.common.collect.Lists;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultLogger;
import com.puppycrawl.tools.checkstyle.TreeWalker;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.Configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Base test class. Based on CheckStyle BaseCheckTestSupport test class.
 * @see com.puppycrawl.tools.checkstyle.BaseCheckTestSupport in CheckStyle sources
 */
public class BaseTest {

	/**
	 * Width of tab.
	 */
	private static final int TAB_WIDTH = 4;

	public static class BriefLogger extends DefaultLogger {

		public BriefLogger(OutputStream out) {
			super(out, true);
		}

		@Override
		public void auditStarted(AuditEvent evt) {
		}

		@Override
		public void fileFinished(AuditEvent evt) {
		}

		@Override
		public void fileStarted(AuditEvent evt) {
		}
	}

	public static String getPath(String fileName) throws IOException {
		return new File("src/test/resources/org/jboss/soa/qa/checkstyle/checks/" + fileName).getCanonicalPath();
	}

	protected final ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
	protected final PrintStream printStream = new PrintStream(byteArrayOS);

	public Checker createChecker(Configuration configuration) throws Exception {
		final DefaultConfiguration defaultConfiguration = createCheckerConfig(configuration);
		final Checker checker = new Checker();
		final Locale locale = Locale.ENGLISH;

		checker.setLocaleCountry(locale.getCountry());
		checker.setLocaleLanguage(locale.getLanguage());
		checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
		checker.configure(defaultConfiguration);

		checker.addListener(new BriefLogger(printStream));
		return checker;
	}

	private DefaultConfiguration createCheckerConfig(Configuration configuration) {
		final DefaultConfiguration defaultConfiguration = new DefaultConfiguration("configuration");
		final DefaultConfiguration treeWalkerConfiguration = new DefaultConfiguration(TreeWalker.class.getName());

		defaultConfiguration.addAttribute("charset", "UTF-8");
		defaultConfiguration.addChild(treeWalkerConfiguration);
		treeWalkerConfiguration.addChild(configuration);
		treeWalkerConfiguration.addAttribute("tabWidth", Integer.valueOf(TAB_WIDTH).toString());

		return defaultConfiguration;
	}

	protected void verify(Checker checker, String processedFie, String[] expectedOutput) throws Exception {
		verify(checker, processedFie, processedFie, expectedOutput);
	}

	protected void verify(Checker checker, String processedFie, String messageFileName, String[] expectedOutput)
			throws Exception {

		verify(checker, new File[] {new File(processedFie)}, messageFileName, expectedOutput);
	}

	protected void verify(Checker checker, File[] processedFiles, String messageFileName, String[] expectedOutput)
			throws Exception {

		printStream.flush();
		final List<File> files = Lists.newArrayList();
		Collections.addAll(files, processedFiles);
		final int errs = checker.process(files);

		// process each of the lines
		final ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOS.toByteArray());
		final LineNumberReader lnr = new LineNumberReader(new InputStreamReader(bais));

		for (int i = 0; i < expectedOutput.length; i++) {
			final String expected = messageFileName + ":" + expectedOutput[i];
			final String actual = lnr.readLine();
			assertEquals("error message " + i, expected, actual);
		}

		assertEquals("unexpected output: " + lnr.readLine(), expectedOutput.length, errs); checker.destroy();
	}
}
