package decorator;

import model.Hero;
import observer.*;
import skill.AttackSkill;

import java.util.List;

public abstract class HeroDecorator extends Hero {
    protected Hero decoratedHero;

    public HeroDecorator(Hero hero) {
        super(hero.getHp(), hero.getDamage(), hero.getX(), hero.getY(), hero.isPlayer());
        this.decoratedHero = hero;
        this.strategies = hero.strategies;
    }

    @Override
    public int getHp() {
        return decoratedHero.getHp();
    }

    @Override
    public void setHp(int hp) {
        decoratedHero.setHp(hp);
    }

    @Override
    public int getDamage() {
        return decoratedHero.getDamage();
    }

    @Override
    public int getX() {
        return decoratedHero.getX();
    }

    @Override
    public int getY() {
        return decoratedHero.getY();
    }

    @Override
    public void setPosition(int x, int y) {
        decoratedHero.setPosition(x, y);
    }

    @Override
    public boolean isAlive() {
        return decoratedHero.isAlive();
    }

    @Override
    public boolean isPlayer() {
        return decoratedHero.isPlayer();
    }

    @Override
    public void setEventManager(GameEventManager eventManager) {
        decoratedHero.setEventManager(eventManager);
    }

    @Override
    public GameEventManager getEventManager() {
        return decoratedHero.getEventManager();
    }

    @Override
    public List<AttackSkill> getAvailableSkills() {
        return decoratedHero.getAvailableSkills();
    }

    @Override
    public void attack(Hero enemy) {
        decoratedHero.attack(enemy);
    }

    @Override
    protected char getClassSymbol() {
        return decoratedHero.getMapSymbol();
    }
}
