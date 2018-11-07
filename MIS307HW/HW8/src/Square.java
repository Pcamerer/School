/**
 * Code for E13.2(HW8)
 * @author Preston Camerer
 */
/**
 * A square with integer dimensions.
 */
public class Square {
	private int width;

	/**
	 * Constructs a new square with integer dimensions.
	 * 
	 * @param w
	 *            width
	 */
	public Square(int w) {
		width = w;
	}

	/**
	 * Computes the area of the square.
	 * 
	 * @return the area
	 */
	public int getArea() {
		if (width == 0) {
			return 0;
		} else {
			return (new Square(width - 1)).getArea() + 2 * width - 1;
		}
	}
}
