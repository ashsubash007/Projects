import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;



public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a mathematical expression:");
        String input = scanner.nextLine().replaceAll("\\s+", ""); 
        scanner.close();
        
        
        
        List<String> tokens = Pattern.compile("(\\d+\\.?\\d*|[+\\-*/%])")
                                     .matcher(input)
                                     .results() 
                                     .map(MatchResult::group)
                                     .collect(Collectors.toList());
        
        if (tokens.isEmpty()) {
            System.out.println("Error: Invalid input");
            return;
        }

        double result = evaluateExpression(tokens);
        System.out.println("Result: " + result);     
    }
    	private static double evaluateExpression(List<String> tokens) {
        double result = Double.parseDouble(tokens.get(0));
        Iterator<String> iterator = tokens.subList(1, tokens.size()).iterator();
        
        while (iterator.hasNext()) {
            String operator = iterator.next();
            double currentNumber = Double.parseDouble(iterator.next());
            result = applyOperation(result, currentNumber, operator);
        }
        return result;
    }  
    private static double applyOperation(double a, double b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": 
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            case "%": 
                if (b == 0) throw new ArithmeticException("Modulo by zero");
                return a % b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
