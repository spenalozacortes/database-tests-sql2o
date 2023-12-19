package utils;

import lombok.experimental.UtilityClass;
import org.testng.ITestResult;

import java.time.Duration;

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

    public static Duration getTestDuration(ITestResult result) {
        long startTime = result.getStartMillis();
        long endTime = result.getEndMillis();
        long durationInMilliseconds = endTime - startTime;
        long seconds = durationInMilliseconds / 1000;
        long nanos = (durationInMilliseconds % 1000) * 1_000_000;
        return Duration.ofSeconds(seconds, nanos);
    }
}
