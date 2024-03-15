public class Character extends Space{
    private int index;
    public Character(String Character, int index) {
        super(Character);
        this.index = index;
    }

    @Override
    public String getSymbol() {
        return super.getSymbol().substring(index, index + 1);
    }

    public int getIndex() {
        return index;
    }

}
