public class SmallStrengthPotion extends Potion {

    private final double bonusDamage;

    public SmallStrengthPotion() {
        super(15);
        bonusDamage = 10;
    }

    @Override
    public void use(Entity target) {

        target.setPower(target.getPower() + this.bonusDamage);

        System.out.println("\n" + this.toString() + " used. " + bonusDamage + " extra damage");
    }

    @Override
    public String toString() {
        return "Small Strength Potion";
    }
    public double getBonusDamage() {
    return this.bonusDamage;
}
}