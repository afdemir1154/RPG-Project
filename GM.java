import java.util.ArrayList;
import java.util.Scanner;

public class GM {
    private Hero hero;
    private Shop shop;
    private ArrayList<Foe> currentEnemies;
    private Scanner scanner;
    private int wave = 1; // 3 Farkli karsilasma takibi icin

    public GM() {
        this.hero = new Hero("Funda", 100, 15.0, 50, 100);
        this.shop = new Shop();
        this.currentEnemies = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        // İlk dusman
        System.out.println("Your Adventure Has Begun!");
        System.out.println("\n!!! : A measly goblin stops you !!!");
        currentEnemies.add(new Goblin());
    }

    public void startGame() {
        boolean isGameOver = false;

        while (!isGameOver) {
            System.out.println("\n--- (Wave " + wave + ") ---");
            System.out.println(hero.toString());

            heroTurn();

            // Eğer dusman olduyse sonraki dusmani getir
            if (currentEnemies.isEmpty() && wave < 5) {
                wave++;
                if (wave == 2) {
                    System.out.println("\n!!! : A ghoul appeared !!!");
                    currentEnemies.add(new Ghoul());
                } else if (wave == 3) {
                    System.out.println("\n!!! : A witch is approaching !!!");
                    currentEnemies.add(new Witch());
                } else if (wave == 4) {
                    System.out.println("\n!!! : IS THAT A CYCLOPS??? !!!");
                    currentEnemies.add(new Cyclops());
                } else if(wave == 5) {
                    System.out.println("!!! : Medusa... !!!");
                    currentEnemies.add(new Medusa());
                }
            }

            if (hero.isAlive() && !currentEnemies.isEmpty()) {
                enemyTurn();
            }

            if (!hero.isAlive()) {
                gameOver();
                isGameOver = true;
            } else if (currentEnemies.isEmpty() && wave >= 5) {
                System.out.println("\n=================================================");
                System.out.println("You Saved The Kingdom!");
                System.out.println("Your Final Score: " + hero.getCoinTotal());
                System.out.println("=================================================");
                isGameOver = true;
            }
        }
        scanner.close();
    }

    public void heroTurn() {
        System.out.println("\nYour Turn!");
        handleMenuInput(); 
    }

    public void enemyTurn() {
        System.out.println("\nEnemy Turn!");
        
        for (Foe foe : currentEnemies) {
            if (foe.isAlive()) {
                System.out.println(foe.toString() + " Attacks you!");
                foe.attack(hero);
            }
        }
    }

    public void handleMenuInput() {
        System.out.println("What Would You Like To Do?");
        System.out.println("1. Attack");
        System.out.println("2. Open Inventory");
        System.out.println("3. Visit The Shop");
        System.out.println("Your Choice: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                try {
                    if (currentEnemies.isEmpty()) {
                        throw new DeadCharacterException("There Are No Foes To Attack!");
                    }
                    Foe target = currentEnemies.get(0);
                    System.out.println("\n" + hero.getName() + " approaches and attacks to " + target.toString() + " vigorously! ");
                    hero.attack(target);
                    
                    if (!target.isAlive()) {
                        System.out.println(target.toString() + " is dead meat! " + target.getReward() + " coins looted.");
                        hero.addCoin(target.getReward());
                        currentEnemies.remove(target);
                    }
                } catch (DeadCharacterException e) {
                    System.out.println("Dead Character Error: " + e.getMessage());
                }
                break;

            case "2":
                openInventoryMenu();
                break;

            case "3":
                openShopMenu();
                break;

            default:
                System.out.println("Invalid selection! You lost your turn due to carelessness.");
                break;
        }
    }

    private void openInventoryMenu() {
        System.out.println("\n--- HERO'S INVENTORY ---");
        hero.getInventory().displayItems();
        System.out.print("Select item number to equip (0 to Exit):  ");
        
       
        try {
            int itemChoice = Integer.parseInt(scanner.nextLine().trim());
            if (itemChoice == 0) return;

            ArrayList<Tradeable> items = hero.getInventory().getItems();
            if (itemChoice < 1 || itemChoice > items.size()) {
                System.out.println("Invalid inventory number!");
                return;
            }

            Tradeable chosenItem = items.get(itemChoice - 1);

           
            if (chosenItem instanceof Usable) {
                Usable potion = (Usable) chosenItem;
                hero.useItem(potion, hero);
                hero.getInventory().removeItem(chosenItem); // İksir kullanildiktan sonra tuketilir
            } else if (chosenItem instanceof Equipable) {
                Equipable weapon = (Equipable) chosenItem;
                hero.equipWeapon(weapon);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input Error: Please enter a valid menu number!");
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