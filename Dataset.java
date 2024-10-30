import java.util.Arrays;
import java.util.List;

public class Dataset {
    private List<Double> x;
    private List<Double> y;
    private Double B0;
    private Double B1;
    private Double B2;
    private Double error;

    public Dataset(List<Double> x, List<Double> y) {
        this.x = x;
        this.y = y;
    }

    public Dataset(Double B0, Double B1, Double B2, Double error) {
        this.B0 = B0;
        this.B1 = B1;
        this.B2 = B2;
        this.error = error;
    }

    public List<Double> getX() {
        return x;
    }

    public List<Double> getY() {
        return y;
    }


    public double getB0() {
        return B0;
    }

    public double getB1() {
        return B1;
    }

    public double getB2() {
        return B2;
    }

    public double getError() {
        return error;
    }
        public static Dataset datos() {
            List<Double> x = Arrays.asList(108.0, 115.0, 106.0, 97.0, 95.0, 91.0, 97.0, 83.0, 83.0, 78.0, 54.0, 67.0, 56.0, 53.0, 61.0, 115.0, 81.0, 78.0, 30.0, 45.0, 99.0, 32.0, 25.0, 28.0, 90.0, 89.0);
            List<Double> y = Arrays.asList(95.0, 96.0, 95.0, 97.0, 93.0, 94.0, 95.0, 93.0, 92.0, 86.0, 73.0, 80.0, 65.0, 69.0, 77.0, 96.0, 87.0, 89.0, 60.0, 63.0, 95.0, 61.0, 55.0, 56.0, 94.0, 93.0);
            return new Dataset(x, y);
    }
}

