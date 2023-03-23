public class Board {
    Grid currentGrid;
    Grid nextGrid;

    public Board() {
        currentGrid = new Grid(3);
        nextGrid = new Grid(3);
   }

    public void play(int row1, int col1, int row2, int col2) {
        currentGrid.updateGridAfterSwap(row1, col1, row2, col2);
        currentGrid.fillwith(nextGrid);
    }   
    
    public void print() {
        nextGrid.print();
        System.out.println();
        currentGrid.print();
    }
}
