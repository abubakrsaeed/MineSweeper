import java.util.Random;

public class MineGrid {
    static final int MINE = -1;
    private int[][] mineInformation;

    public MineGrid(int numberOfRows, int numberOfColumns, int numberOfMines) {
        this.mineInformation = new int[numberOfRows][numberOfColumns];
        this.initializeCells();
        this.placeMines(numberOfMines);
        this.setMineInformation();
    }

    private void initializeCells() {
        for (int i = 0; i < this.mineInformation.length; ++i) {
            for (int j = 0; j < this.mineInformation[0].length; ++j) {
                this.mineInformation[i][j] = 0;
            }
        }
    }

    private void placeMines(int numberOfMines) {
        Random random = new Random();
        for (int i = 0; i < numberOfMines; ++i) {
            int rowIndex = random.nextInt(mineInformation.length);
            int columnIndex = random.nextInt(mineInformation[0].length);
            if (this.mineInformation[rowIndex][columnIndex] != MINE) {
                this.mineInformation[rowIndex][columnIndex] = MINE;
            } else {
                --i;
            }
        }
    }

    private void incrementMineCountAt(int rowIndex, int columnIndex) {
        if (this.isInsideGrid(rowIndex, columnIndex) && !this.isMine(rowIndex, columnIndex)) {
            ++this.mineInformation[rowIndex][columnIndex];
        }
    }

    private void setMineInformation() {
        for (int i = 0; i < this.mineInformation.length; ++i) {
            for (int j = 0; j < this.mineInformation[0].length; ++j) {
                if (this.mineInformation[i][j] == MINE) {
                    // previous row
                    this.incrementMineCountAt(i - 1, j - 1);
                    this.incrementMineCountAt(i - 1, j);
                    this.incrementMineCountAt(i - 1, j + 1);

                    // left and right cells
                    this.incrementMineCountAt(i, j - 1);
                    this.incrementMineCountAt(i, j + 1);

                    // next row
                    this.incrementMineCountAt(i + 1, j - 1);
                    this.incrementMineCountAt(i + 1, j);
                    this.incrementMineCountAt(i + 1, j + 1);
                }
            }
        }
    }

    private boolean isInsideGrid(int rowIndex, int columnIndex) {
        return (rowIndex >= 0 && rowIndex < this.mineInformation.length) && (columnIndex >= 0 && columnIndex <
                this.mineInformation[0].length);
    }

    public boolean isMine(int rowIndex, int columnIndex) {
        return this.mineInformation[rowIndex][columnIndex] == MINE;
    }

    public int getCellContent(int rowIndex, int columnIndex) {
        return this.mineInformation[rowIndex][columnIndex];
    }
}
