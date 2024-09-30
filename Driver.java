import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver{
    public static void main(String[] args) {
        try {
            // Test 1: Construct polynomial from arrays
            double[] coeffs1 = {5, -3, 7};   // Represents 5x^2 - 3x + 7
            int[] exps1 = {2, 1, 0};
            Polynomial p1 = new Polynomial(coeffs1, exps1);
            System.out.println("Polynomial p1 created from arrays:");

            // Test 2: Construct polynomial from a file
            // The file should contain a polynomial in text format (e.g., "5x2-3x+7")
            File file = new File("polynomial.txt");  // Ensure this file contains valid polynomial
            Polynomial p2 = new Polynomial(file);
            System.out.println("Polynomial p2 created from file:");
            
            // Test 3: Save polynomial to file
            p1.saveToFile("output_polynomial.txt");
            System.out.println("Polynomial p1 saved to 'output_polynomial.txt'.");

            // Test 4: Polynomial addition
            Polynomial sum = p1.add(p2);
            System.out.println("Result of p1 + p2:");
            sum.saveToFile("sum_polynomial.txt");
            System.out.println("Polynomial sum saved to 'sum_polynomial.txt'.");

            // Test 5: Polynomial multiplication
            Polynomial product = p1.multiply(p2);
            System.out.println("Result of p1 * p2:");
            product.saveToFile("product_polynomial.txt");
            System.out.println("Polynomial product saved to 'product_polynomial.txt'.");

            // Test 6: Evaluate p1 at x = 2
            double result = p1.evaluate(2);
            System.out.println("p1 evaluated at x = 2: " + result);

            // Test 7: Check if p1 has root at x = 1
            boolean hasRoot = p1.hasRoot(1);
            System.out.println("p1 has root at x = 1: " + hasRoot);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
