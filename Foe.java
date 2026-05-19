public abstract class Foe extends Entity {

    public Foe(String name, int health, int power) {
        super(name, health, power);
    }

    public int getReward() {
        return (int) power * 2;
    }
}