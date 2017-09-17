
/**
 * this class is used to store approximate matching methods
 */

public class ApproxMatch {

	// this method is used to calculate the local distance between two strings
	public static int localDistance(String q, String t) {
		String qTemp = q.toLowerCase();
		String tTemp = t.toLowerCase();
		int lq = qTemp.length();
		int lt = tTemp.length();
		int delete = -1;
		int insert = -1;

		int[][] matrix = new int[lq + 1][lt + 1];
		for (int a = 0; a <= lq; a++)
			matrix[a][0] = 0;
		for (int b = 0; b <= lt; b++)
			matrix[0][b] = 0;
		for (int a = 1; a <= lq; a++) {
			for (int b = 1; b <= lt; b++) {
				matrix[a][b] = Math.max(
						Math.max(0, matrix[a - 1][b - 1]
								+ (qTemp.charAt(a - 1) == tTemp.charAt(b - 1)
										? 1 : (-1))),
						Math.max(matrix[a - 1][b] + insert,
								matrix[a][b - 1] + delete));
			}
		}
		int max = 0;
		for (int a = 0; a <= lq; a++) {
			for (int b = 0; b <= lt; b++) {
				if (matrix[a][b] > max)
					max = matrix[a][b];
			}
		}
		return max;
	}

	// this method is used to calculate the levenshtein distance between two
	// strings
	public static int levenshteinDistance(String q, String t) {
		String qTemp = q.toLowerCase();
		String tTemp = t.toLowerCase();
		int lq = qTemp.length();
		int lt = tTemp.length();
		int delete = 1;
		int insert = 1;

		int[][] matrix = new int[lq + 1][lt + 1];
		for (int a = 0; a <= lq; a++)
			matrix[a][0] = a;
		for (int b = 0; b <= lt; b++)
			matrix[0][b] = b;
		for (int a = 1; a <= lq; a++) {
			for (int b = 1; b <= lt; b++) {
				matrix[a][b] = Math.min(matrix[a - 1][b - 1]
						+ (qTemp.charAt(a - 1) == tTemp.charAt(b - 1) ? 0 : 1),
						Math.min(matrix[a - 1][b] + insert,
								matrix[a][b - 1] + delete));
			}
		}
		return matrix[lq][lt];
	}

}
