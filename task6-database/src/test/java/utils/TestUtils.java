package utils;

import lombok.experimental.UtilityClass;
import org.testng.ITestResult;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static int getNewStatusId(int initialStatusId) {
        List<String> statusIds = new ArrayList<>(Arrays.asList("1", "2", "3"));
        statusIds.remove(String.valueOf(initialStatusId));
        int randomIndex = RandomUtils.getRandomInt(statusIds.size());
        return Integer.parseInt(statusIds.get(randomIndex));
    }
}
