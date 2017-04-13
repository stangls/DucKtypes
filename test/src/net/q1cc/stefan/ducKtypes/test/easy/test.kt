package net.q1cc.stefan.ducKtypes.test.easy

import enterBoth
import enters

/**
 * Created by stefan on 13.04.17.
 */

fun main(args: Array<String>) {

    Sea.enters(ActualDuck())
    Sea.enters(ActualFrog())

    Sea.enterBoth(ActualDuck(),ActualFrog())
    Sea.enterBoth(ActualFrog(),ActualFrog())

}
