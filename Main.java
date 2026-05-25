public class Main {//Sadece hoşgeldiniz diyip işi GM'ye devrediyor 
    public static void main(String[] args) {
        System.out.println("---- Welcome to the Fantasy Quest Adventure  ----");
   

        
        GM gameManager = new GM();

       
        gameManager.startGame();
    }
}