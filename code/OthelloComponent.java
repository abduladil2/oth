//package com.faraaz.proj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class OthelloComponent extends JComponent implements MouseListener {

  private static final int MARGIN = 10;

  private static final int CIRCLE_MARGIN = MARGIN / 2;
  private static final int BOTTOM_MARGIN = MARGIN;
  private static final int TOP_MARGIN = MARGIN;
  private static final int LEFT_MARGIN = MARGIN;
  private static final int RIGHT_MARGIN = MARGIN;

  private Simple2DInterface grid;
  private int numRows;
  private int numColumns;

  private double circleDiameter;
  private double topLeftX;
  private double topLeftY;
  private double cellHeight;
  private double cellWidth;
  private double boardHeight;
  private double boardWidth;

  private int turn;

  public OthelloComponent(Simple2DInterface a2DArray) {
    grid = a2DArray;
    numRows = grid.getNumberOfRows();
    numColumns = grid.getNumberOfColumns();
    turn = Simple2DArray.BLACK;

    this.addMouseListener(this);
  }

  public int getColumn(int x) {
    return (int) (Math.ceil((x - topLeftX) / cellWidth) - 1);
  }

  public int getRow(int y) {
    return (int) (Math.ceil((y - topLeftY) / cellHeight) - 1);
  }

  public void mouseClicked(MouseEvent arg0) {
    int column = getColumn(arg0.getX());
    int row = getRow(arg0.getY());

    // you can take this out if you want
    System.out.println("r: " + row + ", c: " + column);

    if (row >= 0 && column >= 0 && row < numRows && column < numColumns) {
        grid.set(row, column, turn);
    }
    repaint();
  }

  public void mouseEntered(MouseEvent arg0) {}

  public void mouseExited(MouseEvent arg0) {}

  public void mousePressed(MouseEvent arg0) {}

  public void mouseReleased(MouseEvent arg0) {}

  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    cellHeight = ((double) getHeight() - (TOP_MARGIN + BOTTOM_MARGIN)) / numRows;
    cellWidth = ((double) getWidth() - (LEFT_MARGIN + RIGHT_MARGIN)) / numColumns;

    // make cells the largest possible squares
    if (cellWidth > cellHeight) {
      cellWidth = cellHeight;
    } else {
      cellHeight = cellWidth;
    }
    circleDiameter = cellWidth - (2 * CIRCLE_MARGIN);

    boardHeight = cellHeight * numRows;
    boardWidth = cellWidth * numColumns;

    topLeftX = (getWidth() - boardWidth) / 2;
    topLeftY = (getHeight() - boardHeight) / 2;
    double bottomRightX = topLeftX + boardWidth;
    double bottomRightY = topLeftY + boardHeight;

    Line2D.Double line = new Line2D.Double(0,0,0,0);

    // draw the lines on the board
    g2.setColor(Color.BLACK);
    for (int i = 0; i <= numRows; i++) {
      line.setLine(topLeftX, topLeftY + i * cellHeight, bottomRightX, topLeftY + i * cellHeight);
      g2.draw(line);
    }

    for (int i = 0; i <= numColumns; i++) {
      line.setLine(topLeftX + i * cellWidth, topLeftY, topLeftX + i * cellWidth, bottomRightY);
      g2.draw(line);
    }

    // draw circles
    for (int row = 0; row < numRows; row++) {
      for (int column = 0; column < numColumns; column++) {
        if  (grid.get(row, column) != Simple2DArray.NONE) {
          Color fill = grid.get(row, column) == Simple2DArray.BLACK ? Color.BLACK : Color.WHITE;
          drawCircle(row, column, fill, Color.BLACK, g2);
        }
      }
    }
  }

  public void switchColor() {
    turn = -turn;
  }

  public boolean whiteToPlay() {
    return turn == Simple2DArray.WHITE;
  }

  private void drawCircle(int row, int column, Color fill, Color border, Graphics2D g2) {
    Ellipse2D.Double circle = new Ellipse2D.Double();
    double x = topLeftX + CIRCLE_MARGIN + column * cellWidth;
    double y = topLeftY + CIRCLE_MARGIN + row * cellHeight;
    circle.setFrame(x,y,circleDiameter,circleDiameter);
    g2.setColor(fill);
    g2.fill(circle);
    g2.setColor(border);
    g2.draw(circle);
  }
}
