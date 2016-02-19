package orz.Type;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class BoolValue extends Value {
    public boolean value;


    public BoolValue(boolean value) {
        this.value = value;
    }

    public String toString() {
        return value ? "true" : "false";
    }
}
