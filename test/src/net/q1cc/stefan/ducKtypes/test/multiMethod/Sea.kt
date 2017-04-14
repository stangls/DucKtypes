package net.q1cc.stefan.ducKtypes.test.multiMethod

import net.q1cc.stefan.ducKtypes.Duckable

/**
 * Created by stefan on 13.04.17.
 */
object Sea {

    /** makes the duck quack **/
    fun enters( @Duckable d: Duck ){
        d.quack()
    }

    /** makes both ducks quack **/
    fun enterBoth( @Duckable d: Duck, @Duckable d2: Duck ){
        d.quack()
        d2.quack()
    }

    /** possible because not marked duckable **/
    fun enters( f: Frog ){
        f.bork()
    }

}

