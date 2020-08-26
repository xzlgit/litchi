package cn.litchi.model.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
    public static List<Long> splitGetLongIds(String ids) {
        return Arrays.asList(ids.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
    }

    public static String trim(String string) {
        return string.replaceAll(" ", "");
    }
}
