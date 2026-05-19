public class SmallStrengthPotion extends Potion {

    private final double bonusDamage;

    public SmallStrengthPotion() {
        super(15);
        bonusDamage = 10;
    }

    @Override
    public void use(Entity target) {

        target.power += bonusDamage;

        System.out.println("Small strength potion used. +10 bonus damage");
    }

    @Override
    public String toString() {
        return "SmallStrengthPotion";
    }
    public double getBonusDamage() {
    return this.bonusDamage;
}
}