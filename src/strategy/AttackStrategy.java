package strategy;

import model.Hero;
import skill.AttackSkill;

public interface AttackStrategy {
    AttackSkill getSkill(Hero attacker);
    String getStrategyType();
}
