import java.util.ArrayList;

public class GM {
    private final Hero hero;
    private final Shop shop;
    private final ArrayList<Foe> currentEnemies;
    private final UIManager ui; //user interface manager
    private final CombatManager combat; //combat manager
    private final InventoryManager inventoryManager; //inventory manager
    private int wave = 1; 

    public GM() {
        this.hero = new Hero("FF", 100, 15.0, 50, 100, 15.0);
        this.shop = new Shop();
        this.currentEnemies = new ArrayList<>();
        this.ui = new UIManager();
        this.combat = new CombatManager(this.ui);
        this.inventoryManager = new InventoryManager(this.ui);

        ui.showMessage("----------- Your Adventure Has Begun! -----------");
        ui.showMessage("\n!!! A measly goblin stops you !!!\n");
        currentEnemies.add(new Goblin());
    }

    public void startGame() {
        boolean isGameOver = false;
        
        while (!isGameOver) {
            ui.showMessage(hero.toString());
            ui.showMessage(currentEnemies.toString());
            
            heroTurn();
            
            if (hero.isAlive() && !currentEnemies.isEmpty()) {
                enemyTurn();
            }
            
            if (!hero.isAlive()) {
                gameOver();
                isGameOver = true;
            } else if (currentEnemies.isEmpty()) {
                if (wave < 5) {
                    wave++;
                    switch (wave) {
                        case 2 -> {
                            ui.showMessage("\n!!! A ghoul lurks in the dark !!!\n");
                            currentEnemies.add(new Ghoul());
                        }
                        case 3 -> {
                            ui.showMessage("\n!!! A witch is approaching !!!\n");
                            currentEnemies.add(new Witch());
                        }
                        case 4 -> {
                            ui.showMessage("\n!!! IS THAT A CYCLOPS??? !!!\n");
                            currentEnemies.add(new Cyclops());
                        }
                        case 5 -> {
                            ui.showMessage("!!! Medusa... !!!\n");
                            currentEnemies.add(new Medusa());
                        }
                    }
                } else {
                    ui.showMessage("\n=================================================");
                    ui.showMessage("You Saved The Kingdom!");
                    ui.showMessage("Your Final Score: " + hero.getCoinTotal());
                    ui.showMessage("=================================================");
                    isGameOver = true;
                }
            }
        }
    }

    public void heroTurn() { 
        boolean actionTaken = false;
        while (!actionTaken) {
            ui.showMessage("\nYour Turn!");
            actionTaken = handleMenuInput(); 
        }
    }

    public void enemyTurn() {
        ui.showMessage("\n\nEnemy Turn!\n");
    
        for (Foe foe : currentEnemies) {
            if (foe.isAlive() && hero.isAlive()) {
                combat.executeAttack(foe, hero);
                ui.showMessage("-------------------------------------------------\n");
            }
        }
    }

    public boolean handleMenuInput() {
        String choice = ui.getMainMenuChoice();

        switch (choice) {
            case "1" -> {
                try {
                    if (currentEnemies.isEmpty()) {
                        throw new DeadCharacterException("There are no alive enemies left to attack!");
                    }
                    Foe target = currentEnemies.get(0);
                    ui.showMessage("\n" + hero.getName() + " charges at " + target.getName() + "!");

                    combat.executeAttack(hero, target);

                    hero.resetPower(); 

                    if (!target.isAlive()) {
                        ui.showMessage(target.getName() + " is dead meat! You earned " + target.getReward() + " coins.");
                        hero.addCoin(target.getReward());
                        currentEnemies.remove(target);
                        ui.showMessage("\n-------------------------------------------------");
                    }
                    return true;
                
                } catch (DeadCharacterException e) {
                    ui.showMessage("Game State Error: " + e.getMessage());
                    return false;
                }
            }
            case "2" -> {
                boolean result = inventoryManager.openInventoryMenu(hero);
                ui.showMessage(currentEnemies.toString()); 
                return result; 
            } 
            case "3" -> {
                openShopMenu();
                return false; 
            }
            default -> {
                ui.showMessage("Invalid choice, please try again.");
                return false; 
            }
        }
    }

    private void openShopMenu() {
        shop.displayShop();
        int shopChoice = ui.getNumberInput("Enter item number to buy (0 to Exit): ");
        
        if (shopChoice == 0) return;
        if (shopChoice == -1) {
            ui.showMessage("Input Error: Please enter a valid number to select an item!");
            return;
        }

        try {
            shop.buyItem(hero, shopChoice - 1);
        } catch (InsufficientCoinException e) {
            ui.showMessage("Shop Operation Error: " + e.getMessage());
        }
    }

    public void gameOver() {
        ui.showMessage("\n=== GAME OVER ===");
        ui.showMessage("Our hero " + hero.getName() + " could not withstand their wounds and succumbed...");
    }

    @Override
    public String toString() {
        return "Game Manager Status: Hero HP=" + hero.getHealth() + ", Enemies left=" + currentEnemies.size();
    }
}