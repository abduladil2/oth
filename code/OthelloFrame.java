//
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class OthelloFrame extends JFrame {

  private static final int FRAME_WIDTH = 600;
  private static final int FRAME_HEIGHT = FRAME_WIDTH;

  private OthelloComponent gridComponent;
  private JButton switchPlayerButton;

  public OthelloFrame() {
    // create grid and add to frame
    Simple2DInterface grid =
        new Simple2DArray(Simple2DArray.DEFAULT_BOARD_DIMENSION, Simple2DArray.DEFAULT_BOARD_DIMENSION);
    int halfWidth = grid.getNumberOfColumns() / 2;
    int halfHeight = grid.getNumberOfRows() / 2;
    grid.set(halfHeight - 1, halfWidth - 1, Simple2DArray.WHITE);
    grid.set(halfHeight - 1, halfWidth, Simple2DArray.BLACK);
    grid.set(halfHeight, halfWidth - 1, Simple2DArray.BLACK);
    grid.set(halfHeight, halfWidth, Simple2DArray.WHITE);
    gridComponent = new OthelloComponent(grid);
    add(gridComponent);

    // create control panel
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new GridLayout(2,1));

    // create and add switch player button to control panel
    switchPlayerButton = new JButton("Switch Color to White");
    ActionListener sp = new SwitchButtonListener();
    switchPlayerButton.addActionListener(sp);
    controlPanel.add(switchPlayerButton);

    // create and add instructions to control panel
    JPanel msgPanel = new JPanel();
    msgPanel.setBorder(new TitledBorder("Message"));
    msgPanel.add(new JLabel("Click on an empty space to put a disk or click on a disk to change color."));
    controlPanel.add(msgPanel);

    // add control panel to frame
    add(controlPanel, BorderLayout.SOUTH);

    // final setup
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    setTitle("Wanna be Othello");
    setVisible(true);
  }

  public static void main(String[] args) {
    JFrame frame = new OthelloFrame();
    frame.setVisible(true);
  }

  private class SwitchButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent arg0) {
      gridComponent.switchColor();
      switchPlayerButton.setText("Switch Color to " + (gridComponent.whiteToPlay() ? "Black" : "White"));
    }
  }
}
