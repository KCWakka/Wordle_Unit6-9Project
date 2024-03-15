import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Word {
    private ArrayList<String> wordsList;
    private String word;

    public Word() {
        word = "";
        wordsList = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public ArrayList<String> getWordsList() {
        return wordsList;
    }

    public void readData() {
        try {
            File myfile = new File("src\\words.txt");
            Scanner fileScanner = new Scanner(myfile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                wordsList.add(data);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void chooseWord(int length) {
        int number = (int) (Math.random() * wordsList.size());
        while (wordsList.get(number).length() != length) {
            number = (int) (Math.random() * wordsList.size());
        }
        word = wordsList.get(number);
    }
}
