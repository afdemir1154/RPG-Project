import java.util.Random;

public abstract class Entity {

    private final String name;
    private int health;
    private double power;
    private final Random random;

    public Entity(String name, int health, double power) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.random = new Random();
    }

    public void attack(Entity target) {
        double minDamage = this.power * 0.8;
        double maxDamage = this.power * 1.2;

        int finalDamage = (int) (minDamage + (random.nextDouble() * (maxDamage - minDamage)));

        boolean isCritical = random.nextInt(100) < 10;
        if (isCritical) {
            finalDamage *= 2;
            System.out.println("HIT RIGHT IN THE HEART!");
        }

        boolean isMiss = random.nextInt(100) < 5;
        if (isMiss) {
            finalDamage = 0;
            System.out.println("MISS");
        }
        
        target.takeDamage(finalDamage);
    }

    public void takeDamage(int amount) {
        health -= amount;

        if (health < 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public void setEntityHealth(int health) { this.health = health; } 
    public double getPower() { return power; }
    public void setPower(double power) { this.power = power; }
    public Random getRandom() { return random; }
}