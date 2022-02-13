package engine;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import world.World;

public class Menu extends Element {
    int health = 100;
    int money = 200;

    Text moneyView;
    Text healthView;

    public Menu(World world) {
        view = new Rectangle(500, 0, 200, 500);
        view.setFill(Color.rgb(100,100,100));
        view.setOnMouseClicked(event -> {
            if (money < 100) return;
            world.setPlacingTower(true);
        });

        moneyView = new Text(500, 20, String.valueOf(money));
        moneyView.setFill(Color.rgb(0, 0, 0));

        healthView = new Text(600, 20, String.valueOf(health));
        healthView.setFill(Color.rgb(0, 0, 0));

        App.parentView.getChildren().addAll(view, moneyView, healthView);
    }

    public void bleed(int damage) {
        health -= damage;
        healthView.setText(String.valueOf(health));

        if (health <= 0) {
            kill();
        }
    }

    public void pay(int price) {
        money -= price;
        moneyView.setText(String.valueOf(money));
    }

    public void kill() {
        System.out.println("GAME OVER!");
//        Platform.runLater(() -> {
//            Alert a = new Alert(Alert.AlertType.NONE);
//            a.setContentText("GAME OVER!");
//            a.show();
//        });
    }
}
