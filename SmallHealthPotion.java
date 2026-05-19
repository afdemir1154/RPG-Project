public class SmallHealthPotion extends Potion {

    private final int healAmount;

    public SmallHealthPotion() {
        super(10);
        healAmount = 20;
    }

    @Override
    public void use(Entity target) {

        target.health += healAmount;

        System.out.println("Small health potion used. 20 HP restored");
    }

    @Override
    public String toString() {
        return "SmallHealthPotion";
    }
    public int getHealAmount() {
    return this.healAmount;
}
}