public class Hero extends Entity {

    
    protected int coinTotal;
    protected int maxHealth;
    protected Weapon equippedWeapon;
    protected Inventory inventory;
    
    public Hero(String name, int health, double power, int coinTotal, int maxHealth) {
        super(name, health, power);
        this.coinTotal = coinTotal;
        this.maxHealth = maxHealth;
        this.inventory = new Inventory();
    }

    
    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount); 
        System.out.println(this.name + " took damage! Current HP: " + this.health + "/" + this.maxHealth);
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
            equippedWeapon = weapon1;
            weapon.equip();
            // Silahın hasarını güce ekle
            this.power += equippedWeapon.bonusDamage;
            System.out.println(equippedWeapon + " equipped! New Power: " + this.power);
        }
    }

    // GM ve Shop siniflarinin erisebilmesi icin gerekli Getters/Setters
    public String getName() { return this.name; }
    public int getHealth() { return this.health; }
    public void setHealth(int health) { 
        this.health = health; 
        if(this.health > maxHealth) this.health = maxHealth;
    }
    public double getPower() { return this.power; }
    public int getCoinTotal() { return coinTotal; }
    public void addCoin(int amount) { this.coinTotal += amount; }
    public Inventory getInventory() { return inventory; }

    @Override
    public String toString() {
        return "Hero: " + name + " | HP: " + health + "/" + maxHealth + " | Power: " + power + " | Coin: " + coinTotal;
    }
}