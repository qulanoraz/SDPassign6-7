package skill;

import model.Hero;

public class WeakMeleeSkill implements AttackSkill {
    private int skillDamage;

    @Override
    public void use(Hero attacker, Hero defender) {
        skillDamage = attacker.getDamage();
        String name = attacker.getClass().getSimpleName();

        switch (name) {
            case "Archer":
                defender.setHp(defender.getHp() - skillDamage);
//                System.out.println(name + " uses FAST ARROW skill, dealing " + skillDamage + " damage (" + defender.getHp() + " HP left)");
                break;
            case "Mage":
                defender.setHp(defender.getHp() - skillDamage);
//                System.out.println(name + " casts FIRE DAGGER skill, dealing " + skillDamage + " damage (" + defender.getHp() + " HP left)");
                break;
        }
    }

    @Override
    public String getSkillName(Hero attacker) {
        String name = attacker.getClass().getSimpleName().toLowerCase();

        switch (name) {
            case "archer":
                return "Fast Arrow";
            case "mage":
                return "Fire Dagger";
        }

        return null;
    }

    @Override
    public int getSkillDamage(Hero attacker) {
        skillDamage = attacker.getDamage();
        return skillDamage;
    }
}
