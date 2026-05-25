import java.util.Random;
//Başta her şeyi GM yapıyordu fakat sonradan GM çok kabarınca birkaç managera bölmeye karar verdik. Single Responsibility'e uymaya çalıştık. 
public class CombatManager {
    private final UIManager ui;
    private final Random random;
//UI managera bağlı çalışıyor
    public CombatManager(UIManager ui) {
        this.ui = ui;
        this.random = new Random(); 
    }

    public void executeAttack(Entity attacker, Entity target) {
        //varyasyon için standart vuruşta base damage değerinin %80'i ile %120'si arasında hasar vuruluyor
        double minDamage = attacker.getPower() * 0.8;
        double maxDamage = attacker.getPower() * 1.2;

        int finalDamage = (int) (minDamage + (random.nextDouble() * (maxDamage - minDamage)));

        ui.showMessage(attacker.getName() + " attacks");
        
        int roll = random.nextInt(100); // 0 ile 99 arası zar atıyoruz
        
        if (roll < 5) {
            //%5 Miss ihtimali
            finalDamage = 0;
            ui.showMessage(attacker.getName() + " swung wildly and MISSED!");
        } else if (roll >= 5 && roll < 15) {
            //%10 Crit ihtimali
            finalDamage *= 2;
            ui.showMessage("HIT RIGHT IN THE HEART!");
            ui.showMessage(attacker.getName() + " dealt " + finalDamage + " damage to " + target.getName() + ".");
        } else {
            //%85 Standart Vuruş
            ui.showMessage(attacker.getName() + " dealt " + finalDamage + " damage to " + target.getName() + ".");
}
        
        target.takeDamage(finalDamage);
    }
}