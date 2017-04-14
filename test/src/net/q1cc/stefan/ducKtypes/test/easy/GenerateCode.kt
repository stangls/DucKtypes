package net.q1cc.stefan.ducKtypes.test.easy

import net.q1cc.stefan.ducKtypes.*

/**
 * Created by stefan on 13.04.17.
 */

object GenerateCode {

    @JvmStatic
    fun main(args: Array<String>) {

        // package easy

        ClassRegistry.addAll(
            Sea::class.java,
            Duck::class.java,
            Frog::class.java,
            ActualDuck::class.java,
            ActualFrog::class.java
        )

        CodeGenerator.createExtensionMethods(
            "test/src/net/q1cc/stefan/ducKtypes/test/easy/ducKtype.kt"
        )

    }

}
