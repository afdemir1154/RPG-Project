public abstract class Weapon implements Equipable, Tradeable {

    private final int price;
    private final double bonusDamage;

    public Weapon(int price, double bonusDamage) {
        this.price = price;
        this.bonusDamage = bonusDamage;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void equip() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + price;
        long temp;
        temp = Double.doubleToLongBits(bonusDamage);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Weapon other = (Weapon) obj;
        if (price != other.price)
            return false;
        return Double.doubleToLongBits(bonusDamage) == Double.doubleToLongBits(other.bonusDamage);
    }
    
    public double getBonusDamage() {
    return this.bonusDamage;
    }
}