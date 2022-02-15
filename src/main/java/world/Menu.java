package world;

import engine.App;
import entities.Entity;
import entities.Tower;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Menu extends Entity {
    public static final Image[] views = {
            new Image("menu/money.png", 20, 0, true, true),
            new Image("menu/health.png", 20, 0, true, true)
    };
    public static final Position[] viewPositions = {
            new Position(World.worldSize.x + 25, 25),
            new Position(World.worldSize.x + 25, 50)
    };

    public static final Position[] towerPositions = {
            new Position(World.worldSize.x + 25, 100),
            new Position(World.worldSize.x + 25, 200),
            new Position(World.worldSize.x + 25, 300),
            new Position(World.worldSize.x + 25, 500)
    };

    public static final int menuSize = 200;


    Text moneyText;
    Text healthText;

    int health = 100;
    int money = 500;

    public boolean alive = true;


    public Menu(World world) {

        Rectangle rectangle = new Rectangle(World.worldSize.x, 0, menuSize, World.worldSize.y);
        rectangle.setFill(Color.rgb(150,100,100));
        view = rectangle;

        moneyText = new Text(World.worldSize.x + 50, 40, String.valueOf(money));
        moneyText.setFill(Color.rgb(255, 180, 0));

        healthText = new Text(World.worldSize.x + 50, 65, String.valueOf(health));
        healthText.setFill(Color.rgb(255, 0, 0));

        App.parentView.getChildren().addAll(view, moneyText, healthText);

        for (int i = 0; i < 2; i++) {
            ImageView view = new ImageView(views[i]);
            view.relocate(viewPositions[i].x, viewPositions[i].y);
            App.parentView.getChildren().add(view);
        }

        for (int i = 0; i < 4; i++) {
            ImageView view = new ImageView(Tower.views[i]);
            view.relocate(towerPositions[i].x, towerPositions[i].y);

            ImageView priceView = new ImageView(views[0]);
            priceView.relocate(towerPositions[i].x, towerPositions[i].y + Tower.sizes[i].y + 10);


            Text priceText = new Text(towerPositions[i].x + 25, towerPositions[i].y + Tower.sizes[i].y + 25, String.valueOf(Tower.prices[i]));
            priceText.setFill(Color.rgb(255, 180, 0));

            int finalI = i;
            view.setOnMousePressed(event -> {
                if (canAfford(finalI)) {
                    world.setPlacingTower(finalI);
                }
            });

            App.parentView.getChildren().addAll(view, priceView, priceText);
        }
    }

    private boolean canAfford(int i) {
        return money >= Tower.prices[i];
    }

    public void bleed(int damage) {
        health -= damage;
        healthText.setText(String.valueOf(health));

        if (health <= 0) {
            kill();
        }
    }

    public void pay(int price) {
        money -= price;
        moneyText.setText(String.valueOf(money));
    }

    public void kill() {
        this.alive = false;
        App.lose();
    }
}
