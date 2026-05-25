public abstract class Foe extends Entity {

    public Foe(String name, int health, double power) {
        super(name, health, power);
    }

    public int getReward() {
        // Temel ödül (gücün 3 katı)
        int baseReward = (int) (this.getPower() * 3);
        
        // Ödül miktarı %80 ile %120 arasında olsun
        int minReward = (int) (baseReward * 0.8);
        int maxReward = (int) (baseReward * 1.3);
        
        return minReward + new java.util.Random().nextInt((maxReward - minReward) + 1);
    }
    @Override
    public String toString() {
        return ("Foe: " + this.getName() + " | Foe HP: " + this.getHealth());
    }
}