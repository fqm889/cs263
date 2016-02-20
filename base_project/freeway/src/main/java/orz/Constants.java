package orz;

import orz.Type.*;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Constants {

    public static final String LINE_COMMENT = "//";

    public static final String LIST_BEGIN = "(";
    public static final String LIST_END = ")";

    public static final String ARRAY_BEGIN = "[";
    public static final String ARRAY_END = "]";

    public static final String IF_KEYWORD = "if";
    public static final String DEFN_KEYWORD = "defn";
    public static final String DEF_KEYWORD = "def";
    public static final String ASSIGN_KEYWORD = "set!";
    public static final String LET_KEYWORD = "let";
    public static final String LOOP_KEYWORD = "loop";
    public static final String RECUR_KEYWORD = "recur";

    public static final Value INT_TYPE = new IntT();
    public static final Value REAL_TYPE = new RealT();
    public static final Value BOOL_TYPE = new BoolT();
    public static final Value CHAR_TYPE = new CharT();
    public static final Value Str_TYPE = new StrT();
    public static final Value VECTOR_TYPE = new VectorT();
}

