public class Hero extends Entity {

    private int coinTotal;
    private int maxHealth;
    private Weapon equippedWeapon;
    private final Inventory inventory;
    private double maxPower;
    
    public Hero(String name, int health, double power, int coinTotal, int maxHealth, double maxPower) {
        super(name, health, power);
        this.coinTotal = coinTotal;
        this.maxHealth = maxHealth;
        this.maxPower = maxPower;
        this.inventory = new Inventory();
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount); 

        if (amount > 0) {
            System.out.println(this.getName() + " took " + amount + " damage!\n");
        }
        else {
            System.out.println(this.getName() + " took no damage!\n");
        }
    }

    public boolean spendCoin(int amount) {
        if (coinTotal >= amount) {
            coinTotal -= amount;
            return true;
        }
        return false;
    }

    public void useItem(Usable item, Entity target) {
        item.use(target);
    }

    public void equipWeapon(Equipable weapon) {
        if (weapon instanceof Weapon weapon1) {
            
            if (this.equippedWeapon != null) {
                this.setPower (getPower() - this.equippedWeapon.getBonusDamage());
                this.inventory.addItem(this.equippedWeapon);
                System.out.println(this.equippedWeapon + " unequipped and returned to inventory.");
            }
            
            this.equippedWeapon = weapon1;
            weapon.equip();
            this.setPower (getPower() + this.equippedWeapon.getBonusDamage());
            
            this.inventory.removeItem(weapon1);

            this.maxPower = this.getPower();
            
            System.out.println("\n" + this.equippedWeapon + " equipped! New Power: " + this.getPower());
        }
    }

    // GM ve Shop siniflarinin erisebilmesi icin gerekli Getters/Setters
    public int setHealth(int health) { 
        int diff = -1;
        if(health > maxHealth) { 
            diff = this.maxHealth - this.getHealth();
            this.setHealth(maxHealth);
        }
        else {
            this.setEntityHealth(health);
        }
        return diff;
    }
    public void resetPower() {
        this.setPower(maxPower);
    }
    public int getCoinTotal() { return coinTotal; }
    public void addCoin(int amount) { this.coinTotal += amount; }
    public Inventory getInventory() { return inventory; }

    @Override
    public String toString() {
        return "Hero: " + this.getName() + " | HP: " + this.getHealth() + "/" + this.maxHealth + " | Power: " + this.getPower() + " | Coin: " + this.coinTotal;
    }
}