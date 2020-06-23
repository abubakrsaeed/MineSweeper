import javax.swing.*;
import java.awt.*;

class MineSweeperGUI extends JPanel {
    private MineGrid mineGrid;
    private JButton[][] buttons;

    public MineSweeperGUI(int numberOfRows, int numberOfColumns, int numberOfMines) {
        this.mineGrid = new MineGrid(numberOfRows, numberOfColumns, numberOfMines);
        this.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        buttons = new JButton[numberOfRows][numberOfColumns];

        setLayout(new GridLayout(numberOfRows, numberOfColumns));
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                JButton button = new JButton();
                button.addMouseListener(new ButtonHandler(i, j, this.mineGrid,this));
                add(button);
                buttons[i][j] = button;
            }
        }
    }

    public JButton getButton(int i, int j) {
        return buttons[i][j];
    }

    private boolean check(int row, int column) {
        return row >= 0 && column >= 0 && row < buttons.length && column < buttons[0].length;

    }

    public void openNeighbours(int i, int j) {
        for (int row = i - 1; row < i + 2; row++) {
            for (int column = j - 1; column < j + 2; column++) {
                if (check(row, column)) {
                    buttons[row][column].setText("" + mineGrid.getCellContent(row, column));
                }
            }
        }
    }
}
