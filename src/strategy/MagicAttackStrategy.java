package strategy;

import model.Hero;
import skill.AttackSkill;
import skill.StrongMagicSkill;
import skill.WeakMagicSkill;

public class MagicAttackStrategy implements AttackStrategy {
    private boolean isStrong;

    public MagicAttackStrategy(boolean isStrong) {
        this.isStrong = isStrong;
    }

    @Override
    public AttackSkill getSkill(Hero attacker) {
        return isStrong ? new StrongMagicSkill() : new WeakMagicSkill();
    }

    @Override
    public String getStrategyType() {
        return "magic";
    }
}
