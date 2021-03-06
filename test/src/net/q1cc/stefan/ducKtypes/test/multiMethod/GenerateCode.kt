package net.q1cc.stefan.ducKtypes.test.multiMethod

import net.q1cc.stefan.ducKtypes.*
import java.io.File

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
            "test/src/net/q1cc/stefan/ducKtypes/test/multiMethod/ducKtypeMM.kt",
            requireAnnotations = true
        )

    }

}
