public abstract class Entity {

    private final String name;
    private int health;
    private double power;

    public Entity(String name, int health, double power) {
        this.name = name;
        this.health = health;
        this.power = power;
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
    protected void setEntityHealth(int health) { this.health = health; } //Sadece çocukların kullanmasını istiyoruz 
    public double getPower() { return power; }
    public void setPower(double power) { this.power = power; }
}