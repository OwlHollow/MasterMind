package mastermind;

import java.io.IOException;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.logging.Logger;

public class MasterMind extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {        
        Parent root = new Group();
        try {
            root = FXMLLoader.load(getClass()
                    .getResource("/mastermind/view/Main.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MasterMind.class.getName())
                .log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("/mastermind/res/img/logo.png"));
        primaryStage.setTitle("Master Mind");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
