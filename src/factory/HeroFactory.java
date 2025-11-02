package factory;

import model.Hero;
import model.Warrior;
import model.Mage;
import model.Archer;

public class HeroFactory {
    public static Hero createHero(String type, String heroClass, int x, int y, boolean isPlayer) {
        switch (type.toLowerCase()) {
            case "melee":
                if (heroClass.equalsIgnoreCase("warrior")) {return new Warrior(100, 10, x, y, isPlayer);}
                break;
            case "ranged":
                if (heroClass.equalsIgnoreCase("archer")) {return new Archer(80, 10, x, y, isPlayer);}
                break;
            case "magic":
                if (heroClass.equalsIgnoreCase("mage")) {return new Mage(65, 15, x, y, isPlayer);}
                break;
        }
        return null;
    }
}
