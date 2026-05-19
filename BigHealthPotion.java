public class BigHealthPotion extends Potion {

    private int healAmount;

    public BigHealthPotion() {
        super(25);
        healAmount = 50;
    }

    @Override
    public void use(Entity target) {

        target.health += healAmount;

        System.out.println("Big health potion used.");
    }

    @Override
    public String toString() {
        return "BigHealthPotion";
    }
}