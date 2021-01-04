import org.junit.*;
import org.junit.rules.*;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaLoopsTests {

    //TestName - глобальная переменная для получения имени конкретного тестового метода в любом методе тестового класса
    @Rule
    public final TestName name = new TestName();

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting tests....");
        System.out.println("JavaLoopsTests.class.getPackageName():\n" + JavaLoopsTests.class.getPackage().getName());

    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("All tests finished!");
    }

    @Before
    public void setUp() {
        //testCount++;
        //System.out.println("Starting test " + testCount + "...");
        System.out.println("Starting test " + name.getMethodName() + "...");
    }

    @After
    public void tearDown() {
        //System.out.println("Finishing test " + testCount + "\n");
        System.out.println("Finishing test " + name.getMethodName() + "\n");
    }

    private class TestData {
        public TestData(int a, int b, int n, String expected) {
            this.a = a;
            this.b = b;
            this.n = n;
            this.expected = expected;
        }
        public int a, b, n;
        public String expected;
    }

    private void testPrintSeqHelper(TestData testData) {

        PrintStream stdout = System.out;
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        try {
            System.out.println("Test case: a=" + testData.a +
                    "; b=" + testData.b +
                    "; n=" + testData.n);

            System.setOut(new PrintStream(result));

            JavaLoops.printSeq(testData.a, testData.b, testData.n);
            Assert.assertEquals(testData.expected, result.toString());
        } finally {
            System.setOut(stdout);
        }
    }

    @Test
    public void testPrintSeq() {
        TestData [] testData =
                {
                        // new TestData(0, 0, 0, ""), // ?
                        new TestData(0, 0, 1, "0 \r\n"), // boundary conditions
                        new TestData(1, 0, 1, "1 \r\n"), // boundary conditions
                        new TestData(0, 1, 1, "1 \r\n"), // boundary conditions
                        new TestData(1, 1, 1, "2 \r\n"), // boundary conditions
                        new TestData(1, 1, 2, "2 4 \r\n"), // boundary conditions
                        new TestData(2, 2, 2, "4 8 \r\n"), // boundary conditions
                        new TestData(49, 49, 1, "98 \r\n"), // boundary conditions
                        new TestData(50, 50, 1, "100 \r\n"), // boundary conditions
                        new TestData(50, 50, 15, "100 200 400 800 1600 3200 6400 12800 25600 51200 " +
                                "102400 204800 409600 819200 1638400 \r\n") // boundary conditions
                        // new TestData(aaa, aaa, aaa, "") // ? error prediction
                };

        for (TestData currentTest : testData) {
            testPrintSeqHelper(currentTest);
        }
    }
}
