import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

class Polynomial{
    public double[] coefficients;
    public int[] exponents;

    public Polynomial(){
        coefficients = null;
        exponents = null;
    }

    public Polynomial(double[] coeffs, int[] exps){
        this.coefficients = new double[coeffs.length];
        for(int i = 0; i < coeffs.length; i++){
            this.coefficients[i] = coeffs[i];
        }

        this.exponents = new int[exps.length];
        for(int i = 0; i < exps.length; i++){
            this.exponents[i] = exps[i];
        }
    }

    //Another constructor to take in a HashMap rather than two arrays to create a polynomial
    public Polynomial(HashMap<Integer, Double> p){
        this.coefficients = new double[p.size()];
        this.exponents = new int[p.size()];

        int i = 0;
        for(HashMap.Entry<Integer, Double> j : p.entrySet()){
            this.exponents[i] = j.getKey();
            this.coefficients[i] = j.getValue();
            i++;
        }
    }

    public Polynomial(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        String polynomial = scanner.nextLine();
        scanner.close();
        polynomial = polynomial.replaceAll("[-]", "+-");
        String[] terms = polynomial.split("[+]");

        HashMap<Integer, Double> polynomialDict = new HashMap<>();

        for(int i = 0; i < terms.length; i++){
            String term = terms[i].trim();

            if(term.contains("x")){
                String[] parts = term.split("x");
                double coeff;
                int expo;

                if(parts[0].isEmpty() || parts[0].equals("+")){
                    coeff = 1;
                }
                else if(parts[0].equals("-")){
                    coeff = -1;
                }
                else{
                    coeff = Double.parseDouble(parts[0]);
                }

                if(parts.length == 1){
                    expo = 1;
                }
                else{
                    expo = Integer.parseInt(parts[1]);
                }

                if(polynomialDict.containsKey(expo)){
                    polynomialDict.put(expo, polynomialDict.get(expo) + coeff);
                }
                else{
                    polynomialDict.put(expo, coeff);
                }
            }
            else{
                int expo = 0;
                double coeff = Double.parseDouble(term);
                if(polynomialDict.containsKey(expo)){
                    polynomialDict.put(expo, polynomialDict.get(0) + coeff);
                }
                else{
                    polynomialDict.put(expo, coeff);
                }
            }
        }

        this.coefficients = new double[polynomialDict.size()];
        this.exponents = new int[polynomialDict.size()];

        int i = 0;
        for(HashMap.Entry<Integer, Double> j : polynomialDict.entrySet()){
            this.exponents[i] = j.getKey();
            this.coefficients[i] = j.getValue();
            i++;
        }
        
    }

    public void saveToFile(String fileName) throws IOException{
        FileWriter writer = new FileWriter(fileName);

        for(int i = 0; i < exponents.length; i++){
            double coeff = coefficients[i];
            int expo = exponents[i];

            if(i > 0 && coeff > 0){
                writer.write("+");
            }

            if(expo == 0 || coeff != 1 || coeff != -1){
                writer.write(String.valueOf(coeff));
            }
            else if(coeff == -1){
                writer.write("-");
            }

            if(expo > 1){
                writer.write("x");
                if(expo > 1){
                    writer.write(String.valueOf(expo));
                }
            }
        }

        writer.close();
    }

    public Polynomial add(Polynomial p){
        double[] arrayC1 = this.coefficients;
        int[] arrayE1 = this.exponents;

        double[] arrayC2 = p.coefficients;
        int[] arrayE2 = p.exponents;

        HashMap<Integer, Double> poly1 = new HashMap<>();
        HashMap<Integer, Double> poly2 = new HashMap<>();

        for(int i = 0; i < arrayE1.length; i++){
            poly1.put(arrayE1[i], arrayC1[i]);
        }

        for(int i = 0; i < arrayE2.length; i++){
            poly2.put(arrayE2[i], arrayC2[i]);
        }

        HashMap<Integer, Double> finPoly = new HashMap<>();

        for(Integer key : poly1.keySet()){
            finPoly.put(key, poly1.get(key));
        }

        for(Integer key : poly2.keySet()){
            if(finPoly.containsKey(key)){
                finPoly.put(key, finPoly.get(key) + poly2.get(key));
            }
            else{
                finPoly.put(key, poly2.get(key));
            }
        }
        Polynomial finalPolynomial = new Polynomial(finPoly);
        
        return finalPolynomial;
    }

    public Polynomial multiply(Polynomial p){
        double[] arrayC1 = this.coefficients;
        int[] arrayE1 = this.exponents;

        double[] arrayC2 = p.coefficients;
        int[] arrayE2 = p.exponents;

        HashMap<Integer, Double> poly1 = new HashMap<>();
        HashMap<Integer, Double> poly2 = new HashMap<>();

        for(int i = 0; i < arrayE1.length; i++){
            poly1.put(arrayE1[i], arrayC1[i]);
        }

        for(int i = 0; i < arrayE2.length; i++){
            poly2.put(arrayE2[i], arrayC2[i]);
        }

        HashMap<Integer, Double> finPoly = new HashMap<>();

        int keyNew;
        double valueNew;
        for(Integer key1 : poly1.keySet()){
            for(Integer key2 : poly2.keySet()){
                keyNew = key1 + key2;
                valueNew = poly1.get(key1) * poly2.get(key2);
                if(finPoly.containsKey(keyNew)){
                    finPoly.put(keyNew, finPoly.get(keyNew) + valueNew);
                }
                else{
                    finPoly.put(keyNew, valueNew);
                }
            }
        }
        Polynomial finalPolynomial = new Polynomial(finPoly);
        
        return finalPolynomial;
    }

    public double evaluate(double x){
        double result = 0;
        int length = exponents.length;
        for(int i = 0; i < length; i++){
            result += coefficients[i] * (Math.pow(x, exponents[i]));
        }
        return result;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0.0;
    }
}