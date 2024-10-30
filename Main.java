
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int grado = 2; 

        List<Modelo> modelos;

        Dataset dataSet = Dataset.datos();
        RegresionPolinomial regresionPolinomial = new RegresionPolinomial(dataSet, grado);
        System.out.println("\nData splitting: ");
        
        // Llamar a los métodos de división de datos
        //70-30
        regresionPolinomial.formula2();
        //30-70
        regresionPolinomial.formula3();
        //70-30 pero aleatorio
        regresionPolinomial.formula4();

        modelos=RegresionPolinomial.getModelos();
        Modelo.encontrarMejorModelo(modelos);
    }
}
