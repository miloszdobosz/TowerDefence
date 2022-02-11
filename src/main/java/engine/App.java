package engine;

import entities.Tower;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import world.Position;
import world.World;

public class App extends Application {
    World world;
    Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Group group = new Group();
        world = new World(group);
        primaryStage.setScene(new Scene(group));
//        update();
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.getScene().setOnMouseClicked(event -> world.addTower(new Tower(world, new Position((int) (event.getX() - event.getX() % 50), (int) (event.getY() - event.getY() % 50)), group)));


        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (world) {
                    world.update();
                }
//                Platform.runLater(() -> update());
            }

        });
        thread.start();
    }

//    public void update() {
//        Group parent = (Group) primaryStage.getScene().getRoot();
//        parent.getChildren().clear();
//        world.view(parent);
//    }
}
