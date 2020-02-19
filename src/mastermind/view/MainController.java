package mastermind.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mastermind.model.Game;
import mastermind.utils.AnimationManager;

public class MainController implements Initializable {
    private final Game game = new Game(Game.DIFFICULTY_EASY);
    private final AnimationManager animationManager = new AnimationManager();
    @FXML
    private Label expression;
    @FXML
    private Label correctnessText;
    @FXML
    private TextField input;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() ->{
            expression.setText(game.nextExpression());
        });
    }    

    @FXML
    private void keyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            correctnessText.
                setText(game.checkAnswer(Integer.decode(input.getText())));
            input.clear();
            animationManager.showCorrectness(correctnessText);
            animationManager.changeExpression(expression, game.nextExpression());
        }
    }
    
}
