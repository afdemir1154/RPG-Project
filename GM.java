import java.util.ArrayList;
import java.util.Scanner;

public class GM {
    private final Hero hero;
    private final Shop shop;
    private final ArrayList<Foe> currentEnemies;
    private final Scanner scanner;
    private int wave = 1; 

    public GM() {
        this.hero = new Hero("FF", 100, 15.0, 50, 100, 15.0);
        this.shop = new Shop();
        this.currentEnemies = new ArrayList<>();
        this.scanner = new Scanner(System.in);


        // İlk dusman
        System.out.println("----------- Your Adventure Has Begun! -----------");
        System.out.println("\n!!! A measly goblin stops you !!!\n");
        currentEnemies.add(new Goblin());
    }

    public void startGame() {
        try (scanner) {
            boolean isGameOver = false;
            
            while (!isGameOver) {
                System.out.println(hero.toString());
                System.out.println(currentEnemies.toString());
                
                // 1. KAHRAMANIN TURU
                heroTurn();
                
                // 2. DÜŞMANIN TURU (Eğer kahraman hayattaysa ve listede hala canlı düşman varsa)
                if (hero.isAlive() && !currentEnemies.isEmpty()) {
                    enemyTurn();
                }
                
                if (!hero.isAlive()) {
                    gameOver();
                    isGameOver = true;
                } else if (currentEnemies.isEmpty()) {
                    // Eğer tüm düşmanlar öldüyse ve son dalgada değilsek yeni dalgayı getir
                    if (wave < 5) {
                        wave++;
                        switch (wave) {
                            case 2 -> {
                                System.out.println("\n!!! A ghoul lurks in the dark !!!\n");
                                currentEnemies.add(new Ghoul());
                            }
                            case 3 -> {
                                System.out.println("\n!!! A witch is approaching !!!\n");
                                currentEnemies.add(new Witch());
                            }
                            case 4 -> {
                                System.out.println("\n!!! IS THAT A CYCLOPS??? !!!\n");
                                currentEnemies.add(new Cyclops());
                            }
                            case 5 -> {
                                System.out.println("!!! Medusa... !!!\n");
                                currentEnemies.add(new Medusa());
                            }
                            default -> {
                            }
                        }
                    } else {
                        // Dalga 5 bittiyse ve düşman kalmadıysa oyunu kazan
                        System.out.println("\n=================================================");
                        System.out.println("You Saved The Kingdom!");
                        System.out.println("Your Final Score: " + hero.getCoinTotal());
                        System.out.println("=================================================");
                        isGameOver = true;
                    }
                }
            }
        }
    }

    public void heroTurn() { 
        boolean actionTaken = false;
        
        while (!actionTaken) {
            System.out.println("\nYour Turn!");
            actionTaken = handleMenuInput(); 
        }
    }

    public void enemyTurn() {
        System.out.println("\n\nEnemy Turn!\n");
        
        for (Foe foe : currentEnemies) {
            if (foe.isAlive()) {
                System.out.println(foe.name + " Attacks " + hero.name + "!");
                foe.attack(hero);
                System.out.println("-------------------------------------------------\n");
            }
        }
    }

    public boolean handleMenuInput() {
        System.out.println("What would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Use Item / Equip Weapon (Inventory)");
        System.out.println("3. Go to Shop");
        System.out.print("Your choice: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> {
                try {
                    if (currentEnemies.isEmpty()) {
                        throw new DeadCharacterException("There are no alive enemies left to attack!");
                    }
                    Foe target = currentEnemies.get(0);
                    System.out.println("\n" + hero.getName() + " charges and attacks " + target.name + "!");
                    hero.attack(target);
                    
                    if (!target.isAlive()) {
                        System.out.println(target.name + " is dead! You earned " + target.getReward() + " coins.");
                        hero.addCoin(target.getReward());
                        currentEnemies.remove(target);
                        System.out.println("\n-------------------------------------------------");
                    }
                    return true;

                } catch (DeadCharacterException e) {
                    System.out.println("Game State Error: " + e.getMessage());
                    return false;
                }
            }

            case "2" -> {
                return openInventoryMenu(); 
            }

            case "3" -> {
                openShopMenu();
                return false; 
            }

            default -> {
                System.out.println("Invalid choice, please try again.");
                return false; 
            }
        }
    }

    private boolean openInventoryMenu() {
        System.out.println("\n--- " + hero.name+"'S INVENTORY ---");
        hero.getInventory().displayItems();
        System.out.print("Enter the number of the item to use/equip (0 to exit): ");
        
        try {
            int itemChoice = Integer.parseInt(scanner.nextLine().trim());
            if (itemChoice == 0) return false;

            ArrayList<Tradeable> items = hero.getInventory().getItems();
            if (itemChoice < 1 || itemChoice > items.size()) {
                System.out.println("Invalid inventory number!");
                return false;
            }

            Tradeable chosenItem = items.get(itemChoice - 1);

           if (chosenItem instanceof Usable potion) {
                if (potion instanceof SmallStrengthPotion || potion instanceof BigStrengthPotion) {
                    hero.useItem(potion, hero);
                    hero.getInventory().removeItem(chosenItem);
                }
                // Can değişiyor mu 
                else {
                    int tempHealth = hero.getHealth();
                    hero.useItem(potion, hero);
                    
                    if (tempHealth != hero.getHealth()) {
                        // Can değiştiyse kullanılmıştır envanterden sil.
                        hero.getInventory().removeItem(chosenItem);
                    }
                }
            } else if (chosenItem instanceof Equipable weapon) {
                hero.equipWeapon(weapon);
            }
            
            System.out.println("\n--- Updated Status ---");
            System.out.println(hero.toString());
            System.out.println(currentEnemies.toString());

            
            return false; 

        } catch (NumberFormatException e) {
            System.out.println("Input Error: Please enter a valid NUMBER from the menu!");
            return false;
        }
    }

    private void openShopMenu() {
        shop.displayShop();
        System.out.print("Enter item number to buy (0 to Exit): ");
        

        try {
            int shopChoice = Integer.parseInt(scanner.nextLine().trim());
            if (shopChoice == 0) return;

           
            shop.buyItem(hero, shopChoice - 1);

        } catch (NumberFormatException e) {
            System.out.println("Input Error: Please enter a valid number to select an item!");
        } catch (InsufficientCoinException e) {
            System.out.println("Shop Operation Error: " + e.getMessage());
        }
    }

    public void gameOver() {
        System.out.println("\n=== GAME OVER ===");
        System.out.println("Our hero " + hero.getName() + " could not withstand their wounds and succumbed...");
    }

    @Override
    public String toString() {
        return "Game Manager Status: Hero HP=" + hero.getHealth() + ", Enemies left=" + currentEnemies.size();
    }
}