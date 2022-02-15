package engine;

import entities.Tower;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import world.Position;
import world.World;

public class Menu extends Element {

    public static final Image[] views = {
            new Image("menu/money.png", 20, 0, true, true),
            new Image("menu/health.png", 20, 0, true, true)
    };
    public static final Position[] viewPositions = {
            new Position(World.worldSize.x + 25, 25),
            new Position(World.worldSize.x + 25, 50)
    };
    public static int menuSize = 200;

//    public static ArrayList<Position> towerPositions = new ArrayList<>();
//    public static ArrayList<Position> towerSizes = new ArrayList<>();
//    public static ArrayList<Integer> prices = new ArrayList<>();
//    public static ArrayList<Integer> ranges = new ArrayList<>();

    public static final Position[] towerPositions = {
            new Position(World.worldSize.x + 25, 100),
            new Position(World.worldSize.x + 25, 200),
            new Position(World.worldSize.x + 25, 300),
            new Position(World.worldSize.x + 25, 500)
    };

    int health = 100;
    int money = 500;

    Text moneyText;
    Text healthText;

    public boolean alive = true;

    public Menu(World world) {
//        towerPositions.add(new Position(World.worldSize.x + 25, 100));
//        towerPositions.add(new Position(World.worldSize.x + 125, 100));
//        towerPositions.add(new Position(World.worldSize.x + 25, 200));
//        towerPositions.add(new Position(World.worldSize.x + 25, 300));
//
//        towerSizes.add(new Position(World.fieldSize, World.fieldSize));
//        towerSizes.add(new Position(World.fieldSize, World.fieldSize));
//        towerSizes.add(new Position(World.fieldSize, World.fieldSize * 3));
//        towerSizes.add(new Position(World.fieldSize * 2, World.fieldSize * 2));
//
//        prices.add(100);
//        prices.add(200);
//        prices.add(200);
//        prices.add(400);
//
//        ranges.add(100);
//        ranges.add(100);
//        ranges.add(300);
//        ranges.add(200);

        Rectangle rectangle = new Rectangle(World.worldSize.x, 0, menuSize, World.worldSize.y);
        rectangle.setFill(Color.rgb(100,100,100));
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

            int finalI = i;
            view.setOnMousePressed(event -> {
                if (canAfford(finalI)) {
                    world.setPlacingTower(finalI);
                }
            });

            App.parentView.getChildren().add(view);
        }
    }

    private void addOptions() {
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

        System.out.println("GAME OVER!");

        Platform.runLater(() -> {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setContentText("GAME OVER!");
            a.show();
        });
    }
}
