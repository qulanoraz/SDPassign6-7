import decorator.*;
import model.*;
import factory.HeroFactory;
import model.Map;
import observer.*;
import skill.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GameEventManager eventManager = new GameEventManager();

    public static void main(String[] args) {
        GameStatsTracker statsTracker = new GameStatsTracker();
        eventManager.addObserver(new BattleLogger());
        eventManager.addObserver(statsTracker);
        eventManager.addObserver(new ConsoleUIObserver());

        Map map = new Map();
        map.placeElement(new Wall(), 1, 0);
        map.placeElement(new Wall(), 1, 1);
        map.placeElement(new Wall(), 1, 2);
        map.placeElement(new Wall(), 3, 0);
        map.placeElement(new Wall(), 3, 1);
        map.placeElement(new Wall(), 4, 1);
        map.placeElement(new Wall(), 7, 1);

        map.placeElement(new Wall(), 2, 4);
        map.placeElement(new Wall(), 3, 4);
        map.placeElement(new Wall(), 4, 4);
        map.placeElement(new Wall(), 5, 4);
        map.placeElement(new Wall(), 6, 4);
        map.placeElement(new Wall(), 7, 4);

        map.placeElement(new Wall(), 2, 5);
        map.placeElement(new Wall(), 2, 7);

        map.placeElement(new Buff("damage"), 0, 0);
        map.placeElement(new Buff("health"), 0, 1);

        map.placeElement(new Buff("shield"), 7, 0);
        map.placeElement(new Buff("health"), 6, 0);

        map.placeElement(new Buff("shield"), 7, 5);
        map.placeElement(new Buff("health"), 7, 6);
        map.placeElement(new Buff("damage"), 7, 7);


        String type = "";
        String heroClass = "";

        Set<String> meleeClasses = Set.of("warrior");
        Set<String> rangedClasses = Set.of("archer");
        Set<String> magicClasses = Set.of("mage");

        while (true) {
            System.out.println("Choose hero type (melee, ranged, magic):");
            type = scanner.nextLine().toLowerCase();
            if (type.equals("melee") || type.equals("ranged") || type.equals("magic")) {
                break;
            }
            System.out.println("Invalid hero type. Please choose melee, ranged or magic.");
        }

        while (true) {
            System.out.println("Choose hero class:");
            if (type.equals("melee")) {
                System.out.println("Available classes: warrior");
                heroClass = scanner.nextLine().toLowerCase();
                if (meleeClasses.contains(heroClass)) {
                    break;
                }
            } else if (type.equals("ranged")) {
                System.out.println("Available classes: archer");
                heroClass = scanner.nextLine().toLowerCase();
                if (rangedClasses.contains(heroClass)) {
                    break;
                }
            } else if (type.equals("magic")) {
                System.out.println("Available classes: mage");
                heroClass = scanner.nextLine().toLowerCase();
                if (magicClasses.contains(heroClass)) {
                    break;
                }
            }
            System.out.println("Invalid hero class for the selected type, please try again.");
        }


        Hero player = HeroFactory.createHero(type, heroClass, 2, 0, true);
        player.setEventManager(eventManager);
        map.placeElement(player, 2, 0);

        // Enemies creation
        Hero enemy1 = HeroFactory.createHero("melee", "warrior", 0, 2, false);
        enemy1.setEventManager(eventManager);
        map.placeElement(enemy1, 0, 2);

        Hero enemy2 = HeroFactory.createHero("magic", "mage", 5, 1, false);
        enemy2.setEventManager(eventManager);
        map.placeElement(enemy2, 5, 1);

        Hero enemy3 = HeroFactory.createHero("ranged", "archer", 6, 1, false);
        enemy3.setEventManager(eventManager);
        map.placeElement(enemy3, 6, 1);

        Hero enemy4 = HeroFactory.createHero("melee", "warrior", 3, 6, false);
        enemy4.setEventManager(eventManager);
        map.placeElement(enemy4, 3, 6);

        Hero enemy5 = HeroFactory.createHero("melee", "warrior", 3, 5, false);
        enemy5.setEventManager(eventManager);
        map.placeElement(enemy5, 3, 5);

        Hero enemy6 = HeroFactory.createHero("melee", "warrior", 3, 7, false);
        enemy6.setEventManager(eventManager);
        map.placeElement(enemy6, 3, 7);

        eventManager.notifyObservers("Game started!");

        boolean running = true;
        while(running) {
            map.display();
            System.out.println("\nYour HP: " + player.getHp() + " | Damage: " + player.getDamage()  );
            System.out.println("Enter move: W/A/S/D or Q to quit");
            String move = scanner.nextLine().toLowerCase();

            int newX = player.getX();
            int newY = player.getY();

            switch (move) {
                case "w": newY -= 1; break;
                case "a": newX -= 1; break;
                case "s": newY += 1; break;
                case "d": newX += 1; break;
                case "q":
                    running = false;
                    eventManager.notifyObservers("Game ended by Player");
                    continue;
                default:
                    System.out.println("Wrong input");
                    continue;
            }

            if (!map.isValidPosition(newX, newY)) {
                System.out.println("Out of map!");
                continue;
            }

            MapElement element = map.getElementAt(newX, newY);

            if (element instanceof Buff buff) {
                player = applyBuff(player, buff);
                map.placeElement(null, newX, newY);
                map.moveHero(player, newX, newY);
                eventManager.notifyObservers("Player picked up " + buff.getBuffType() + " buff!");
            }

            if(element instanceof Hero enemyHero && enemyHero != player) {
                eventManager.notifyObservers("Battle started between " +
                        player.getClass().getSimpleName() + " and " +
                        enemyHero.getClass().getSimpleName());

                battleLoop(player, enemyHero, map);

                if(!player.isAlive()) {
                    running = false;
                }
                if(!enemyHero.isAlive()) {
                    map.removeElementAt(enemyHero.getX(), enemyHero.getY());
                }
            } else if (map.isPassable(newX, newY)) {
                map.moveHero(player, newX, newY);
                eventManager.notifyObservers("Player moved to (" + newX + ", " + newY + ")");
            } else {
                System.out.println("Can't move there!");
            }
        }

        statsTracker.printFinalStats();
    }

    private static Hero applyBuff(Hero hero, Buff buff) {
        switch (buff.getBuffType()) {
            case "damage":
                DamageBuff damageBuff = new DamageBuff(hero, 5);
                System.out.println("⚔ " + damageBuff.getBuffDescription());
                return damageBuff;
            case "health":
                HealthBuff healthBuff = new HealthBuff(hero, 20);
                System.out.println("❤ " + healthBuff.getBuffDescription());
                return healthBuff;
            case "defense":
                ShieldBuff shieldBuff = new ShieldBuff(hero, 0.2);
                System.out.println("🛡 " + shieldBuff.getBuffDescription());
                return shieldBuff;
            default:
                return hero;
        }
    }

    private static void battleLoop(Hero player, Hero enemy, Map map) {
        while (player.isAlive() && enemy.isAlive()) {
            List<AttackSkill> playerSkills = player.getAvailableSkills();

            System.out.println("Choose your skill:");
            for (int i = 0; i < playerSkills.size(); i++) {
                System.out.println((i + 1) + ". " + playerSkills.get(i).getSkillName(player));
            }

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                continue;
            }

            if (choice < 0 || choice >= playerSkills.size()) {
                System.out.println("Invalid skill choice");
                continue;
            }

            AttackSkill selectedSkill = playerSkills.get(choice);
            selectedSkill.use(player, enemy);
            String attackerName = player.isPlayer() ? "Player (" + player.getClass().getSimpleName() + ")" : player.getClass().getSimpleName();
            eventManager.notifyObservers(attackerName +
                    " uses " + selectedSkill.getSkillName(player) + ", dealing " + selectedSkill.getSkillDamage(player) + " damage (" +
                    enemy.getHp() + " HP left)");

            if (!enemy.isAlive()) {
                break;
            }

            List<AttackSkill> enemySkills = enemy.getAvailableSkills();
            AttackSkill enemySkill = enemySkills.get((int)(Math.random() * enemySkills.size()));
            enemySkill.use(enemy, player);
            String enemyName = "Enemy (" + enemy.getClass().getSimpleName() + ")";
            eventManager.notifyObservers(enemyName +
                    " uses " + enemySkill.getSkillName(enemy) + ", dealing " + selectedSkill.getSkillDamage(enemy) + " damage (" +
                    player.getHp() + " HP left)");

            if (!player.isAlive()) {
                break;
            }
        }
    }
}
