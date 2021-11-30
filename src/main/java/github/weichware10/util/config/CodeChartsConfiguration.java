package github.weichware10.util.config;

import java.util.Arrays;

/**
 * Beinhaltet Konfiguration für CodeCharts-Versuche.
 */
public class CodeChartsConfiguration extends ToolConfiguration {
    private String[] strings = { "foobar", "barfoo" };
    private float initialSize = 4.2f;
    private float[] timings = { 2.0f, 2.0f };

    // GETTERS

    public String[] getStrings() {
        return strings;
    }

    public float getInitialSize() {
        return initialSize;
    }

    public float[] getTimings() {
        return timings;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("CodeChartsConfiguration");
        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CodeChartsConfiguration that = (CodeChartsConfiguration) (obj);
        return Arrays.equals(strings, that.strings)
                && initialSize == that.initialSize
                && Arrays.equals(timings, that.timings);
    }

    @Override
    public String toString() {
        return String.format("""
                codeChartsConfiguration: {
                        %s, strings: %s, initialSize: %f, timings: %s }""",
                super.toString(),
                Arrays.toString(strings),
                initialSize,
                Arrays.toString(timings));
    }
}
