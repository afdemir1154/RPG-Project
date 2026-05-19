public abstract class Weapon implements Equipable, Tradeable {

    protected int price;
    protected double bonusDamage;

    public Weapon(int price, double bonusDamage) {
        this.price = price;
        this.bonusDamage = bonusDamage;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void equip() {
        System.out.println("Weapon equipped.");
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Weapon weapon = (Weapon) obj;

        return price == weapon.price &&
                bonusDamage == weapon.bonusDamage;
    }
}