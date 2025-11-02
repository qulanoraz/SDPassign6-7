package model;

import observer.GameEventManager;
import skill.AttackSkill;
import strategy.AttackStrategy;

import java.util.List;

public abstract class Hero implements MapElement {
    private int hp;
    private int maxHp;
    private int damage;
    private int x, y;
    private boolean isPlayer;
    public List<AttackStrategy> strategies;
    private GameEventManager eventManager;

    public Hero(int hp, int damage, int startX, int startY, boolean isPlayer) {
        this.hp = hp;
        this.maxHp = hp;
        this.damage = damage;
        this.x = startX;
        this.y = startY;
        this.isPlayer = isPlayer;
    }

    public void setEventManager(GameEventManager eventManager) {
        this.eventManager = eventManager;
    }
    public GameEventManager getEventManager() {
        return eventManager;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        int oldHp = this.hp;
        this.hp = hp;

        if (eventManager != null && oldHp != hp) {
            if (hp <= 0) {
                if (isPlayer) {
                    eventManager.notifyObservers("PLAYER_DEFEATED");
                } else {
                    eventManager.notifyObservers("ENEMY_DEFEATED: " + this.getClass().getSimpleName());
                }
            } else if (hp < maxHp * 0.3 && oldHp >= maxHp * 0.3) {
                String heroType = isPlayer ? "Player" : this.getClass().getSimpleName();
                eventManager.notifyObservers(this.getClass().getSimpleName() + " has low health!");
            }
        }
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAlive() {
        return hp > 0;
    }
    public boolean isPlayer() {
        return isPlayer;
    }

    @Override
    public char getMapSymbol() {
        if (isPlayer) {
            return 'Y';
        } else {
            return getClassSymbol();
        }
    }

    public void moveUp() {
        y--;
    }
    public void moveDown() {
        y++;
    }
    public void moveLeft() {
        x--;
    }
    public void moveRight() {
        x++;
    }

    protected abstract char getClassSymbol();

    public abstract void attack(Hero enemy);

    public List<AttackSkill> getAvailableSkills() {
        return strategies.stream()
                .map(strategy -> strategy.getSkill(this))
                .toList();
    }
}
