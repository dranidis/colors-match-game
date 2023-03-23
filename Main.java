public class Main {

    public static void main(String[] args) {
        // Grid grid = new Grid(3);

        Board board = new Board();

        board.print();

        board.play(0, 0, 0, 1);
        board.print();

    }
}
