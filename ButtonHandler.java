import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class ButtonHandler implements MouseListener {
    private int row;
    private int column;
    private MineGrid mineGrid;
    private MineSweeperGUI gui;

    public ButtonHandler(int row, int column, MineGrid mineGrid, MineSweeperGUI gui) {
        this.row = row;
        this.column = column;
        this.mineGrid = mineGrid;
        this.gui = gui;
    }

    public void actionPerformed(MouseEvent mouseEvent) {
        if (this.mineGrid.isMine(this.row, this.column)) {
            JOptionPane.showMessageDialog(null, "GAME OVER!");
            System.exit(0);
        } else {
            if (mouseEvent.getSource() instanceof JButton) {
                JButton button = (JButton) mouseEvent.getSource();
                button.setText(String.valueOf(this.mineGrid.getCellContent(this.row, this.column)));

                int cellZero = mineGrid.getCellContent(row, column);
                button.setText(String.valueOf(cellZero));

                if (cellZero == 0) {
                    gui.openNeighbours(row, column);
                }
            }

        }
    }

    private void flagged(MouseEvent mouseEvent) {
        JButton button = (JButton) mouseEvent.getSource();
        if (button.getText().equals("F")) {
            button.setText("");
        } else {
            button.setText("F");
            if (allFlagged()) {
                JOptionPane.showMessageDialog(null, "You're a genius");
                System.exit(0);
            }
        }
    }

    private boolean allFlagged() {
        int flagged = 0;
        for (int i = 0; i < MineSweeper.SIZE; i++) {
            for (int j = 0; j < MineSweeper.SIZE; j++) {
                if (gui.getButton(i, j).getText().equals("F")) {
                    if (mineGrid.getCellContent(i, j) != MineGrid.MINE) {
                        return false;
                    } else {
                        flagged++;
                    }
                }
            }
        }
        return flagged == MineSweeper.NUMBER_OF_MINES;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            actionPerformed(mouseEvent);
        }
        else if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            flagged(mouseEvent);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}