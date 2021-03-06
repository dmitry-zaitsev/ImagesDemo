package com.example.pipe;

import org.junit.Test;


public class TesterTest {

    Source<String> source = new Source<>();
    Pipe<String> pipe = Pipe.fromSource(source);

    @Test(expected = AssertionError.class)
    public void assertValues_Fail_NoValues() throws Exception {
        // Given
        Tester<String> tester = Tester.test(pipe);

        // When
        tester.assertValues("A");

        // Then
        // Expect exception
    }

    @Test(expected = AssertionError.class)
    public void assertValues_Fail_DifferentValue() throws Exception {
        // Given
        Tester<String> tester = Tester.test(pipe);
        source.push("B");

        // When
        tester.assertValues("A");

        // Then
        // Expect exception
    }

    @Test(expected = AssertionError.class)
    public void assertValues_Fail_DifferentCount() throws Exception {
        // Given
        Tester<String> tester = Tester.test(pipe);
        source.push("A");
        source.push("B");
        source.push("C");

        // When
        tester.assertValues("A", "B");

        // Then
        // Expect exception
    }

    @Test
    public void assertValues_Success() throws Exception {
        // Given
        Tester<String> tester = Tester.test(pipe);
        source.push("A");
        source.push("B");

        // When
        tester.assertValues("A", "B");

        // Then
        // Success
    }

    @Test
    public void assertEmpty_Empty() throws Exception {
        // Given
        Tester<String> tester = Tester.test(pipe);

        // When
        tester.assertEmpty();

        // Then
        // Success
    }

    @Test(expected = AssertionError.class)
    public void assertEmpty_NotEmpty() throws Exception {
        // Given
        Tester<String> tester = Tester.test(pipe);
        source.push("A");

        // When
        tester.assertEmpty();

        // Then
        // Expect exception
    }
}