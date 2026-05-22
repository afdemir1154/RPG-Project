public class Hero extends Entity {

    
    protected int coinTotal;
    protected int maxHealth;
    protected Weapon equippedWeapon;
    protected Inventory inventory;
    protected double maxPower;
    
    public Hero(String name, int health, double power, int coinTotal, int maxHealth, double maxPower) {
        super(name, health, power);
        this.coinTotal = coinTotal;
        this.maxHealth = maxHealth;
        this.maxPower = maxPower;
        this.inventory = new Inventory();
    }

    @Override
    public void attack(Entity target) {
        double minDamage = this.power * 0.8;
        double maxDamage = this.power * 1.2;

        int finalDamage = (int) (minDamage + (random.nextDouble() * (maxDamage - minDamage)));

        boolean isCritical = random.nextInt(100) < 10;
        if (isCritical) {
            finalDamage *= 2;
            System.out.println("HIT RIGHT IN THE HEART!");
        }

        boolean isMiss = random.nextInt(100) < 5;
        if (isMiss) {
            finalDamage = 0;
            System.out.println("MISS");
        }
        
        System.out.println("" + this.name + " dealt " + finalDamage + " damage to " + target.name);

        target.takeDamage(finalDamage);
        resetPower();
    }


    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount); 

        if (amount > 0) {
            System.out.println(this.name + " took " + amount + " damage!\n");
        }
        else {
            System.out.println(this.name + " took no damage!\n");
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
                this.power -= this.equippedWeapon.getBonusDamage();
                this.inventory.addItem(this.equippedWeapon);
                System.out.println(this.equippedWeapon + " unequipped and returned to inventory.");
            }
            
            this.equippedWeapon = weapon1;
            weapon.equip();
            this.power += this.equippedWeapon.getBonusDamage();
            
            this.inventory.removeItem(weapon1);

            this.maxPower = power;
            
            System.out.println(this.equippedWeapon + " equipped! New Power: " + this.power);
        }
    }

    // GM ve Shop siniflarinin erisebilmesi icin gerekli Getters/Setters
    public String getName() { return this.name; }
    public int getHealth() { return this.health; }
    public int setHealth(int health) { 
        int diff = -1;
        if(health > maxHealth) { 
            diff = maxHealth - this.health;
            this.health = maxHealth;
        }
        else {
            this.health = health;
        }
        return diff;
    }
    public double getPower() { return this.power; }
    public void resetPower() {
        this.power = maxPower;
    }
    public int getCoinTotal() { return coinTotal; }
    public void addCoin(int amount) { this.coinTotal += amount; }
    public Inventory getInventory() { return inventory; }

    @Override
    public String toString() {
        return "Hero: " + name + " | HP: " + health + "/" + maxHealth + " | Power: " + power + " | Coin: " + coinTotal;
    }
}