import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
public class WordleLogic {
    private Space[][] board;
    private Scanner scan;
    private Word word;
    private ArrayList<String> letterUsed;
    private ArrayList<WordAmount> amountOfLetter;
    public WordleLogic() {
        scan = new Scanner(System.in);
        word = new Word();
        letterUsed = new ArrayList<>();
        amountOfLetter = new ArrayList<>();
        word.readData();
        introduce();
        play();
    }

    private void introduce() {
        System.out.println(Color.BLUE + "\n" +
                "██╗    ██╗ ██████╗ ██████╗ ██████╗ ██╗     ███████╗\n" +
                "██║    ██║██╔═══██╗██╔══██╗██╔══██╗██║     ██╔════╝\n" +
                "██║ █╗ ██║██║   ██║██████╔╝██║  ██║██║     █████╗  \n" +
                "██║███╗██║██║   ██║██╔══██╗██║  ██║██║     ██╔══╝  \n" +
                "╚███╔███╔╝╚██████╔╝██║  ██║██████╔╝███████╗███████╗\n" +
                " ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═╝╚═════╝ ╚══════╝╚══════╝\n" +
                "                                                   \n" + Color.RESET);
        System.out.println("Welcome to the game of wordle! Here are some Instruction for you to know!");
        System.out.println("Each Color have their own meaning. Red mean that the character isn't in the word or there is no more of that character.");
        System.out.println("Green mean the word have that character and is at the correct place. Yellow mean the word have that character but it isn't at that position.");
        System.out.println("Your goal of this game is to find out the word before you run out of tries by using information you obtain by the colors.");
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
                if (col instanceof  Character) {
                    int index = -1;
                    for (int i = 0; i < amountOfLetter.size(); i++) {
                        if (amountOfLetter.get(i).getLetter().equals(col.getSymbol())) {
                            index = i;
                            break;
                        }
                    }
                    if (index < 0 ) {
                        System.out.print(setColor((Character) col));
                    } else {
                        if (amountOfLetter.get(index).getAmount() != 0) {
                            System.out.print(setColor((Character) col));
                            amountOfLetter.get(index).removeAmount();
                        } else {
                            System.out.print(Color.RED + ((Character) col).getSymbol() + Color.RESET);
                        }
                    }
                } else {
                    System.out.print(col.getSymbol());
                }
            }
            wordAmount();
            System.out.println();
        }
    }

    private void play() {
        int index = 0;
        while (index < board.length && !checkRow(index - 1)) {
            wordAmount();
            printBoard();
            System.out.print("Please enter a word: ");
            String word = scan.nextLine();
            index += processChoice(word, index);
            System.out.println("Letter used: ");
            printLetterUsed();
        }
        printBoard();
        if (checkRow(index - 1)) {
            System.out.println("You win! You guess the word using " + index + " words!" + "Here are the letter you used!");
            printLetterUsed();
        } else {
            System.out.println("You lose! The word was " + Color.BLUE + word.getWord() + " Try again next time! Here are the letter you used!");
            printLetterUsed();
        }
    }

    private boolean checkRow(int index) {
        if (index < 0 ) {
            return false;
        }
        String word = "";
        for (Space col : board[index]) {
                word += col.getSymbol();
        }
        return word.equals(this.word.getWord());
    }

    private String setColor(Character word) {
        if (word.getSymbol().equals(this.word.getWord().substring(word.getIndex(), word.getIndex() + 1))) {
            return Color.GREEN + word.getSymbol() + Color.RESET;
        } else if (this.word.getWord().contains(word.getSymbol())) {
            return Color.YELLOW + word.getSymbol() + Color.RESET;
        } else {
            return Color.RED + word.getSymbol() + Color.RESET;
        }
    }
    private int processChoice(String word, int index) {
        if (word.length() != this.word.getWord().length()) {
            System.out.println("The word you select is either too short or too long to be input!");
            return 0;
        } else if (!this.word.getWordsList().contains(word)){
            System.out.println("The word you select is not a real word!");
        } else {
            for (int col = 0; col < board[index].length; col++) {
                Character character = new Character(word, col);
                if (!letterUsed.contains(character.getSymbol())) {
                    letterUsed.add(character.getSymbol());
                }
                board[index][col] = character;
            }
            return 1;
        }
        return 0;
    }

    private void printLetterUsed() {
        selectionSortletterUsed();
        for (String s : letterUsed) {
            System.out.println(s);
        }
    }
    private void selectionSortletterUsed() {
        for (int i = 0; i < letterUsed.size()- 1; i++) {
            int min = i;
            for (int f = i + 1; f < letterUsed.size(); f++) {
                String value = letterUsed.get(f);
                if (value.compareTo(letterUsed.get(min)) < 0) {
                    min = f;
                }
            }
            letterUsed.set(i, letterUsed.set(min, letterUsed.get(i)));
        }
    }
    private void wordAmount() {
        amountOfLetter.clear();
        WordAmount first = new WordAmount(word.getWord().substring(0, 1));
        amountOfLetter.add(first);
        for (int i = 1; i < word.getWord().length(); i++) {
            int index = -1;
            for (int f = 0; f < amountOfLetter.size(); f++) {
                if (amountOfLetter.get(f).getLetter().equals(word.getWord().substring(i, i + 1))) {
                    index = f;
                    break;
                }
            }
                if (index != -1) {
                    amountOfLetter.get(index).addAmount();
                } else {
                    WordAmount temp = new WordAmount(word.getWord().substring(i, i + 1));
                    amountOfLetter.add(temp);
                }
        }
    }
}
