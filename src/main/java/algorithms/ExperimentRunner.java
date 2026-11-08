package algorithms;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ExperimentRunner {

    private static final int REPETITIONS = 5;
    private static final String ALPHABET = "ABCD";
    private static long checksum = 0;

    public static void main(String[] args) throws Exception {

        Path folder = Path.of("results");
        Files.createDirectories(folder);

        Path file = folder.resolve("experiment_results.csv");

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {

            writer.write("algorithm,input_size,pattern_size,case_type,trial,time_ns");
            writer.newLine();

            runDynamicProgrammingExperiments(writer);
            runStringMatchingExperiments(writer);
            runKasaiExperiments(writer);
        }

        System.out.println("Experiments completed!");
        System.out.println("Results saved to: " + file.toAbsolutePath());
        System.out.println("Checksum: " + checksum);
    }

    private static void runDynamicProgrammingExperiments(
            BufferedWriter writer) throws Exception {

        int[] sizes = {100, 500, 1000};
        Random random = new Random(42);

        for (int size : sizes) {

            String str1 = randomString(size, random);
            String str2 = randomString(size, random);

            // Warm-up
            for (int i = 0; i < 3; i++) {
                LCS.findLCS(str1, str2);
                EditDistance.findDistance(str1, str2);
            }

            for (int trial = 1; trial <= REPETITIONS; trial++) {

                long start = System.nanoTime();
                int result = LCS.findLCS(str1, str2);
                long end = System.nanoTime();

                checksum += result;

                writeResult(writer, "LCS", size, 0,
                        "N/A", trial, end - start);

                start = System.nanoTime();
                result = EditDistance.findDistance(str1, str2);
                end = System.nanoTime();

                checksum += result;

                writeResult(writer, "EditDistance", size, 0,
                        "N/A", trial, end - start);
            }
        }
    }

    private static void runStringMatchingExperiments(
            BufferedWriter writer) throws Exception {

        int[] sizes = {10000, 50000, 100000};
        int patternSize = 10;

        Random random = new Random(42);

        for (int size : sizes) {

            String text = randomString(size, random);

            String presentPattern = text.substring(
                    size / 2, size / 2 + patternSize);

            String absentPattern = "ZZZZZZZZZZ";

            String[] patterns = {
                    presentPattern,
                    absentPattern
            };

            String[] caseTypes = {
                    "present",
                    "absent"
            };

            for (int p = 0; p < patterns.length; p++) {

                String pattern = patterns[p];
                String caseType = caseTypes[p];

                // Warm-up
                for (int i = 0; i < 3; i++) {
                    NaiveSearch.search(text, pattern);
                    KMP.search(text, pattern);
                    RabinKarp.search(text, pattern);
                }

                for (int trial = 1; trial <= REPETITIONS; trial++) {

                    long start = System.nanoTime();
                    List<Integer> result =
                            NaiveSearch.search(text, pattern);
                    long end = System.nanoTime();

                    checksum += result.size();

                    writeResult(writer, "NaiveSearch", size,
                            patternSize, caseType, trial, end - start);

                    start = System.nanoTime();
                    result = KMP.search(text, pattern);
                    end = System.nanoTime();

                    checksum += result.size();

                    writeResult(writer, "KMP", size,
                            patternSize, caseType, trial, end - start);

                    start = System.nanoTime();
                    result = RabinKarp.search(text, pattern);
                    end = System.nanoTime();

                    checksum += result.size();

                    writeResult(writer, "RabinKarp", size,
                            patternSize, caseType, trial, end - start);
                }
            }
        }
    }

    private static void runKasaiExperiments(
            BufferedWriter writer) throws Exception {

        int[] sizes = {1000, 5000, 10000};
        Random random = new Random(42);

        for (int size : sizes) {

            String text = randomString(size, random);

            int[] suffixArray = buildSuffixArray(text);

            // Warm-up
            for (int i = 0; i < 3; i++) {
                Kasai.buildLCP(text, suffixArray);
            }

            for (int trial = 1; trial <= REPETITIONS; trial++) {

                long start = System.nanoTime();
                int[] lcp = Kasai.buildLCP(text, suffixArray);
                long end = System.nanoTime();

                checksum += Arrays.stream(lcp).sum();

                writeResult(writer, "Kasai", size, 0,
                        "N/A", trial, end - start);
            }
        }
    }

    private static String randomString(int length, Random random) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            result.append(ALPHABET.charAt(index));
        }

        return result.toString();
    }

    private static int[] buildSuffixArray(String text) {

        int n = text.length();
        Integer[] suffixes = new Integer[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }

        Arrays.sort(suffixes,
                (a, b) -> compareSuffixes(text, a, b));

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = suffixes[i];
        }

        return result;
    }

    private static int compareSuffixes(
            String text, int first, int second) {

        while (first < text.length()
                && second < text.length()) {

            char a = text.charAt(first);
            char b = text.charAt(second);

            if (a != b) {
                return Character.compare(a, b);
            }

            first++;
            second++;
        }

        return Integer.compare(
                text.length() - first,
                text.length() - second);
    }

    private static void writeResult(
            BufferedWriter writer,
            String algorithm,
            int inputSize,
            int patternSize,
            String caseType,
            int trial,
            long time) throws Exception {

        writer.write(
                algorithm + "," +
                        inputSize + "," +
                        patternSize + "," +
                        caseType + "," +
                        trial + "," +
                        time
        );

        writer.newLine();
    }
}