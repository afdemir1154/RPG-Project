public abstract class Potion implements Usable, Tradeable {

    protected int price;

    public Potion(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

}