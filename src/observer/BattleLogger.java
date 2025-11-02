package observer;

public class BattleLogger implements Observer {
    @Override
    public void update(String event) {
        if (event.startsWith("ENEMY_DEFEATED:") || event.equals("PLAYER_DEFEATED")) {
            return;
        }

        System.out.println("[BATTLE LOG] " + event + "\n");
    }
}
