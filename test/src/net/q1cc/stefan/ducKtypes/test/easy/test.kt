package net.q1cc.stefan.ducKtypes.test.easy

import enterBoth
import enters

/**
 * Created by stefan on 13.04.17.
 */

fun main(args: Array<String>) {

    Sea.enters(ActualDuck())
    // quack
    Sea.enters(ActualFrog())
    // quaaak

    Sea.enterBoth(ActualDuck(),ActualFrog())
    // quack
    // quaak
    Sea.enterBoth(ActualFrog(),ActualFrog())
    // quaak
    // quaak

}
