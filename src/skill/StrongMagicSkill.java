package skill;

import model.Hero;

public class StrongMagicSkill implements AttackSkill {
    private int skillDamage;

    @Override
    public void use(Hero attacker, Hero defender) {
        skillDamage = (int) (attacker.getDamage() * 1.8);

        defender.setHp(defender.getHp() - skillDamage);
//        System.out.println(attacker.getClass().getSimpleName() + " casts METEOR SHOWER skill, dealing " + skillDamage + " damage (" + defender.getHp() + " HP left)");
    }

    @Override
    public String getSkillName(Hero attacker) {
        return "Meteor Shower";
    }

    @Override
    public int getSkillDamage(Hero attacker) {
        skillDamage = (int) (attacker.getDamage() * 1.8);
        return skillDamage;
    }
}
