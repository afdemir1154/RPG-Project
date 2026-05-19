import java.util.ArrayList;

public class Shop {

    private ArrayList<Tradeable> stock;

    public Shop() {
        stock = new ArrayList<>();
        
        stock.add(new SmallHealthPotion());
        stock.add(new BigHealthPotion());
        stock.add(new SmallStrengthPotion());
        stock.add(new Dagger());
        stock.add(new Longsword());
    }

   
    public void buyItem(Hero hero, int index) throws InsufficientCoinException {
        if (index < 0 || index >= stock.size()) {
            System.out.println("Item not found in shop!");
            return;
        }
        
        Tradeable item = stock.get(index);
        if (hero.getCoinTotal() >= item.getPrice()) {
            hero.spendCoin(item.getPrice());
            hero.getInventory().addItem(item);
            stock.remove(item); 
            System.out.println(item.toString() + " successfully purchased and added to your inventory!");
        } else {
            throw new InsufficientCoinException("Insufficient Coins! " + item.toString() + " costs: " + item.getPrice() + " Coins. ");
        }
    }

    public void displayShop() {
        System.out.println("\n=== SHOP STOCK ===");
        if (stock.isEmpty()) {
            System.out.println("Shop is empty!");
            return;
        }
        for (int i = 0; i < stock.size(); i++) {
            System.out.println((i + 1) + ". " + stock.get(i).toString() + " - Price: " + stock.get(i).getPrice() + " Coin");
        }
    }
}