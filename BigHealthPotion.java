public class BigHealthPotion extends Potion {

    private final int healAmount;

    public BigHealthPotion() {
        super(25);
        healAmount = 50;
    }

    @Override
    public void use(Entity target) {
        if (target == null) {
            return; 
        }

        if (target instanceof Hero hero) {
            //iksirin ne kadarı kullanıldıysa o kadar yazdır, kullanılmadıysa envanterde tutulduğunu belirt
            int result = hero.setHealth(hero.getHealth() + healAmount);

            if (result == 0) {
                System.out.println("\nHP is already full. " + this.toString() + " kept in the inventory.");
            } 
            else if (result != -1) {
                System.out.println("\n" + this.toString() + " used. " + result + " HP restored.");
            } 
            else {
                System.out.println("\n" + this.toString() + " used. " + healAmount + " HP restored.");
            }
        } 
        else {
            //Hero dışı için heal (şu an kullanılmadığı için ilkel kaldı)
            target.takeDamage(-healAmount);
            System.out.println("\n" + this.toString() + " used. " + healAmount + " HP restored.");
        }
    }


    @Override
    public String toString() {
        return "Big Health Potion";
    }
    public int getHealAmount() {
    return this.healAmount;
}
}