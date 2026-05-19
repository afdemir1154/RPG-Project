public class SmallHealthPotion extends Potion {

    private int healAmount;

    public SmallHealthPotion() {
        super(10);
        healAmount = 20;
    }

    @Override
    public void use(Entity target) {

        target.health += healAmount;

        System.out.println("Small health potion used.");
    }

    @Override
    public String toString() {
        return "SmallHealthPotion";
    }
}