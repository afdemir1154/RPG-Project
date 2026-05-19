public class Coin implements Lootable {

    public Coin() {
    }

    @Override
    public void drop() {
        System.out.println("Coin dropped.");
    }

    @Override
    public String toString() {
        return "Coin";
    }
}