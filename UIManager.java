import java.util.Scanner;

public class UIManager {//UI handling managerı
    private final Scanner scanner;

    public UIManager() {
        this.scanner = new Scanner(System.in);
    }

    // Ana menüyü gösterir ve oyuncunun seçimini string döndürür
    public String getMainMenuChoice() {
        System.out.println("What would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Use Item / Equip Weapon (Inventory)");
        System.out.println("3. Go to Shop");
        System.out.print("Your choice: ");
        return scanner.nextLine().trim();
    }

    // Oyuncudan input alır
    public int getNumberInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Hatalı girişi temsil etmesi için -1 
        }
    }

    //Print metodu 
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Oyunu belirtilen süre kadar duraklatır (Yazıların daha oyunvari akması için kullanıldı)
    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Hata kontrolü
        }
}
}