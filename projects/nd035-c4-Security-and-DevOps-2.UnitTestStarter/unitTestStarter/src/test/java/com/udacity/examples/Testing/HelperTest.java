package com.udacity.examples.Testing;

import junit.framework.TestCase;
import org.junit.*;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HelperTest {

    @Before
    public void init() {
        System.out.println("init executed"); }
    @After
    public void teardown() {
        System.out.println("teardown executed"); }
    @Test
    public void test1() {
        assertEquals(1+2,3);
    }

    @Test
    public void verify_getCount() {
        List<String> empList = Arrays.asList("Olga", "", "Peter", "clown", "");
        final long count = Helper.getCount(empList);
        assertEquals(3,count);
    }

    @Test
    public void verify_getStat() {
        List<Integer> yrsOfExperience = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        //List<Integer> expectedList = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        final IntSummaryStatistics stats = Helper.getStats(yrsOfExperience);
        assertEquals(19, stats.getMax());
    }
    @Test
    public void compare_arrays() {
        int[] yrs = {10, 14, 2};
        int[] expectedYrs = {10,14, 2};
        assertArrayEquals(yrs, expectedYrs);
    }
	@Test
    public void verify_merged() {
        List<String> empList = Arrays.asList("Olga", "Peter");
        final String mergedList = Helper.getMergedList(empList);
        assertEquals("Olga, Peter", mergedList);
    }

    @Test
    public void validate_3lengthString() {
        List<String> empNames = Arrays.asList("sareeta", "", "Jef","sam");
        assertEquals(2, Helper.getStringsOfLength3(empNames));
    }
    @Test
    public void verify_list_is_squared(){
        List<Integer> yrsOfExperience = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        List<Integer> expected = Arrays.asList(169, 16, 225, 36, 289, 64, 361, 1, 4, 9);
        assertEquals(expected, Helper.getSquareList(yrsOfExperience));
    }
    @Ignore
    @Test
    public void verify_getMax(){
        List<Integer> empLevel = Arrays.asList(3,3,3,5,7,2,2,5,7,5);
        assertEquals(7, Helper.getStats(empLevel).getMax());
    }
    // This method must be public and static
    @BeforeClass
    public static void initClass() {
        System.out.println("init Class executed");
    }
    @AfterClass
    public static void teardownclass() {
        System.out.println("teardown Class executed");
    }
}
