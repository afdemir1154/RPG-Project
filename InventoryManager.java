import java.util.ArrayList;

public class InventoryManager {
    private final UIManager ui;

    public InventoryManager(UIManager ui) {
        this.ui = ui;
    }

    public boolean openInventoryMenu(Hero hero) {
        ui.showMessage("\n--- " + hero.getName() + "'S INVENTORY ---");
        hero.getInventory().displayItems();
        
        int itemChoice = ui.getNumberInput("Enter the number of the item to use/equip (0 to exit): ");
        
        if (itemChoice == 0) return false;
        if (itemChoice == -1) {
            ui.showMessage("Input Error: Please enter a valid NUMBER from the menu!");
            return false;
        }

        ArrayList<Tradeable> items = hero.getInventory().getItems();
        if (itemChoice < 1 || itemChoice > items.size()) {
            ui.showMessage("Invalid inventory number!");
            return false;
        }

        Tradeable chosenItem = items.get(itemChoice - 1);

        if (chosenItem instanceof Usable potion) {
            if (potion instanceof SmallStrengthPotion || potion instanceof BigStrengthPotion) {
                hero.useItem(potion, hero);
                hero.getInventory().removeItem(chosenItem);
            } else {
                int tempHealth = hero.getHealth();
                hero.useItem(potion, hero);
                
                if (tempHealth != hero.getHealth()) {
                    hero.getInventory().removeItem(chosenItem);
                }
            }
        } else if (chosenItem instanceof Equipable weapon) {
            hero.equipWeapon(weapon);
        }
        
        ui.showMessage("\n--- Updated Status ---");
        ui.showMessage(hero.toString());
        
        return false; 
    }
}