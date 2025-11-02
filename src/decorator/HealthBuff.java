package decorator;

import model.Hero;

public class HealthBuff extends HeroDecorator {
    private int healthBonus;

    public HealthBuff(Hero hero, int healthBonus) {
        super(hero);
        this.healthBonus = healthBonus;

        int newHp = decoratedHero.getHp() + healthBonus;
        decoratedHero.setHp(newHp);
    }

    public String getBuffDescription() {
        return "Health restored +" + healthBonus;
    }
}
