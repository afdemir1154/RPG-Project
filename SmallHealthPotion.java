public class SmallHealthPotion extends Potion {

    private final int healAmount;

    public SmallHealthPotion() {
        super(10);
        healAmount = 20;
    }

    @Override
public void use(Entity target) {
    if (target == null) {
        return; 
    }
    
    if (target instanceof Hero hero) {
        int result = hero.setHealth(hero.getHealth() + healAmount);
        
        if (result == 0) {
            System.out.println("\nHP is already full. " + this.toString() + " kept in the inventory.");
        } 
        else if (result != -1) {
            System.out.println("\n" + this.toString() + " used. " + result + " HP restored.");
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
        return "Small Health Potion";
    }
    public int getHealAmount() {
    return this.healAmount;
}
}