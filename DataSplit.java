public class DataSplit<K, V> {
    private K xValue;
    private V yValue;

    public DataSplit(K xValue, V yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public K getXValue() {
        return xValue;
    }

    public V getYValue() {
        return yValue;
    }
}