package orz.Type;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class CharValue extends Value {
    public char value;

    public CharValue(char value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
