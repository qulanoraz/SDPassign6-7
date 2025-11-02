package observer;

public class GameStatsTracker implements Observer {
    private int enemiesDefeated = 0;
    private int skillsUsed = 0;

    @Override
    public void update(String event) {
        if (event.startsWith("ENEMY_DEFEATED:")) {
            enemiesDefeated++;
            System.out.println("[STATS] Total enemies defeated: " + enemiesDefeated);
        } else if (event.contains("uses")) {
            skillsUsed++;
        }
    }

    public void printFinalStats() {
        System.out.println("\n=== GAME STATISTICS ===");
        System.out.println("Enemies Defeated: " + enemiesDefeated);
        System.out.println("Skills Used: " + skillsUsed);
        System.out.println("======================");
    }
}
