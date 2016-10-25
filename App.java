package Cartoon;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 This application models a Moon rise as seen from the interior of a room. Its two
 main methods of interaction are controlling the speed at which the moon travels
 through its rise/fall cycle, and manipulating the location of the 5 layers through
 mouse input for a parallax effect. The first is achieved through use of a timeline 
 and keyframe, and the latter is achieved through a MouseHandler triggered by Mouse movement.
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
        stage.show();
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        stage.setScene(scene);
    }

    /*
    * Here is the mainline! No need to change this.
    */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
