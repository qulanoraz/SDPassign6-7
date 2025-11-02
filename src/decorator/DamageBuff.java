package decorator;

import model.Hero;

public class DamageBuff extends HeroDecorator {
    private int damageBonus;

    public DamageBuff(Hero hero, int damageBonus) {
        super(hero);
        this.damageBonus = damageBonus;
    }

    @Override
    public int getDamage() {
        return decoratedHero.getDamage() + damageBonus;
    }

    public String getBuffDescription() {
        return "Damage +" + damageBonus;
    }
}
