package mastermind.model;

import java.util.Random;

public abstract class Expression {
    private final Random rand = new Random();
	
    protected int result;
    protected String expression;
    
    public int getResult(){
        return result;
    }
    
    public String getExpression(){
        return expression;
    }
    
    /**
     * Метод, генерирующий выражение с заданным количеством элементов, 
     * а так же в заданном диапазоне значений элементов
     * @param elementsCount количество элементов
     * @param rangeFrom нижняя граница диапазона возможных значений элементов выражения
     * @param rangeTo верхняя граница диапазона возможных значений элементов выражения
     * @param difficultyLevel коэффициент, задающий уровень сложности выражений
     * @return Текстовое представление выражения 
     */
    public abstract String generate(int elementsCount,
                                    int rangeFrom, 
                                    int rangeTo,
                                    int difficultyLevel);
    
    
    /**
     * Вспомогательный метод, подсчитывающий количество делителей числа.
     * @param value значение, для которого подсчитывается количество делителей
     * @return целое, не отрицательное число, равное количеству делителей данного числа
     */
    protected int divisorsCount(int value){
        int count = 2;
        for(int i = 2; i < value; i++)
            if(value % i == 0)
                count++;
        
        return count;
    }
    
    /**
     * Метод, возвращающий случайное числов в заданом диапазоне
     * @param from нижняя граница диапазона значения
     * @param to верхняя граница диапазона значения
     * @return Cлучайное, целое число в диапазоне от from до to 
    */
    protected int randomBetween(int from, int to){
        int random;
        
        if(from < 0){
            random = rand.nextInt(Math.abs(from)) - rand.nextInt(Math.abs(to));
        } else {
            random = rand.nextInt(to + 1) + from;
        }
        
        if(random > to){
            random -= from;
        }
        return random;
    }
    
}
