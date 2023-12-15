package utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {

    public static String getPackageName(String className) {
        int lastDotIndex = className.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return className.substring(0, lastDotIndex);
        } else {
            return "";
        }
    }
}
