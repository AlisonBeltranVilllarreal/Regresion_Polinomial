import java.util.List;

public class Modelo {
    private double[] coeficientes;
    private double error;  // Para almacenar el valor de R²

    public Modelo(double[] coeficientes, double error) {
        this.coeficientes = coeficientes;
        this.error = error;
    }

    public double getError() {
        return error;
    }

    public double[] getCoeficientes() {
        return coeficientes;
    }

    public static void encontrarMejorModelo(List<Modelo> modelos) {
        if (modelos == null || modelos.isEmpty()) {
            System.out.println("No hay modelos disponibles para comparar.");
            return;
        }

        // Inicializar el mejor modelo
        Modelo mejorModelo = modelos.get(0); // Cambiado a 0 para no saltar el primer modelo
        for (Modelo modelo : modelos) {
            if (modelo.getError() > mejorModelo.getError()) {
                mejorModelo = modelo;
            }
        }

        // Mostrar el mejor modelo
        System.out.println("\nEl mejor modelo de los Data Splitting:");
        
        // Mostrar la función de regresión
        System.out.print("F(X) = ");
        for (int i = 0; i < mejorModelo.getCoeficientes().length; i++) {
            System.out.printf("%.8f", mejorModelo.getCoeficientes()[i]);
            if (i < mejorModelo.getCoeficientes().length - 1) {
                System.out.print(" * X^" + i + " + ");
            }
        }
        
        // Mostrar el R²
        System.out.printf("\nCon R² de = %.8f%n", mejorModelo.getError());
    }
}
