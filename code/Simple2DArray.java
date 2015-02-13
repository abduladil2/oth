public class Simple2DArray implements Simple2DInterface {
  public static final int DEFAULT_BOARD_DIMENSION = 8;
  static final int BLACK = -1;
  static final int NONE = 0;
  static final int WHITE = 1;
  private int[][] simpleArray;
  /** Creates a blank Simple2DArray with the specified number of rows and columns. */
  public Simple2DArray(int rows, int columns) {
    simpleArray = new int[rows][columns];
  }
  /** Reset every element to 0 */
  public void clear() {
    simpleArray = new int[getNumberOfRows()][getNumberOfColumns()];
  }
  /** Gets the value at row and column. */ 
  public int get(int row, int column) {
    return simpleArray[row][column];
  }
  /** Gets the number of columns of this Simple 2D Array. */
  public int getNumberOfColumns() {
    return simpleArray.length != 0 ? simpleArray[0].length : 0;
  }
  /** Gets the number of rows of this Simple 2D Array. */
  public int getNumberOfRows() {
    return simpleArray.length;
  }

  /** Sets the value at location row and column to a certain color. */
  public void set(int row, int column, int color) {
    simpleArray[row][column] = color;
  }
}
