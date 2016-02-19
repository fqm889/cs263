package orz.Type;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class StrValue extends Value {
    public String value;

    public StrValue(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
