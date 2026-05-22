public class BigStrengthPotion extends Potion {

    private final double bonusDamage;

    public BigStrengthPotion() {
        super(30);
        bonusDamage = 25;
    }

    @Override
    public void use(Entity target) {

        target.power += bonusDamage;

        System.out.println("\n" + this.toString() + " used. " + bonusDamage + " bonus damage");
    }

    @Override
    public String toString() {
        return "Big Strength Potion";
    }
    public double getBonusDamage() {
    return this.bonusDamage;
}
}