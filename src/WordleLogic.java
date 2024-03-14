import java.util.Scanner;
public class WordleLogic {
    private Space[][] board;
    private Scanner scan;
    private Word word;
    public WordleLogic() {
        scan = new Scanner(System.in);
        word = new Word();
        word.readData();
        introduce();
    }

    private void introduce() {
        System.out.println("Welcome to the game of wordle! Here are some Instruction for you to know!");
        System.out.println("Each Color have their own meaning. Red mean that the character isn't in the word or there is no more of that character.");
        System.out.println("Green mean the word have that character and is at the correct place. Yellow mean the word have that character but it isn't at that position.");
        System.out.print("Please enter the length of the word you want to do. It must be above 3 letter! ");
        int length = scan.nextInt();
        scan.nextLine();
        while (length < 4) {
            System.out.print("Please choose the a appropriate length: ");
            length = scan.nextInt();
            scan.nextLine();
        }
        setUpBoard(length);
    }
    private void setUpBoard(int length) {
        word.chooseWord(length);
        board = new Space[6][length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Space space = new Space("_");
                board[row][col] = space;
            }
        }
    }

    private void printBoard() {
        for (Space[] row : board) {
            for (Space col : row) {
                System.out.print(col.getSymbol());
            }
            System.out.println();
        }
    }

    private void play() {
        int index = 0;
        while (!(board[5][1] instanceof Character)) {

        }
    }

    private boolean checkRow(int index) {
        String word = "";
        for (Space col : board[index]) {
            if (col instanceof Character) {
                word += ((Character) col).getSymbol();
            } else {
                word += col.getSymbol();
            }
        }
    }
}
