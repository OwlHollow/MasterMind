package mastermind.utils;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AnimationManager {
    public void changeExpression(Label expressionText, String newExpression){   
        TranslateTransition ttOut = 
                new TranslateTransition(Duration.millis(1500), expressionText);
            ttOut.setByX(
                -(expressionText.getLayoutX() + expressionText.getWidth()) - 50);
            
            ttOut.setOnFinished((ActionEvent animationEvent) -> {
                expressionText.setText(newExpression);
            });
            
            TranslateTransition ttIn = 
                new TranslateTransition(Duration.millis(1500), expressionText);
            ttIn.setByX(
                expressionText.getLayoutX() + expressionText.getWidth() + 50);
            
            SequentialTransition animation = 
                new SequentialTransition(ttOut, ttIn);
            
            animation.play();
    }
    
        public void showCorrectness(Label correctnessText){   
            double offsetX = 100;
            TranslateTransition ttIn = 
                new TranslateTransition(Duration.seconds(1), correctnessText);
            if(correctnessText.getText().equals("wrong")){
                correctnessText.setTextFill(Color.CRIMSON);
                offsetX = -offsetX;
            } else {
                correctnessText.setTextFill(Color.GREEN);
            }
            ttIn.setFromX(
                correctnessText.getLayoutX() - 300);
            ttIn.setByX(offsetX);
            
            FadeTransition ft = 
                new FadeTransition(Duration.seconds(1), correctnessText);
            ft.setFromValue(1);
            ft.setToValue(0);
            
            ParallelTransition animation = 
                new ParallelTransition(ttIn, ft);
            
            animation.play();
    }
}
