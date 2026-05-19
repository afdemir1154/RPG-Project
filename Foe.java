public abstract class Foe extends Entity {

    public Foe(String name, int health, double power) {
        super(name, health, power);
    }

    public int getReward() {
        // Temel ödül (eskisi gibi gücün 2 katı)
        int baseReward = (int) (power * 2);
        
        // Ödül miktarını %80 ile %120 arasında rastgele bir değere çekiyoruz
        int minReward = (int) (baseReward * 0.8);
        int maxReward = (int) (baseReward * 1.3);
        
        // minReward ile maxReward arasında rastgele bir değer üret
        return minReward + random.nextInt((maxReward - minReward) + 1);
    }
}