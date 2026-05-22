public class BigHealthPotion extends Potion {

    private final int healAmount;

    public BigHealthPotion() {
        super(25);
        healAmount = 50;
    }

    @Override
    public void use(Entity target) {
        if (target == null) {
            return; 
        }
        
        if (target instanceof Hero hero) {
            hero.setHealth(hero.getHealth() + healAmount);
            if (hero.setHealth(hero.getHealth() + healAmount) != -1 && hero.setHealth(hero.getHealth() + healAmount) != 0) {
                System.out.println("\n" + this.toString() + " used. " + hero.setHealth(hero.getHealth() + healAmount) + " HP restored.");
            }
            else if (hero.setHealth(hero.getHealth() + healAmount) == 0) {
                System.out.println("\n HP is already full. " + this.toString() + " returned to the inventory");
                hero.inventory.addItem(this);
            } 
            else {
                System.out.println("\n" + this.toString() + " used. " + healAmount + " HP restored.");
            }
        } 
        else {
            target.health += healAmount;
            System.out.println("\n" + this.toString() + " used. " + healAmount + " HP restored.");
        }
    }


    @Override
    public String toString() {
        return "Big Health Potion";
    }
    public int getHealAmount() {
    return this.healAmount;
}
}