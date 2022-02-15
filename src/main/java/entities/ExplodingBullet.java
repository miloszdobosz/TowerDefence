package entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ExplodingBullet extends Bullet {
    private double range;

    private Entities<Enemy> enemies;

    public ExplodingBullet(Tower tower, Enemy enemy, int damage, double range, Entities enemies) {
        super(tower, enemy, damage);

        this.range = range;
        this.enemies = enemies;

        view = new Circle(position.x, position.y, 10, Color.rgb(150, 0, 0));
    }

    @Override
    public void update() {
        if (!move()) {
            explode();
            delete();
        }
    }

    private void explode() {
        enemies.stream().filter(enemy1 -> enemy.getPosition().distanceTo(enemy1.getPosition()) <= range).forEach(enemy -> enemy.bleed(damage));
    }
}
