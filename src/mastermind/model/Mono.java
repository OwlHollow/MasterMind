package mastermind.model;
import java.util.ArrayList;

public class Mono extends Expression {
    
    /**
     * Метод, генерирующий одночлен 
     * с заданным количеством множителей (делителей),
     * а так же в заданном диапазоне значений множителей (элементов)
     * @param elementsCount количество элементов
     * @param rangeFrom нижняя граница диапазона возможных значений элементов одночлена
     * @param rangeTo верхняя граница диапазона возможных значений элементов одночлена
     * @return Текстовое представление одночлена 
     */
    @Override
    public String generate(int elementsCount, 
                            int rangeFrom, 
                            int rangeTo, 
                            int difficultyLevel) {
        int currentValue = randomBetween(rangeFrom, rangeTo);
        while(Math.abs(currentValue) == 1 ||
              currentValue == 0){
            //
            currentValue = randomBetween(rangeFrom, rangeTo);
        }
        
        // Коллекция(более корректное название этой структуры данных),
	// хранящая уже сгенерированные значения 
        //(необходимо для отсутствия выражений типа 11/11, 5/5 и т.п.)
        ArrayList<Integer> values = new ArrayList();
        
        result = currentValue; // result = 20;
        expression = Integer.toString(currentValue); // expression = "20";
        values.add(currentValue);
        // elementsCount = 2
        for(int i = 1; i < elementsCount; i++){
            currentValue = randomBetween(rangeFrom, rangeTo);
            while(Math.abs(currentValue) == 1 ||
                  currentValue == 0 || 
                  values.contains(currentValue) ||
                  values.contains(-currentValue)){
                    //
                    currentValue = randomBetween(rangeFrom, rangeTo);
            }
 
            //Если количество делителей результата выражения достаточно (что подбирается опционально)
            //Производим деление
            if(
                divisorsCount(result) < values.size() + (2 * difficultyLevel) + 1){
                expression += " * "; //expression = "20 * ";
                result *= currentValue; // result = 200;
                values.add(currentValue);
            }else{
                // Пока в коллекции существует сгенерированное значение
                //(проверяем снова, т.к. генерация будет происходить повторно)
                //Или сгенерированное значение равно нулю
                // Или, пока результат не будет делиться нацело
                while(values.contains(currentValue) ||
                      values.contains(-currentValue) || 
                      Math.abs(currentValue) == 1 ||
                      currentValue == 0 ||
                      result % currentValue != 0 ||
                      result / currentValue == 1){
                // Генерируем заново, но, диапазон берём от минимального значения,
                // до значения результата, чтобы избежать деления результата на большее число
                // (например: 10 / 20, 5 / 100)
                    currentValue = randomBetween(2, result);
                }

                expression += " / "; //expression = "20 / ";
                result /= currentValue; // result = 2;
                values.add(currentValue);
            }

           expression += Integer.toString(currentValue);
        }
        
        return expression;
    }
	
    public void Foo(){
        result = 0;
    }
}
