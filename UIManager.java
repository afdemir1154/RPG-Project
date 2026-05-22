import java.util.Scanner;

public class UIManager {
    private final Scanner scanner;

    public UIManager() {
        this.scanner = new Scanner(System.in);
    }

    // Ana menüyü gösterir ve oyuncunun seçimini String olarak döndürür
    public String getMainMenuChoice() {
        System.out.println("What would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Use Item / Equip Weapon (Inventory)");
        System.out.println("3. Go to Shop");
        System.out.print("Your choice: ");
        return scanner.nextLine().trim();
    }

    // Oyuncudan güvenli bir şekilde sayı (int) alır
    public int getNumberInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Hatalı girişi temsil etmesi için -1 
        }
    }

    // Sadece ekrana mesaj basmak için sarmalayıcı (wrapper) metot
    public void showMessage(String message) {
        System.out.println(message);
    }
}