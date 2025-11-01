package numberrangesummarizer;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Sivuyise MATWA
 * 
 * Unit tests for NumberRangeSummarizerImpl class.
 */

public class NumberRangeSummarizerImplTest {
   
   private NumberRangeSummarizerImpl summarizer;

   /** Fixture initialization (common initialization for all tests). **/
   @Before public void setUp() {
      summarizer = new NumberRangeSummarizerImpl();
   }

   // Tests for collect(input) method  (TOTAL of 5 TEST CASES)
   @Test
    public void testCollectValidInput() {
        Collection<Integer> result = summarizer.collect("1,2,3,5,7"); // valid
        assertEquals(Arrays.asList(1, 2, 3, 5, 7), new ArrayList<>(result));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCollectInvalidCharacter() {
        summarizer.collect("1, 2, a, 4"); // Have bad character
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCollectInvalidFormat() {
        summarizer.collect("1;2;3"); // not comma-separated
    }
    
    @Test
    public void testCollectHandlesSpaces() {
        Collection<Integer> result = summarizer.collect(" 10 ,  11 ,12 "); // comma seperate but with spaces
        assertEquals(Arrays.asList(10, 11, 12), new ArrayList<>(result));
    }
    
    @Test
    public void testCollectSingleNumber() {
        Collection<Integer> result = summarizer.collect("42"); // A single number string
        assertEquals(Collections.singletonList(42), new ArrayList<>(result));
    }
    
    // Test for summarizeCollection(input) method  (TOTAL of 7 TEST CASES)
    @Test
    public void testSummarizeCollectionNoInput() { // empty list
        String result = summarizer.summarizeCollection(Collections.emptyList());
        assertEquals("", result);
    }

    @Test
    public void testSummarizeCollectionSingleNumber() { // single number
        String result = summarizer.summarizeCollection(Collections.singletonList(42));
        assertEquals("42", result);
    }

    @Test
    public void testSummarizeCollectionNoSequentialNumbers() { // No sequential number
        String result = summarizer.summarizeCollection(Arrays.asList(1, 3, 5, 7));
        assertEquals("1, 3, 5, 7", result);
    }

    @Test
    public void testSummarizeCollectionWithSequentialNumbers() { // Increasing collection
        String result = summarizer.summarizeCollection(Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31));
        assertEquals("1, 3, 6-8, 12-15, 21-24, 31", result);
    }

    @Test
    public void testSummarizeCollectionNonIncreasing() { // Non increasing collection
        String result = summarizer.summarizeCollection(Arrays.asList(8, 2, 3, 5, 12, 10, 1, 9));
        assertEquals("1-3, 5, 8-10, 12", result);
    }
    
    @Test
    public void testSummarizeCollectionWithDuplicates() { // With Duplicate numbers
        String result = summarizer.summarizeCollection(Arrays.asList(1, 2, 2, 3, 5, 6, 8, 8));
        assertEquals("1-3, 5-6, 8", result);
    }
    
    @Test
    public void testSummarizeCollectionNonIncreasingWithDuplicates() { // Non increasing collection wih duplicates
        String result = summarizer.summarizeCollection(Arrays.asList(8, 2, 3, 5, 12, 9, 10, 1, 8, 9));
        assertEquals("1-3, 5, 8-10, 12", result);
    }

}
