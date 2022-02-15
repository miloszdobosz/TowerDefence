package entities;

public class ExplodingBullet extends Bullet {

    public ExplodingBullet(Tower tower, Enemy enemy, int damage) {
        super(tower, enemy, damage);
    }

    @Override
    public void update() {
        if (!move()) {
            explode();
            delete();
        }
    }

    private void explode() {
    }
}
