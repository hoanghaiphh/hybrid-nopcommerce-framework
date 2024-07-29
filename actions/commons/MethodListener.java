package commons;

import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

public class MethodListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        Reporter.setCurrentTestResult(result);
        if (method.isTestMethod()) {
            VerificationFailures allFailures = VerificationFailures.getFailures();

            // Add an existing failure for the result to the failure list.
            if (result.getThrowable() != null) {
                allFailures.addFailureForTest(result, result.getThrowable());
            }

            List<Throwable> failures = allFailures.getFailuresForTest(result);

            if (failures.size() > 0) {
                result.setStatus(ITestResult.FAILURE);
                String lastFailure = failures.get(failures.size() - 1).toString();

                if (failures.size() == 1) {
                    result.setThrowable(failures.get(0));

                } else if (failures.size() == 2 && lastFailure.contains("java.lang.AssertionError")) {
                    result.setThrowable(failures.get(0));

                } else {
                    int adjustSize = 0;
                    if (lastFailure.contains("java.lang.AssertionError")) {
                        adjustSize = failures.size() - 1;
                    } else {
                        adjustSize = failures.size();
                    }

                    StringBuffer message = new StringBuffer(adjustSize + " Failures\n");
                    for (int i = 0; i < adjustSize - 1; i++) {
                        message.append("Failure " + (i + 1) + " of " + adjustSize + "\n");
                        message.append(Utils.longStackTrace(failures.get(i), false)).append("\n");
                    }
                    Throwable last = failures.get(adjustSize - 1);
                    message.append("Failure " + adjustSize + " of " + adjustSize + "\n");
                    message.append(last.toString());
                    Throwable merged = new Throwable(message.toString());
                    merged.setStackTrace(last.getStackTrace());
                    result.setThrowable(merged);
                }
            }
        }
    }
}
