package model;

public class Buff implements MapElement {
    private String buffType;

    public Buff(String buffType) {
        this.buffType = buffType;
    }

    public String getBuffType() {
        return buffType;
    }

    @Override
    public char getMapSymbol() {
        switch (buffType) {
            case "damage": return 'd';
            case "health": return '+';
            case "shield": return 's';
            default: return 'b';
        }
    }
}
