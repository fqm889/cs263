package orz.Type;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class RealValue extends Value {
    public double value;

    public RealValue(double value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
