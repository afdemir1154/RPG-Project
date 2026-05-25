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

        ui.showMessage(attacker.getName() + " attacks");
        
        int roll = random.nextInt(100); // 0 ile 99 arası tek bir zar
        
        if (roll < 5) {
            //%5 Miss
            finalDamage = 0;
            ui.showMessage(attacker.getName() + " swung wildly and MISSED!");
        } else if (roll >= 5 && roll < 15) {
            //%10 Crit
            finalDamage *= 2;
            ui.showMessage("HIT RIGHT IN THE HEART!");
            ui.showMessage(attacker.getName() + " dealt " + finalDamage + " damage to " + target.getName() + ".");
        } else {
            //%85 Normal Vuruş
            ui.showMessage(attacker.getName() + " dealt " + finalDamage + " damage to " + target.getName() + ".");
}
        
        target.takeDamage(finalDamage);
    }
}