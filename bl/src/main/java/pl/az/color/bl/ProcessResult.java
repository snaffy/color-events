package pl.az.color.bl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProcessResult {

    private final boolean isValid;
    private final FailureReason failureReason;

    static ProcessResult valid() {
        return new ProcessResult(true, null);
    }

    static ProcessResult nothingToProcess() {
        return new ProcessResult(false, FailureReason.NOTHING_TO_PROCESS);
    }

    private enum FailureReason {
        NOTHING_TO_PROCESS
    }
}
