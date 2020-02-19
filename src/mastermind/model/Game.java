package mastermind.model;

public class Game {
    public static final int DIFFICULTY_EASY = 1;
    public static final int DIFFICULTY_NORMAL = 2;
    public static final int DIFFICULTY_MODERN = 3;
    public static final int DIFFICULTY_ADVANCE = 4;
    
    private int currentDifficult;
    private Expression ex;
    private int success, faults, correctInARow;
    
    public Game(){
        this.currentDifficult = DIFFICULTY_NORMAL;

        this.success = 0;
        this.faults = 0;
        this.correctInARow = 0;
    };
    
    public Game(int difficultyLevel){
        if(difficultyLevel > DIFFICULTY_ADVANCE)
            this.currentDifficult = 4;
        else if (difficultyLevel < DIFFICULTY_EASY)
            this.currentDifficult = 1;
        else
            this.currentDifficult = difficultyLevel;
        
        this.success = 0;
        this.faults = 0;
        this.correctInARow = 0;
    }
    
    public void changeDifficult(int difficultyLevel){
        if(difficultyLevel > DIFFICULTY_ADVANCE)
            this.currentDifficult = 2;
        else if (difficultyLevel < DIFFICULTY_EASY)
            this.currentDifficult = 1;
        else
            this.currentDifficult = difficultyLevel;
        
        success = 0;
        faults = 0;
        correctInARow = 0;
    }
    
    public int getCurrentDifficultLevel(){
        return currentDifficult;
    }
    
    public String nextExpression(){
        switch(currentDifficult){
            ///////EASY//////////////
            case DIFFICULTY_EASY:
                if(ex == null || ex instanceof Poly){
                    ex = new Mono();
                }
                ex.generate(2 * currentDifficult, 
                            2, 5 * currentDifficult + success,
                            DIFFICULTY_EASY);
            return ex.getExpression();
            ///////EASY//////////////
            
            ///////NORMAL//////////////
            case DIFFICULTY_NORMAL:
                if(ex == null|| ex instanceof Poly){
                    ex = new Mono();
                }
                ex.generate(2 + correctInARow, 
                            2, 10 * (currentDifficult + success - faults), 
                            DIFFICULTY_NORMAL);
            return ex.getExpression();
            ///////NORMAL//////////////
            
            ///////MODERN//////////////
            case DIFFICULTY_MODERN:
                if(ex == null || ex instanceof Mono){
                    ex = new Poly();
                }
                ex.generate(currentDifficult, 
                            2, 5 * (currentDifficult + success), 
                            DIFFICULTY_MODERN);
            return ex.getExpression();
            ///////MODERN//////////////
            
            ///////ADVANCE//////////////
            case DIFFICULTY_ADVANCE:
                if(ex == null || ex instanceof Mono){
                    ex = new Poly();
                }
                ex.generate(currentDifficult + correctInARow, 
                            2, 10 * (currentDifficult + success - faults), 
                            DIFFICULTY_ADVANCE);
            return ex.getExpression();
            ///////ADVANCE//////////////
        }
        
        return null;
    }
    
    public String checkAnswer(int answer){
        String message;
        if(answer == ex.getResult()){
            success += 1;
            correctInARow += 1;
            message = "correct";
        } else {
            faults += 1;
            message = "wrong";
        }
        
        if(success + faults >= 10 - currentDifficult && success > faults){
            changeDifficult(currentDifficult + 1);
            message +=  " level up";
        }
        
        return message;
    }
}
