package entities;

import engine.Element;
import javafx.scene.Group;
import world.Map;

public class Tower implements Element {
    int size;
    int price;
    int damage;
    int range;
    int period;

    Map map;

    Tower() {
    }

    public void attack() {
//        animacja strzalu
//        getTarget.bleed(damage);
    }

    public void getTarget() {
//        for (Enemy enemy : Map.enemies) {
//
//        }
//        return target;
        //return new Enemy();
    }


    @Override
    public void view(Group parent) {
    }
}
