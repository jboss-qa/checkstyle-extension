package org.jboss.soa.qa.checkstyle.checks;

public class InputValidBlankLines {

	private int[] a = {0, 1, 2 };
	private int[] b;
	private int[][] aa = {{0, 1 }, {1, 0 }, {0, 1 } };
	private int[][] bb;

	public InputValidBlankLines() {
		b = new int[] {0, 1, 2 };
	}

	public void method() {
		bb = new int[][] {new int[] {0, 1 }, new int[] {1, 0 }, new int[] {0, 1 } };

		final int[] e = {
				0,
				1
		};
		final int[] f = new int[] {
				0,
				1
		};

		final int[][] ee = {
				{
						0,
						1
				},
				{
						1,
						0
				},
				{
						0,
						1
				}
		};

		final int[][] ff = new int[][] {
				new int[] {
						0,
						1
				},
				new int[] {
						1,
						0
				},
				new int[] {
						0,
						1
				}
		};

		method2(new int[]{0, 1, 2});
		method2(new int[]{
				0,
				1,
				2
		 });
		method2(new int[]{
				0,
				1,
				2 });

		method3(new int [][] {{0, 1 }, {1, 0 }, {0, 1} });
		method3(new int [][] {new int[] {0, 1}, new int[] {1, 0}, new int[] {0, 1	}    });
		method3(new int [][] {
				{0, 1}, {1, 0 }, {0, 1}
		 });
		method3(new int [][] {
				new int[] {0,  1   }, new int[] {1, 0}, new int[] {0, 1}
		 });
		method3(new int [][] {
				{0, 1},
				{  1, 0},
				{0, 1	}
		} );
		method3(new int [][] {
				new int[] {0, 1},
				new int[] {1, 0	},
				new int[] {0, 1}
		  }   );

		/* } */

		/**
		 *
		 * }
		 *
		 * } "
		 *
		 */

		b = new int[] {0, 1, 2 };

		String s1 = "test } }";
		String s2 = "A " + "B }" + " } C";
		String s3 = "A "
				+ "B }"
				+ " } C \\\" }";

		// }
		// "
		b = new int[] {0, 1, 2 };
	}

	public void method2(int[] x) {
	}

	public void method3(int[][] y) {
	}
}
