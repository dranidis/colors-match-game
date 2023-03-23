import java.util.Random;

public class Grid {
    int[][] grid;
    int size;

    public Grid(int size) {
        this.size = size;
        this.grid = new int[size][size];
        populateRandomly();
    }

    public Grid(int [][] grid) {
        this.size = grid.length;
        this.grid = new int[size][size];     
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }   
    }

    private void populateRandomly() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = random.nextInt(2);
            }
        }
    }

    public void print() {
        for (int i = size - 1 ; i >=0; i--) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();

    }

    private int getEqualRow() {
        for (int row = 0; row < size; row++) {
            if (equalRow(row)) {
                return row;
            }
        }
        return -1;
    }


    public void updateGrid() {
        int equalRow = getEqualRow();
        while(equalRow != -1) {
            shiftRow(equalRow);
            equalRow = getEqualRow();
        }
    }

    public void updateGridAfterSwap(int row1, int col1, int row2, int col2) {
        swapCells(row1, col1, row2, col2);
        updateGrid();
    }

    private void shiftRow(int row) {
        for (int r = row ; r < size - 1; r++) {
            for (int c = 0 ; c < size; c++) {
                grid[r][c] = grid[r + 1][c];
            }
        }

        for (int c = 0 ; c < size; c++) {
            grid[size - 1][c] = -1;
        }        
    }

    private void fill() {
    }

    private void swapCells(int row1, int col1, int row2, int col2) {
        // Check if the two cells are adjacent
        if (!isCoord(row1, col1) || !isCoord(row2, col2)) {
            System.out.println("Invalid cell coordinates");
            return;
        }

        if (!areAdjacent(row1, col1, row2, col2)) {
            System.out.println("Error: The two cells are not adjacent.");
            return;
        }
    
        // Swap the two adjacent cells
        int temp = grid[row1][col1];
        grid[row1][col1] = grid[row2][col2];
        grid[row2][col2] = temp;
    }

    private boolean areAdjacent(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) + Math.abs(col1 - col2) == 1;
    }

    private boolean isCoord(int row, int col) {
        return row >= 0 && row < size && col >=0 && col < size;
    }

    private boolean equalRow(int row) {
        for (int i = 0; i < size - 1; i++) {
            if (grid[row][i] == -1 || grid[row][i] != grid[row][i+1]) {
                return false;
            }
        }
        return true;
    }

    private boolean equalColumn(int col) {
        for (int i = 0; i < size - 1; i++) {
            if (grid[i][col] == -1 || grid[i][col] != grid[i+1][col]) {
                return false;
            }
        }
        return true;
    }

	public void fillwith(Grid nextGrid) {
        for (int c = 0; c < size; c++) {
            for (int r = 0; r < size; r++) {
                if (grid[c][r] == -1) {
                    grid[c][r] = nextGrid.getColumnValue(c);
                }
            }
        }
	}

    private int getColumnValue(int c) {
        int value = grid[0][c];
        Random random = new Random();
        shiftColumnDown(c, random.nextInt(2));
        return value;
    }

    private void shiftColumnDown(int column, int nextInt) {
        for(int r = 0; r < size - 1; r++) {
            grid[r][column] = grid[r+1][column];
        }
        grid[size-1][column] = nextInt;
    }
    

}
