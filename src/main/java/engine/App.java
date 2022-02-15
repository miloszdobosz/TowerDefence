package engine;

import entities.Enemy;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import world.World;

public class App extends Application {
    World world;
    public static final Group parentView = new Group();

    @Override
    public void start(Stage primaryStage) {
        Enemy.loadViews();
        world = new World();

        primaryStage.setScene(new Scene(parentView));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }

    public static void win() {
        end("Game over, YOU WIN!");
    }

    public static void lose() {
        end("Game over, YOU LOSE!");
    }

    public static void end(String text) {
        Platform.runLater(() -> {
            Stage gameOver = new Stage();

            Label label = new Label(text);

            Button button = new Button("OK");
            GridPane.setHalignment(button, HPos.CENTER);
            button.setOnMouseClicked(event -> {
                System.exit(0);
            });

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(10);
            grid.add(label, 0, 0);
            grid.add(button, 0, 1);

            gameOver.setScene(new Scene(grid));
            gameOver.show();
        });
    }
}
