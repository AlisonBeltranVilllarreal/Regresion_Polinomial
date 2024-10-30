import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegresionPolinomial {
    private Dataset dataset;
    private int grado;
    private double[] coeficientes;


    // Lista para almacenar los modelos
    public static List<Modelo> modelos = new ArrayList<>();

    public RegresionPolinomial(Dataset dataset, int grado) {
        this.dataset = dataset;
        this.grado = grado;
        this.coeficientes = new double[grado + 1];
        this.calcularRegresion();
    }

    private void calcularRegresion() {
        List<Double> x = this.dataset.getX();
        List<Double> y = this.dataset.getY();

        // Construcción de la matriz A y el vector B
        double[][] A = new double[grado + 1][grado + 1];
        double[] B = new double[grado + 1];

        for (int i = 0; i <= grado; i++) {
            for (int j = 0; j <= grado; j++) {
                A[i][j] = Mate_Discreta.Suma_Potencia_X(x, i + j);
            }
            B[i] = Mate_Discreta.Suma_Potencia_XY(x, y, i);
        }

        // Resolver el sistema de ecuaciones
        this.coeficientes = Mate_Discreta.gaussJordan(A, B);
        Double R2=calcularR2(x,y,coeficientes);
        // Crear una instancia de Modelo 
        Modelo modelo = new Modelo(coeficientes, R2);
        //Comentado para que despues no se compare con los otros modelos
        //modelos.add(modelo);
        mostrarModelo();
        System.out.println("El error cuadratico es de: " + R2);

    }

    public static double calcularR2(List<Double> dataX, List<Double> dataY, double[] coeficientes) {
        double ssTotal = 0;
        double ssResiduos = 0;
        double mediaY = Mate_Discreta.Media_Y(dataY);
        double r2;
    
        // Recorrer los datos
        for (int i = 0; i < dataY.size(); i++) {
            double X = dataX.get(i);
            double Y = dataY.get(i);
    
            // Calcular la predicción usando el polinomio de cualquier grado
            double prediccion = 0;
            for (int j = 0; j < coeficientes.length; j++) {
                prediccion += coeficientes[j] * Math.pow(X, j);
            }
    
            // Suma de los cuadrados totales (total sum of squares)
            ssTotal += Math.pow(Y - mediaY, 2);
    
            // Suma de los residuos (residual sum of squares)
            ssResiduos += Math.pow(Y - prediccion, 2);
        }
    
        // Cálculo del R²
        r2 = 1 - (ssResiduos / ssTotal);
    
        return r2;
    }

    

    private void mostrarModelo() {
        System.out.print("\nLa ecuación de la regresión es: \nY = ");
        for (int i = 0; i < coeficientes.length; i++) {
            System.out.print(coeficientes[i] + "*X^" + i);
            if (i < coeficientes.length - 1) {
                System.out.print(" + ");
            }
        }
        System.out.println();  // Para un salto de línea al final de la ecuación
    }

    public double prediccion(double x) {
        double resultado = 0.0;
        for (int i = 0; i < coeficientes.length; i++) {
            resultado += coeficientes[i] * Math.pow(x, i);
        }
        return resultado;
    }

    public void formula2() {
        List<Double> x = new ArrayList<>(this.dataset.getX());
        List<Double> y = new ArrayList<>(this.dataset.getY());

        // Dividir el conjunto en 70% entrenamiento, 30% prueba
        int splitIndex = (int) (x.size() * 0.7);
        List<Double> xTrain = x.subList(0, splitIndex);
        List<Double> yTrain = y.subList(0, splitIndex);
        List<Double> xTest = x.subList(splitIndex, x.size());
        List<Double> yTest = y.subList(splitIndex, y.size());

        System.out.println("\nRegresión con 70% entrenamiento y 30% prueba");
        calcularRegresionConDatos(xTrain, yTrain);
        Double R2=calcularR2(xTest,yTest,coeficientes);
        // Crear una instancia de Modelo 
        Modelo modelo = new Modelo(coeficientes, R2);
        modelos.add(modelo);
        mostrarModelo();
        System.out.println("El error cuadratico es de: " + R2);
        evaluarModelo(xTest, yTest);
    }

    public void formula3() {
        List<Double> x = new ArrayList<>(this.dataset.getX());
        List<Double> y = new ArrayList<>(this.dataset.getY());

        // Dividir el conjunto en 70% entrenamiento desde el final, 30% prueba al inicio
        int splitIndex = (int) (x.size() * 0.3); // Índice de inicio de la parte de entrenamiento
        List<Double> xTest = x.subList(0, splitIndex);
        List<Double> yTest = y.subList(0, splitIndex);
        List<Double> xTrain = x.subList(splitIndex, x.size()); // 70% de entrenamiento desde el final
        List<Double> yTrain = y.subList(splitIndex, y.size());

        System.out.println("\nRegresión con 70% entrenamiento (desde el final) y 30% prueba");
        calcularRegresionConDatos(xTrain, yTrain);
        Double R2=calcularR2(xTest,yTest,coeficientes);
        // Crear una instancia de Modelo 
        Modelo modelo = new Modelo(coeficientes, R2);
        modelos.add(modelo);
        mostrarModelo();
        System.out.println("El error cuadratico es de: " + R2);
        evaluarModelo(xTest, yTest);
    }

    public void formula4() {
        List<Double> x = new ArrayList<>(this.dataset.getX());
        List<Double> y = new ArrayList<>(this.dataset.getY());
    
        // Combinar x e y en un solo dataset para mezclar    
        List<DataSplit<Double, Double>> dataset = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            dataset.add(new DataSplit<>(x.get(i), y.get(i)));
        }
    
        // Mezclar los datos
        Collections.shuffle(dataset);
        
        // Crear listas para entrenamiento y prueba
        List<Double> xTrain = new ArrayList<>();
        List<Double> yTrain = new ArrayList<>();
        List<Double> xTest = new ArrayList<>();
        List<Double> yTest = new ArrayList<>();
    
        // Calcular el tamaño de los conjuntos
        int trainSize = (int) (dataset.size() * 0.7);
        
        // Dividir en conjuntos de entrenamiento y prueba
        for (int i = 0; i < trainSize; i++) {
            xTrain.add(dataset.get(i).getXValue());
            yTrain.add(dataset.get(i).getYValue());
        }
        
        for (int i = trainSize; i < dataset.size(); i++) {
            xTest.add(dataset.get(i).getXValue());
            yTest.add(dataset.get(i).getYValue());
        }
    
        System.out.println("\nRegresión con 70% entrenamiento y 30% prueba aleatoria");
        calcularRegresionConDatos(xTrain, yTrain);
        Double R2 = calcularR2(xTest, yTest, coeficientes);
        // Crear una instancia de Modelo 
        Modelo modelo = new Modelo(coeficientes, R2);
        modelos.add(modelo);
        mostrarModelo();
        System.out.println("El error cuadratico es de: " + R2);
        evaluarModelo(xTest, yTest);
    }
    

    private void calcularRegresionConDatos(List<Double> x, List<Double> y) {
        // Similar al método calcularRegresion, pero usando los datos de entrada proporcionados
        double[][] A = new double[grado + 1][grado + 1];
        double[] B = new double[grado + 1];

        for (int i = 0; i <= grado; i++) {
            for (int j = 0; j <= grado; j++) {
                A[i][j] = Mate_Discreta.Suma_Potencia_X(x, i + j);
            }
            B[i] = Mate_Discreta.Suma_Potencia_XY(x, y, i);
        }

        // Resolver el sistema de ecuaciones
        this.coeficientes = Mate_Discreta.gaussJordan(A, B);

    }

    private void evaluarModelo(List<Double> xTest, List<Double> yTest) {
        System.out.println("Evaluación del modelo con datos de prueba:");
        for (int i = 0; i < xTest.size(); i++) {
            double pred = prediccion(xTest.get(i));
            System.out.println("Predicción: " + pred + " | Valor real: " + yTest.get(i));
        }
    }

    public static List<Modelo> getModelos() {
        return modelos;
    }
}

