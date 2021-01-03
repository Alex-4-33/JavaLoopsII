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
                        new TestData(1, 2, 3, "3 7 15 \r\n"),
                        new TestData(5, 5, 5, "10 20 40 80 160 \r\n")
                };

        for (TestData currentTest : testData) {
            testPrintSeqHelper(currentTest);
        }
    }
}
