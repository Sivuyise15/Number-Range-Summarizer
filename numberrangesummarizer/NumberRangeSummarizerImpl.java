package numberrangesummarizer;
import java.util.Collection;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * @author Sivuyise MATWA
 * Credit: Werner
 *
 * Implementation of NumberRangeSummarizer interface to produce a comma delimited list of numbers,
 * grouping the numbers into a range when they are sequential.
 *
 * Sample Input: "1,3,6,7,8,12,13,14,15,21,22,23,24,31"
 * Result: "1, 3, 6-8, 12-15, 21-24, 31"
 */

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    /*
    * Collects numbers from a comma-separated string input.
    *
    * @param input, the input string containing numbers
    * @return a collection of integers parsed from the input string
    */
    @Override
    public Collection<Integer> collect(String input) {
        Collection<Integer> numbers = new ArrayList<>();
        for (String part : input.split(",")) {
            try {
                numbers.add(Integer.parseInt(part.trim()));
            } catch (NumberFormatException e) {
                // Throws an exception for invalid number formats. i.e. non-integer values and not comma separated.
                throw new IllegalArgumentException("Invalid number format: " + part);
            }
        }
        return numbers;
    }

    /*
    * Summarizes the collection of numbers by calculating range of sequential numbers.
    * KEY ASSUMPTION: The input collection may contain duplicates and is not guaranteed to be sorted.
    * Only for the purpose of this assignment we will take the cost of sorting the collection.
    *
    * @param input, the collection of integers to summarize
    * @return a comma-delimited string, summarizing the ranges of sequential numbers
    */

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        List<Integer> sortedNumbers = new ArrayList<>(input);
        Collections.sort(sortedNumbers);

        StringBuilder result = new StringBuilder();
        int start = sortedNumbers.get(0);
        int end = start;

        for (int i = 1; i < sortedNumbers.size(); i++) {
            int current = sortedNumbers.get(i);
            if (current == end + 1 || current == end) { // handling duplicates as well
                end = current;
            } else {
                appendRange(result, start, end);
                start = current;
                end = start;
            }
        }
        appendRange(result, start, end);
        // removing the trailing comma & space
        if (result.length() > 2) {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }

    // Helper method to append a range or single number to the result string
    private void appendRange(StringBuilder sb, int start, int end) {
        if (start == end) {
            sb.append(start);
        } else {
            sb.append(start).append("-").append(end);
        }
        sb.append(", ");
    }

    // Main method for manual testing
    public static void main(String[] args) {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a list of comma-separated numbers (e.g., 1,2,3,5,6,8): ");
        String input = scanner.nextLine();
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        System.out.println("Result: " + result);
    }

}