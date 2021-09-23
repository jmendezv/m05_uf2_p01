package cat.copernic.jmendezv

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.time.Duration
import java.util.stream.Stream
import kotlin.test.assertFalse
import kotlin.test.assertTrue


//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
internal class MainKtTest {

    companion object {

        @BeforeAll
        @JvmStatic
        fun init() {
            println("BeforeAll()")
        }

        @AfterAll
        @JvmStatic
        fun end() {
            println("AfterAll()")
        }

        @JvmStatic
        fun provideParameters(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of("a", 1),
                Arguments.of("b", 2),
                Arguments.of("foo", 3)
            )
        }

    }


    @BeforeEach
    fun setUp() {
        println("en setUP()")
    }

    @AfterEach
    fun tearDown() {
        println("en tearDown()")
    }

    @Test
    fun suma() {

        assertAll(
            "sumes",
            { assertEquals(3, cat.copernic.jmendezv.suma(1, 2)) },
            { assertEquals(5, cat.copernic.jmendezv.suma(3, 2)) },
        )
    }

    //    @Test
    @ParameterizedTest
    @ValueSource(ints = [3, 2, 5, 7])
    fun esPrimoTest(n: Long) {
        assertTrue(esPrimo(n))
        assertFalse(esPrimo(n * 2))
    }

    @Test
    fun `testing esPrimo for timeout`() {
        val result = org.junit.jupiter.api.assertTimeout(Duration.ofMillis(150)) {
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
        assertArrayEquals(arrayOf(1, 2, 3, 4, 5), ordena(arrayOf(4, 5, 3, 1, 2)))
    }

    @Test
    fun sayHi() {

    }
}

