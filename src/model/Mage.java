package model;

import skill.AttackSkill;
import strategy.*;

import java.util.ArrayList;
import java.util.List;

public class Mage extends Hero {

    public Mage(int hp, int damage, int x, int y, boolean isPlayer) {
        super(hp, damage, x, y, isPlayer);

        this.strategies = List.of(
                new MeleeAttackStrategy(false),
                new RangedAttackStrategy(false),
                new MagicAttackStrategy(true)
        );
    }

    @Override
    public void attack(Hero enemy) {
        int damage = enemy.getDamage();
        System.out.println("Mage casts magic and deals " + damage + " damage");
        enemy.setHp(enemy.getHp() - damage);
    }

    @Override
    protected char getClassSymbol() {
        return 'M';
    }
}
