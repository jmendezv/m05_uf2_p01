package cat.copernic.jmendezv

import java.math.BigInteger
import kotlin.math.pow



fun suma(x: Int, y: Int) = x + y
fun producto(x: Int, y: Int) = x * y
fun resta(x: Int, y: Int) = x - y
fun division(x: Int, y: Int) = x / y
fun imc(weight: Double, height: Double) = weight / height.pow(2.0)
fun ordena(nums: Array<Int>) = nums.sortedArray()
fun esPrimo(n: Long) = BigInteger.valueOf(n).isProbablePrime(2)
fun sayHi() = print("Hi!")
