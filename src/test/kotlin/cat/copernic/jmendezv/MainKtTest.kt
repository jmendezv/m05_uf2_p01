package cat.copernic.jmendezv

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.time.Duration
import java.util.stream.Stream
import kotlin.test.assertFalse
import kotlin.test.assertTrue


//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class MainKtTest {



    companion object {

        private val outContent = ByteArrayOutputStream()
        private val originalOut = System.out

        @BeforeAll
        @JvmStatic
        fun init() {
            System.setOut(PrintStream(outContent));
        }

        @AfterAll
        @JvmStatic
        fun end() {
            System.setOut(originalOut);
        }

        @JvmStatic
        fun provideParameters(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of("a", 1),
                Arguments.of("b", 2),
                Arguments.of("foo", 3)
            )
        }

        @JvmStatic
        fun provideParametersForSuma(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(3, 2, 5),
                Arguments.of(4, 3, 7)
            )
        }
        @JvmStatic
        fun provideParametersForEsPrimo(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(arrayOf(1,2,3,4)),
                Arguments.of(arrayOf(2,4,5,7)),
                Arguments.of(arrayOf(98,12,3,4,40)),
            )
        }

    }

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun suma() {

        assertAll(
            "sumes",
            { assertEquals(3, cat.copernic.jmendezv.suma(1, 2)) },
            { assertEquals(5, cat.copernic.jmendezv.suma(3, 2)) },
        )
    }

    @ParameterizedTest
    @MethodSource("provideParametersForSuma")
    fun suma2(a: Int, b: Int, t: Int) {
        assertEquals(t, suma(a, b))
    }

    //    @Test
    @ParameterizedTest
    @ValueSource(ints = [3, 2, 5, 7], )
    fun esPrimoTest(n: Long) {
        assertTrue(esPrimo(n))
        assertFalse(esPrimo(n * 2))
    }

    @Test
    fun `testing esPrimo for timeout`() {
        val result =
            org.junit.jupiter.api.assertTimeout(
                Duration.ofMillis(150)) {
            esPrimo(3452345252452462464)
        }
//        assert(true) { result }
        assertEquals(false, result)
    }

    @ParameterizedTest
    @CsvSource("1,1,2", "3,2,5", "9,3,12")
    fun testParameters(a: Int, b: Int, c: Int) {
        assertEquals(c, cat.copernic.jmendezv.suma(a, b))
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    fun testParametersFromMethod(name: String, value: Int) {
        println("method data $name value $value")
    }


    @Test
    fun producto() {
        assertEquals(12, cat.copernic.jmendezv.producto(4, 3))
    }

    @Disabled
    @Test
    @DisplayName("Una simple resta")
    fun resta() {
        assertEquals(9, cat.copernic.jmendezv.resta(11, 2))
    }

    @Test
    fun division() {
        assertEquals(2, cat.copernic.jmendezv.division(6, 3))
        assertThrows<ArithmeticException> {
            assertEquals(2, cat.copernic.jmendezv.division(6, 0))
            assertEquals(2, cat.copernic.jmendezv.division(9, 0))
        }
    }

    @Test
    fun imc() {
        org.junit.jupiter.api.assertTimeout(Duration.ofMillis(1000)) {
            assertEquals(24.835, imc(85.0, 1.85), 1e-3)
        }

    }

    @Test
    fun ordena() {
        assertArrayEquals(arrayOf(1, 2, 3, 4, 5),
            ordena(arrayOf(4, 5, 3, 1, 2)))
    }

    @Test
    fun sayHiTest() {

        val output = tapSystemOut {
            sayHi()
        }

        assertEquals(
            "Hi!",
            output.trim()
        )
    }

    @Test
    fun sayHiTestOldSchool() {
        sayHi()
        assertEquals("Hi!", outContent.toString());
    }
}

