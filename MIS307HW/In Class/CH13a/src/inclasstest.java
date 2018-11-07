
public class inclasstest {

	public static void main(String[] args) {
		char[] t = { 's', 'w', 'a' };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != j) {
					for (int k = 0; k < 3; k++) {
						if ((i != k) && (j != k)) {
							System.out.print(t[i]);
							System.out.print(t[j]);
							System.out.print(t[k]);
							System.out.printf("  Permutation\n");
						}
					}
				}
			}
		}

	}

}
