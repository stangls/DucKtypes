package net.q1cc.stefan.missingInKotlin

fun <T> Array<T>.allIndexed(func: (idx:Int,it:T) -> Boolean): Boolean = mapIndexed(func).all { it }
fun <T> Collection<T>.allIndexed(func: (idx:Int,it:T) -> Boolean): Boolean = mapIndexed(func).all { it }