package net.q1cc.stefan.ducKtypes

/**
 * Created by stefan on 13.04.17.
 */

object ClassRegistry : HashSet<Class<*>>() {
    fun addAll(vararg elements: Class<*>) = addAll(elements)
}