import java.util.List;

public class Mate_Discreta {
    
    // Método para calcular la suma de potencias de X
    public static double Suma_Potencia_X(List<Double> listaX, int grado) {
        double suma = 0.0;
        for (double x : listaX) {
            suma += Math.pow(x, grado);
        }
        return suma;
    }

    // Método para calcular la suma de potencias de XY
    public static double Suma_Potencia_XY(List<Double> listaX, List<Double> listaY, int grado) {
        double suma = 0.0;
        for (int i = 0; i < listaX.size(); i++) {
            suma += Math.pow(listaX.get(i), grado) * listaY.get(i);
        }
        return suma;
    }

    // Método de eliminación de Gauss-Jordan
    public static double[] gaussJordan(double[][] A, double[] B) {
        int numRows = A.length;
        int numCols = A[0].length;

        // Crear la matriz aumentada con A y B
        double[][] augmentedMatrix = new double[numRows][numCols + 1];
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(A[i], 0, augmentedMatrix[i], 0, numCols);
            augmentedMatrix[i][numCols] = B[i];
        }

        // Aplicar el método de Gauss-Jordan para reducir a forma escalonada
        for (int pivot = 0; pivot < numRows; pivot++) {
            // Hacer que el pivote sea 1
            double pivotValue = augmentedMatrix[pivot][pivot];
            for (int j = pivot; j < numCols + 1; j++) {
                augmentedMatrix[pivot][j] /= pivotValue;
            }

            // Hacer que todos los elementos de la columna del pivote sean 0 excepto el propio pivote
            for (int i = 0; i < numRows; i++) {
                if (i != pivot) {
                    double factor = augmentedMatrix[i][pivot];
                    for (int j = pivot; j < numCols + 1; j++) {
                        augmentedMatrix[i][j] -= factor * augmentedMatrix[pivot][j];
                    }
                }
            }
        }

        // Extraer las soluciones del sistema (última columna de la matriz aumentada)
        double[] result = new double[numRows];
        for (int i = 0; i < numRows; i++) {
            result[i] = augmentedMatrix[i][numCols];
        }

        return result;
    }

    // Método para calcular la suma de los elementos de Y
    public static double Suma_Y(List<Double> listaY) {
        double suma = 0.0;
        for (double y : listaY) {
            suma += y;
        }
        return suma;
    }
    
    // Método para calcular la media de Y
    public static double Media_Y(List<Double> listaY) {
        return Suma_Y(listaY) / listaY.size();
    }

    // Método para calcular la varianza de Y
    public static double Varianza_Y(List<Double> listaY) {
        double media = Media_Y(listaY);
        double varianza = 0.0;
        for (double y : listaY) {
            varianza += Math.pow(y - media, 2);
        }
        return varianza / (listaY.size() - 1); // Usar n-1 para la varianza muestral
    }

    // Método para calcular la desviación estándar de Y
    public static double Desviacion_Estandar_Y(List<Double> listaY) {
        return Math.sqrt(Varianza_Y(listaY));
    }
}
