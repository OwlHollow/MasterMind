package mastermind.model;

public class Poly extends Expression{

    /**
     * Метод, генерирующий многочлен с заданным количеством одночленов
     * @param elementsCount количество многочленов
     * @param rangeFrom нижняя граница диапазона возможных значений элементов одночлена
     * @param rangeTo верхняя граница диапазона возможных значений элементов одночлена
     * @return Текстовое представление многочлена 
     */
    @Override
    public String generate(int elementsCount, 
                            int rangeFrom, 
                            int rangeTo,
                            int difficultyLevel) {
        Mono mono = new Mono();
        
        expression = 
            "(" + mono.generate(
                    randomBetween(2, 2 + difficultyLevel), 
                    rangeFrom, rangeTo, 
                    difficultyLevel);
        
        if(elementsCount == 1)
            expression = expression.replace("(", "");
        
        result = mono.getResult();
        
        for(int i = 1; i < elementsCount; i++){
            mono.generate(
                    randomBetween(2, 3), 
                    rangeFrom, rangeTo,
                    difficultyLevel);
            
            switch(randomBetween(0, 1)){
                case 0:
                    expression += ") + (";
                    result += mono.getResult();
                    break;
                case 1:
                    expression += ") - (";
                    result -= mono.getResult();
                    break;
            }
            
            expression += mono.getExpression();
            if(i == elementsCount - 1) expression += ")";
        }
        
        return expression;
    }
    
}
