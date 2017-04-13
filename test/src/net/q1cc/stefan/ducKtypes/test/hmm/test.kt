package net.q1cc.stefan.ducKtypes.test.hmm

/**
 * Created by stefan on 13.04.17.
 */

interface A
interface B

object Both : A,B

object Receiver {
    fun doSth( a: A ){}
    fun doSth( b: B ){}
}

fun main(args: Array<String>) {
    //Receiver.doSth(Both) // overload resoultion ambiguity
}
