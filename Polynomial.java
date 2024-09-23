class Polynomial{
    public double[] coefficients;

    public Polynomial(){
        coefficients = new double[1];
        coefficients[0] = 0;
    }

    public Polynomial(double[] array){
        coefficients = new double[array.length];
        for(int i = 0; i < array.length; i++){
            coefficients[i] = array[i];
        }
    }

    public Polynomial add(Polynomial p){
        double[] array = p.coefficients;
        int maxLength;
        if(array.length >= coefficients.length){
            maxLength = array.length;
        }
        else{
            maxLength = coefficients.length;
        }
        double[] newCoefficients = new double[maxLength];

        for(int i = 0; i < maxLength; i++){
            double first;
            double second;
            if(i < coefficients.length){
                first = coefficients[i];
            }
            else{
                first = 0;
            }

            if(i < array.length){
                second = array[i];
            }
            else{
                second = 0;
            }

            newCoefficients[i] = first + second;
        }
        Polynomial pNew = new Polynomial(newCoefficients);
        return pNew;
    }

    public double evaluate(double x){
        double result = 0;
        double length = coefficients.length;
        for(int i = 0; i < length; i++){
            result += coefficients[i]*(Math.pow(x, i));
        }
        return result;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0.0;
    }
}