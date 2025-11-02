package skill;

import model.Hero;

public class WeakRangedSkill implements AttackSkill {
    private int skillDamage;

    @Override
    public void use(Hero attacker, Hero defender) {
        skillDamage = attacker.getDamage();
        String name = attacker.getClass().getSimpleName();

        switch (name) {
            case "Warrior":
                defender.setHp(defender.getHp() - skillDamage);
//                System.out.println(name + " throws a knife, dealing " + skillDamage + " damage (" + defender.getHp() + " HP left)");
                break;
            case "Mage":
                defender.setHp(defender.getHp() - skillDamage);
//                System.out.println(name + " casts WATER BALL skill, dealing " + skillDamage + " damage (" + defender.getHp() + " HP left)");
                break;
        }
    }

    @Override
    public String getSkillName(Hero attacker) {
        String name = attacker.getClass().getSimpleName().toLowerCase();

        switch (name) {
            case "warrior":
                return "Throwing Knife";
            case "mage":
                return "Water Ball";
        }

        return null;
    }

    @Override
    public int getSkillDamage(Hero attacker) {
        skillDamage = attacker.getDamage();
        return skillDamage;
    }
}
