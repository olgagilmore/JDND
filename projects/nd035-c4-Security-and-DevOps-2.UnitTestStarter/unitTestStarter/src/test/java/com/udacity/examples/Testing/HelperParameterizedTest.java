package com.udacity.examples.Testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class HelperParameterizedTest {
    private String input;
    private String output;

    public HelperParameterizedTest(String input, String output) {
        super();
        this.input = input;
        this.output = output;
    }
    @Parameterized.Parameters
    public static Collection initData() {
        String empList[][] ={ {"x", "y"}, {"x", "x"}};
        return Arrays.asList(empList);
    }

    @Test
    public void verify_input_is_not_equal_output() {
        assertNotEquals(input, output);
    }

    /*@RunWith()
    @SelectClasses({HelperParameterizedTest.class, HelperTest});*/
}
