
public interface Simple2DInterface {

  /** Reset every element to 0. */
  public void clear();

  /** Gets the value at row and column. */ 
  public int get(int row, int column);

  /** Gets the number of columns of this Simple2DInterface. */
  public int getNumberOfColumns();

  /** Gets the number of rows of this Simple2DInterface. */
  public int getNumberOfRows();

  /** Sets the value at location row and column to a certain color. */
  public void set(int row, int column, int color);
}
