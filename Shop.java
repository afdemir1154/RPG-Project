import java.util.ArrayList;

public class Shop {

    private final ArrayList<Tradeable> stock;

    public Shop() {
        stock = new ArrayList<>();
        
        stock.add(new SmallHealthPotion());
        stock.add(new BigHealthPotion());
        stock.add(new SmallStrengthPotion());
        stock.add(new BigStrengthPotion());
        stock.add(new Dagger());
        stock.add(new Longsword());
        stock.add(new BattleAxe());
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
            System.out.println("\n" + item.toString() + " successfully purchased and added to your inventory!");
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
        Tradeable item = stock.get(i);
        if (item == null) {
                continue; 
            }
        
        String statInfo = "";

        // 1. Eşya bir Silah ise (Dagger, Longsword, BattleAxe)
        if (item instanceof Weapon weapon) {
            statInfo = " [Damage: +" + weapon.getBonusDamage() + "]"; 
        } 
        // 2. Eşya Can İksiri ise (SmallHealthPotion, BigHealthPotion)
        else if (item instanceof SmallHealthPotion hpPotion) {
            statInfo = " [Heal: +" + hpPotion.getHealAmount() + " HP]"; 
        } 
        else if (item instanceof BigHealthPotion hpPotion) {
            statInfo = " [Heal: +" + hpPotion.getHealAmount() + " HP]";
        }
        // 3. Eşya Güç İksiri ise (SmallStrengthPotion, BigStrengthPotion)
        else if (item instanceof SmallStrengthPotion strPotion) {
            statInfo = " [Str Boost: +" + strPotion.getBonusDamage() + "]"; 
        }
        else if (item instanceof BigStrengthPotion strPotion) {
            statInfo = " [Str Boost: +" + strPotion.getBonusDamage() + "]";
        }

        // Konsola fiyattan önce hasar/etki bilgisini de yazdırıyoruz
        System.out.println((i + 1) + ". " + item.toString() + statInfo + " - Price: " + item.getPrice() + " Coin");
    }
}
}