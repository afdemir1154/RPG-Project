import java.util.Random;

public class CombatManager {
    private final UIManager ui;
    private final Random random;

    public CombatManager(UIManager ui) {
        this.ui = ui;
        this.random = new Random(); 
    }

    public void executeAttack(Entity attacker, Entity target) {
        double minDamage = attacker.getPower() * 0.8;
        double maxDamage = attacker.getPower() * 1.2;

        int finalDamage = (int) (minDamage + (random.nextDouble() * (maxDamage - minDamage)));
        // Crit şansı %10
        boolean isCritical = random.nextInt(100) < 10;
        if (isCritical) {
            finalDamage *= 2;
            ui.showMessage("HIT RIGHT IN THE HEART!");
        }

        // Iska şansı %5
        boolean isMiss = random.nextInt(100) < 5;
        if (isMiss) {
            finalDamage = 0;
            ui.showMessage(attacker.getName() + " swung wildly and MISSED!");
        }

        if (!isMiss) {
            ui.showMessage(attacker.getName() + " dealt " + finalDamage + " damage to " + target.getName() + ".");
        }
        
        target.takeDamage(finalDamage);
    }
}