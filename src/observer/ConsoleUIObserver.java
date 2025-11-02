package observer;

public class ConsoleUIObserver implements Observer {
    @Override
    public void update(String event) {
        if (event.contains("Battle started")) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("      BATTLE BEGINS     ");
            System.out.println("=".repeat(50) + "\n");
        } else if (event.startsWith("ENEMY_DEFEATED:")) {
            String enemyName = event.split(":")[1];
            System.out.println("\n" + "=".repeat(50));
            System.out.println("     VICTORY!     " + enemyName + " DEFEATED ");
            System.out.println("=".repeat(50) + "\n");
        } else if (event.equals("PLAYER_DEFEATED")) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("      DEFEAT      ");
            System.out.println("=".repeat(50) + "\n");
        }
    }
}
