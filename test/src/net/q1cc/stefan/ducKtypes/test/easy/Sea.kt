package net.q1cc.stefan.ducKtypes.test.easy

import net.q1cc.stefan.ducKtypes.Duckable

/**
 * Created by stefan on 13.04.17.
 */
object Sea {

    /** makes the duck quack **/
    fun enters( d: Duck ){
        d.quack()
    }

    /** makes both ducks quack **/
    fun enterBoth( d:Duck, d2:Duck ){
        d.quack()
        d2.quack()
    }

    /*
    // disallowed currently
    fun enters( f: Frog ){
        f.bork()
    }
    */

}

