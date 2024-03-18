public class WordAmount {
    private String letter;
    private int amount;
    public WordAmount(String letter) {
        this.letter = letter;
        amount = 0;
    }

    public String getLetter() {
        return letter;
    }
    public int getAmount() {
        return amount;
    }

    public void addAmount() {
        amount++;
    }
    public void removeAmount() {
        amount--;
    }
}
