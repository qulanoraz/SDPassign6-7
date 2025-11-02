package skill;

import model.Hero;

public class StrongRangedSkill implements AttackSkill {
    private int skillDamage;

    @Override
    public void use(Hero attacker, Hero defender) {
        skillDamage = (int) (attacker.getDamage() * 1.8);

        defender.setHp(defender.getHp() - skillDamage);
//        System.out.println(attacker.getClass().getSimpleName() + " uses RAIN OF ARROWS skill, dealing " + skillDamage + " damage (" + defender.getHp() + " HP left)");
    }

    @Override
    public String getSkillName(Hero attacker) {
        return "Rain of Arrows";
    }

    @Override
    public int getSkillDamage(Hero attacker) {
        skillDamage = (int) (attacker.getDamage() * 1.8);
        return skillDamage;
    }
}
