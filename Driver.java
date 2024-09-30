import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class Driver {

    public static void main(String[] args) {
        // Running test cases for all methods
        testConstructorFromArrays();
        testConstructorFromHashMap();
        testAdd();
        testMultiply();
        testEvaluate();
        testHasRoot();
        testSaveToFile();
        testConstructorFromFile();
    }

    // Test Case 1: Constructor from arrays
    public static void testConstructorFromArrays() {
        System.out.println("\nTest 1: Constructor from arrays");
        double[] coeffs = {3, -2, 1, -5};   // Coefficients: 3, -2, 1, -5
        int[] exps = {4, 3, 1, 0};          // Exponents: 4, 3, 1, 0

        Polynomial poly = new Polynomial(coeffs, exps);

        printPolynomial(poly);  // Should print: 3x4-2x3+x-5
    }

    // Test Case 2: Constructor from HashMap
    public static void testConstructorFromHashMap() {
        System.out.println("\nTest 2: Constructor from HashMap");
        HashMap<Integer, Double> terms = new HashMap<>();
        terms.put(2, 4.0);  // 4x^2
        terms.put(0, -1.0); // -1
        terms.put(1, 3.0);  // +3x

        Polynomial poly = new Polynomial(terms);

        printPolynomial(poly);  // Should print: 4x2+3x-1
    }

    // Test Case 3: Add method
    public static void testAdd() {
        System.out.println("\nTest 3: Add polynomials");
        double[] coeffs1 = {3, -2, 1};    // Coefficients of first polynomial: 3x^2 - 2x + 1
        int[] exps1 = {2, 1, 0};          // Exponents of first polynomial: 2, 1, 0

        double[] coeffs2 = {1, -3};       // Coefficients of second polynomial: x - 3
        int[] exps2 = {1, 0};             // Exponents of second polynomial: 1, 0

        Polynomial p1 = new Polynomial(coeffs1, exps1);
        Polynomial p2 = new Polynomial(coeffs2, exps2);

        Polynomial sum = p1.add(p2);

        printPolynomial(sum);  // Should print: 3x2-1x-2
    }

    // Test Case 4: Multiply method
    public static void testMultiply() {
        System.out.println("\nTest 4: Multiply polynomials");
        double[] coeffs1 = {2, -1};    // Coefficients of first polynomial: 2x - 1
        int[] exps1 = {1, 0};          // Exponents of first polynomial: 1, 0

        double[] coeffs2 = {3, 4};     // Coefficients of second polynomial: 3x + 4
        int[] exps2 = {1, 0};          // Exponents of second polynomial: 1, 0

        Polynomial p1 = new Polynomial(coeffs1, exps1);
        Polynomial p2 = new Polynomial(coeffs2, exps2);

        Polynomial product = p1.multiply(p2);

        printPolynomial(product);  // Should print: 6x2+5x-4
    }

    // Test Case 5: Evaluate method
    public static void testEvaluate() {
        System.out.println("\nTest 5: Evaluate polynomial at x=2");
        double[] coeffs = {1, -3, 2};   // Coefficients: x^2 - 3x + 2
        int[] exps = {2, 1, 0};         // Exponents: 2, 1, 0

        Polynomial poly = new Polynomial(coeffs, exps);

        double result = poly.evaluate(2);  // Evaluate at x=2
        System.out.println("Expected result: 0.0, Actual result: " + result);  // Should print: 0.0
    }

    // Test Case 6: hasRoot method
    public static void testHasRoot() {
        System.out.println("\nTest 6: Check if polynomial has root at x=2");
        double[] coeffs = {1, -3, 2};   // Coefficients: x^2 - 3x + 2
        int[] exps = {2, 1, 0};         // Exponents: 2, 1, 0

        Polynomial poly = new Polynomial(coeffs, exps);

        boolean hasRoot = poly.hasRoot(2);  // Check if x=2 is a root
        System.out.println("Expected result: true, Actual result: " + hasRoot);  // Should print: true
    }

    // Test Case 7: saveToFile method
    public static void testSaveToFile() {
        System.out.println("\nTest 7: Save polynomial to file");
        double[] coeffs = {3, -2, 1, -5};   // Coefficients: 3x^4 - 2x^3 + x - 5
        int[] exps = {4, 3, 1, 0};          // Exponents: 4, 3, 1, 0

        Polynomial poly = new Polynomial(coeffs, exps);

        String fileName = "polynomial_test7.txt";
        try {
            poly.saveToFile(fileName);
            System.out.println("Polynomial saved to " + fileName);
            printFileContent(fileName);
        } catch (IOException e) {
            System.out.println("Error while saving or reading the file: " + e.getMessage());
        }
    }

    // Test Case 8: Constructor from file
    public static void testConstructorFromFile() {
        System.out.println("\nTest 8: Constructor from file");
        String fileName = "polynomial_test_input.txt";  // Make sure this file exists and contains a valid polynomial
        try {
            File file = new File(fileName);
            Polynomial poly = new Polynomial(file);

            printPolynomial(poly);  // Should print the polynomial in the file
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find file " + fileName);
        }
    }

    // Utility method to print the content of a file
    public static void printFileContent(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            System.out.print("Content of " + fileName + ": ");
            while (scanner.hasNextLine()) {
                String fileContent = scanner.nextLine();
                System.out.println(fileContent);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find file " + fileName);
        }
    }

    // Utility method to print a polynomial
    public static void printPolynomial(Polynomial poly) {
        System.out.print("Polynomial: ");
        for (int i = 0; i < poly.exponents.length; i++) {
            double coeff = poly.coefficients[i];
            int expo = poly.exponents[i];

            if (i > 0 && coeff > 0) {
                System.out.print("+");
            }

            if (expo == 0 || coeff != 1 && coeff != -1) {
                System.out.print(coeff);
            } else if (coeff == -1) {
                System.out.print("-");
            }

            if (expo > 0) {
                System.out.print("x");
                if (expo > 1) {
                    System.out.print(expo);
                }
            }
        }
        System.out.println();
    }
}
