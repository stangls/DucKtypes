package net.q1cc.stefan.ducKtypes.test.easy

import net.q1cc.stefan.ducKtypes.Duckable

/**
 * Created by stefan on 13.04.17.
 */
object Sea {

    fun enters( @Duckable d: Duck ){
        d.quack()
    }

    fun enterBoth( @Duckable d:Duck, @Duckable d2:Duck ){
        d.quack()
        d2.quack()
    }

}

