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
            // Hero sınıfındaki setHealth, maxHealth sınırını otomatik kontrol eder
            hero.setHealth(hero.getHealth() + healAmount);
        } else {
            target.health += healAmount;
        }
        System.out.println(this.toString() + " used. " + healAmount + " HP restored.");
    }

    @Override
    public String toString() {
        return "SmallHealthPotion";
    }
    public int getHealAmount() {
    return this.healAmount;
}
}