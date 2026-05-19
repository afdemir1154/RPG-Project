import java.util.ArrayList;

public class Inventory {

    private ArrayList<Tradeable> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public boolean addItem(Tradeable item) {
        if (item != null) {
            items.add(item);
            return true;
        }
        return false;
    }

    public void removeItem(Tradeable item) {
        items.remove(item);
    }

    public ArrayList<Tradeable> getItems() {
        return items;
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty!");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "Inventory{" + "items=" + items + '}';
    }
}