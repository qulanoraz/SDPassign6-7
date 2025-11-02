package skill;

import model.Hero;

public class WeakMagicSkill implements AttackSkill {
    private int skillDamage;

    @Override
    public void use(Hero attacker, Hero defender) {
        skillDamage = attacker.getDamage();
        String name = attacker.getClass().getSimpleName();

        switch (name) {
            case "Warrior":
                defender.setHp(defender.getHp() - skillDamage);
//                System.out.println(name + " uses ENCHANTMENT SWORD skill, dealing " + skillDamage + " damage (" + defender.getHp() + " HP left)");
                break;
            case "Archer":
                defender.setHp(defender.getHp() - skillDamage);
//                System.out.println(name + " uses MAGIC ARROWS skill, dealing " + skillDamage + " damage (" + defender.getHp() + " HP left)");
                break;
        }
    }

    @Override
    public String getSkillName(Hero attacker) {
        String name = attacker.getClass().getSimpleName().toLowerCase();

        switch (name) {
            case "warrior":
                return "Enchanted Sword";
            case "archer":
                return "Magic Arrows";
        }

        return null;
    }

    @Override
    public int getSkillDamage(Hero attacker) {
        skillDamage = attacker.getDamage();
        return skillDamage;
    }
}
