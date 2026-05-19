public class BigHealthPotion extends Potion {

    private final int healAmount;

    public BigHealthPotion() {
        super(25);
        healAmount = 50;
    }

    @Override
    public void use(Entity target) {

        target.health += healAmount;

        System.out.println("Big health potion used. 50 HP restored");
    }

    @Override
    public String toString() {
        return "BigHealthPotion";
    }
    public int getHealAmount() {
    return this.healAmount;
}
}