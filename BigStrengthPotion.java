public class BigStrengthPotion extends Potion {

    private double bonusDamage;

    public BigStrengthPotion() {
        super(30);
        bonusDamage = 12;
    }

    @Override
    public void use(Entity target) {

        target.power += bonusDamage;

        System.out.println("Big strength potion used.");
    }

    @Override
    public String toString() {
        return "BigStrengthPotion";
    }
}