package model;

import skill.*;
import strategy.*;

import java.util.ArrayList;
import java.util.List;

public class Archer extends Hero {

    public Archer(int hp, int damage, int x, int y, boolean isPlayer) {
        super(hp, damage, x, y, isPlayer);

        this.strategies = List.of(
                new MeleeAttackStrategy(false),
                new RangedAttackStrategy(true),
                new MagicAttackStrategy(false)
        );
    }

    @Override
    public void attack(Hero enemy) {
        int damage = enemy.getDamage();
        System.out.println("Archer shoots and deals " + damage + " damage");
        enemy.setHp(enemy.getHp() - damage);
    }

    @Override
    protected char getClassSymbol() {
        return 'A';
    }
}
