package strategy;

import model.Hero;
import skill.AttackSkill;
import skill.StrongRangedSkill;
import skill.WeakRangedSkill;

public class RangedAttackStrategy implements AttackStrategy {
    private boolean isStrong;

    public RangedAttackStrategy(boolean isStrong) {
        this.isStrong = isStrong;
    }

    @Override
    public AttackSkill getSkill(Hero attacker) {
        return isStrong ? new StrongRangedSkill() : new WeakRangedSkill();
    }

    @Override
    public String getStrategyType() {
        return "ranged";
    }
}
