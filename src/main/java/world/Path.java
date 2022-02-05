package world;

import engine.Element;
import entities.Enemy;

import java.util.ArrayList;
import java.util.HashSet;

public class Path extends Element {
    ArrayList<Position> positions = new ArrayList<>();
    ArrayList<Direction> directions = new ArrayList<>();
    ArrayList<HashSet<Enemy>> enemies = new ArrayList<>();

    Path() {
//        Wylosuj kierunki
    }

}
