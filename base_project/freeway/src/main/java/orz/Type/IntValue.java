package orz.Type;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class IntValue extends Value {
    public int value;

    public IntValue(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
