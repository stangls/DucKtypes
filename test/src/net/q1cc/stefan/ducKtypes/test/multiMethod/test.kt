package net.q1cc.stefan.ducKtypes.test.multiMethod

import enterBoth
import enters


/**
 * Created by stefan on 13.04.17.
 */

fun main(args: Array<String>) {

    val  actualFrog = ActualFrog()

    Sea.enters(ActualDuck())
    // quack
    Sea.enters(actualFrog)
    // quaaak

    Sea.enterBoth(ActualDuck(), ActualFrog())
    // quack
    // quaak
    Sea.enterBoth(ActualFrog(), ActualFrog())
    // quaak
    // quaak

    Sea.enters(object : Frog {
        override fun quack() {
            actualFrog.quack()
        }
        override fun bork() {
            actualFrog.bork()
        }
    })
    // bork

}
