package skill;

import model.Hero;

public interface AttackSkill {
    void use(Hero attacker, Hero defender);
    String getSkillName(Hero attacker);
    int getSkillDamage(Hero attacker);
}
