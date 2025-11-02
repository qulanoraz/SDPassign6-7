package decorator;

import model.Hero;

public class ShieldBuff extends HeroDecorator {
    private double shieldPercent;

    public ShieldBuff(Hero hero, double shieldPercent) {
        super(hero);
        this.shieldPercent = shieldPercent;
    }

    @Override
    public void setHp(int hp) {
        int currentHp = decoratedHero.getHp();
        int damage = currentHp - hp;

        if (damage > 0) {
            int reducedDamage = (int) (damage * (1 - shieldPercent));
            decoratedHero.setHp(currentHp - reducedDamage);
        } else {
            decoratedHero.setHp(hp);
        }
    }

    public String getBuffDescription() {
        return "Shield +" + (int) (shieldPercent * 100) + "%";
    }
}
