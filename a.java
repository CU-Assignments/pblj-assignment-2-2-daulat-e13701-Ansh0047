import java.util.*;

public class SumCalculator {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        String[] input = {"10", "20", "30", "40"};

        for (String num : input) {
            numbers.add(parseToInteger(num));
        }
        int totalSum = calculateSum(numbers);
        System.out.println("Sum of the numbers: " + totalSum);
    }
    public static Integer parseToInteger(String value) {
        try {
            return Integer.parseInt(value); 
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + value);
            return 0;
        }
    }
    public static int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer num : numbers) {
            sum += num; 
        }
        return sum;
    }
} 
