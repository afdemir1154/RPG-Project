public class SmallStrengthPotion extends Potion {

    private double bonusDamage;

    public SmallStrengthPotion() {
        super(15);
        bonusDamage = 5;
    }

    @Override
    public void use(Entity target) {

        target.power += bonusDamage;

        System.out.println("Small strength potion used.");
    }

    @Override
    public String toString() {
        return "SmallStrengthPotion";
    }
}