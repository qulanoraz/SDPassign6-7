package strategy;

import model.Hero;
import skill.AttackSkill;
import skill.StrongMeleeSkill;
import skill.WeakMeleeSkill;

public class MeleeAttackStrategy implements AttackStrategy {
    private boolean isStrong;

    public MeleeAttackStrategy(boolean isStrong) {
        this.isStrong = isStrong;
    }

    @Override
    public AttackSkill getSkill(Hero attacker) {
        return isStrong ? new StrongMeleeSkill() : new WeakMeleeSkill();
    }

    @Override
    public String getStrategyType() {
        return "melee";
    }
}
