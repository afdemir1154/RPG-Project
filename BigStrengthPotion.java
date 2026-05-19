public class BigStrengthPotion extends Potion {

    private final double bonusDamage;

    public BigStrengthPotion() {
        super(30);
        bonusDamage = 25;
    }

    @Override
    public void use(Entity target) {

        target.power += bonusDamage;

        System.out.println("Big strength potion used. +25 bonus damage");
    }

    @Override
    public String toString() {
        return "BigStrengthPotion";
    }
    public double getBonusDamage() {
    return this.bonusDamage;
}
}