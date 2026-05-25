import java.util.ArrayList;

public class GM {//GM 4 managera bölündü
    private final Hero hero;
    private final Shop shop;
    private final ArrayList<Foe> currentEnemies;
    private final UIManager ui; //user interface manager
    private final CombatManager combat; //combat manager
    private final InventoryManager inventoryManager; //inventory manager
    private int wave = 1; 

    public GM() {
        this.hero = new Hero("Fufa", 100, 20.0, 50, 100, 20.0);
        this.shop = new Shop();
        this.currentEnemies = new ArrayList<>();
        this.ui = new UIManager();
        this.combat = new CombatManager(this.ui);
        this.inventoryManager = new InventoryManager(this.ui);

        ui.showMessage("----------- Your Adventure Has Begun! -----------");
        ui.showMessage("\n\n!!! A measly goblin stops you !!!\n");
        currentEnemies.add(new Goblin());
    }

    public void startGame() {
        boolean isGameOver = false;
        
        while (!isGameOver) {
            ui.pause(1000);
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
                //wave sistemi oyunun akışını kontrol ediyor ve bir düşman öldükten sonra yenisini getiriyor
                if (wave < 5) {
                    wave++;
                    switch (wave) {
                        case 2 -> {
                            ui.pause(1000);
                            ui.showMessage("\n!!! A ghoul lurks in the dark !!!\n");
                            ui.pause(1000);
                            currentEnemies.add(new Ghoul());
                        }
                        case 3 -> {
                            ui.pause(1000);
                            ui.showMessage("\n!!! A witch is approaching !!!\n");
                            ui.pause(1000);
                            currentEnemies.add(new Witch());
                        }
                        case 4 -> {
                            ui.pause(1000);
                            ui.showMessage("\n!!! IS THAT A CYCLOPS??? !!!\n");
                            ui.pause(1000);
                            currentEnemies.add(new Cyclops());
                        }
                        case 5 -> {
                            ui.pause(1000);
                            ui.showMessage("!!! Medusa... !!!\n");
                            ui.pause(1000);
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

    private void heroTurn() { 
        boolean actionTaken = false;
        while (!actionTaken) {
            ui.showMessage("\nYour Turn!");
            actionTaken = handleMenuInput(); 
        }
    }

    private void enemyTurn() {
        ui.showMessage("\n-------------------------------------------------\n");
        ui.showMessage("Enemy Turn!\n");
        ui.pause(2000);
    
        for (Foe foe : currentEnemies) {
            if (foe.isAlive() && hero.isAlive()) {
                combat.executeAttack(foe, hero);
                ui.showMessage("\n-------------------------------------------------\n");
            }
        }
    }

    private boolean handleMenuInput() {
        String choice = ui.getMainMenuChoice();
        //Arayüzden input alarak aksiyona çeviren motor
        switch (choice) {
            case "1" -> {
                try {
                    if (currentEnemies.isEmpty()) {
                        throw new DeadCharacterException("There are no alive enemies left to attack!");
                    }
                    Foe target = currentEnemies.get(0);
                    ui.showMessage("\n" + hero.getName() + " charges at " + target.getName() + "!");

                    ui.pause(1000);

                    combat.executeAttack(hero, target);

                    hero.resetPower(); //hero atak yaptıktan sonra potion etkisini geçirmek için gücü tekrardan eskiye döndürüyor

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
        shop.displayShop(hero);
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

    private void gameOver() {
        ui.showMessage("\n=== GAME OVER ===");
        ui.showMessage("Our hero " + hero.getName() + " could not withstand their wounds and succumbed...");
    }

    @Override
    public String toString() {
        return "Game Manager Status: Hero HP=" + hero.getHealth() + ", Enemies left=" + currentEnemies.size();
    }
}