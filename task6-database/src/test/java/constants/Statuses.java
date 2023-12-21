package constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Statuses {
    PASSED(1),
    FAILED(2),
    SKIPPED(3);

    private final int statusId;

    public static Statuses fromInt(int status) {
        switch (status) {
            case 1: return PASSED;
            case 2: return FAILED;
            default: return SKIPPED;
        }
    }
}
