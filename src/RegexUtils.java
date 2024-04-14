WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com



public class RegexUtils {

    public static RegexFlag[] empteyList() {
        RegexFlag[] flags = new RegexFlag[TOYConstants.DEFAULT_SIZE];
        for (int i = 0; i < TOYConstants.DEFAULT_SIZE; i++) {
            flags[i] = RegexFlag.DEFAULT_CHAR;
        }
        flags['.'] = RegexFlag.ANY;
        flags['^'] = RegexFlag.AT_BOL;
        flags[']'] = RegexFlag.CCL_END;
        flags['['] = RegexFlag.CCL_START;
        flags[')'] = RegexFlag.CLOSE_PAREN;
        flags['*'] = RegexFlag.CLOSURE;
        flags['-'] = RegexFlag.DASH;
        flags['('] = RegexFlag.OPEN_PAREN;
        flags['?'] = RegexFlag.OPTIONAL;
        flags['|'] = RegexFlag.OR;
        flags['+'] = RegexFlag.PLUS_CLOSE;
        return flags;
    }

}
