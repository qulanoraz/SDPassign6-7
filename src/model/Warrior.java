package model;

import skill.*;
import strategy.*;

import java.util.ArrayList;
import java.util.List;

public class Warrior extends Hero {

    public Warrior(int hp, int damage, int x, int y, boolean isPlayer) {
        super(hp, damage, x, y, isPlayer);

        this.strategies = List.of(
                new MeleeAttackStrategy(true),
                new RangedAttackStrategy(false),
                new MagicAttackStrategy(false)
        );
    }

    @Override
    public void attack(Hero enemy) {
        int damage = enemy.getDamage();
        enemy.setHp(enemy.getHp() - damage);
        System.out.println("Warrior attacks and deals " + damage + " damage (" + enemy.getHp() + " HP left)");
    }

    @Override
    protected char getClassSymbol() {
        return 'W';
    }
}
