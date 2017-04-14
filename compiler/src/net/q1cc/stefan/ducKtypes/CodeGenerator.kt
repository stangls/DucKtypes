package net.q1cc.stefan.ducKtypes

import net.q1cc.stefan.missingInKotlin.allIndexed
import java.io.File
import java.lang.reflect.Method
import java.util.*

/**
 * Created by stefan on 13.04.17.
 */
object CodeGenerator {
    fun createExtensionMethods( outputFile: File, requireAnnotations: Boolean = false ) {

        val javaClasses = ClassRegistry.toSet()

        val interfaces = javaClasses.filter { it.isInterface }

        val classesDuckingInterfaces =
            javaClasses.flatMap { jClass ->
                val jClassMethods = jClass.methods.groupBy { it.name }
                val ducktypes = interfaces.filter { iface ->
                    iface != jClass &&
                    iface.methods.all { iMthd ->
                        jClassMethods[iMthd.name]?.any { jcMthd -> jcMthd.implements(iMthd) } ?: false
                    }

                }

                ducktypes.forEach { iface ->
                    println("$jClass can ducktype $iface")
                }

                ducktypes.map { it.to(jClass) }
            }.groupBy({ it.first.canonicalName },{ it.second })


        // search for duckable method-parameters

        outputFile.bufferedWriter().use { writer ->

            writer.write("\n\n// This file was dynamically created by the DucKtypes code generator\n")

            javaClasses.forEach { extendedClass ->

                val extendedClassMethods = extendedClass.methods.groupBy { it.name }

                extendedClass.declaredMethods.forEach { extendedMethod ->

                    println(extendedMethod)

                    val possibleDucktyings : List<List<Class<*>>> = extendedMethod.parameterTypes.mapIndexed{ paramIdx,paramType ->
                        listOf(paramType) +
                        if ( !requireAnnotations or extendedMethod.parameterAnnotations[paramIdx].any{ it is Duckable } ) {
                            // this parameter should be ducktyped into so we search for ducktyping classes
                            println("paramater $paramIdx should be ducked into")
                            if (!paramType.isInterface){
                                println("WARNING: $paramType is not an interface so duckability is limited to zero :-/ (maybe will change in later versions)")
                                listOf()
                            }else{
                                classesDuckingInterfaces[paramType.canonicalName]?:listOf()
                            }
                        }else{
                            listOf()
                        }
                    }

                    // iterate over all possibile combinations of parameter-duckings
                    if (possibleDucktyings.isNotEmpty()) {

                        val extendedMethodName = extendedMethod.name
                        val extendedClassname = extendedClass.canonicalName
                        writer.write("\n// Methods for $extendedClassname.$extendedMethodName\n\n")

                        //println("$extendedClass.$extendedMethod possibly $possibleDucktyings")

                        val sizes = possibleDucktyings.map { it.size }.toIntArray()
                        val counter = possibleDucktyings.map { 0 }.toMutableList()

                        fun increaseCounter(at: Int = 0): Boolean {
                            if (at>=counter.size) return false
                            val newValue = counter[at] + 1
                            if (newValue>=sizes[at]) {
                                counter[at]=0
                                return increaseCounter(at+1)
                            }else{
                                counter[at]=newValue
                                return true
                            }
                        }

                        while (increaseCounter()) {
                            val permutation = possibleDucktyings.mapIndexed { idx, list -> list[counter[idx]] }

                            if ( extendedClassMethods[extendedMethodName]?.any{ Arrays.equals(it.parameterTypes, permutation.toTypedArray()) } ?: false ){
                                println("INFO: skipping generation of one extension-method $extendedClassname.$extendedMethodName because it exists already")
                                println("      simple signature: ${extendedClass.simpleName}.${extendedMethodName}("+permutation.map{it.simpleName}.joinToString()+")")
                                continue
                            }

                            writer.write(
                                "fun $extendedClassname.$extendedMethodName ("+
                                permutation.mapIndexed { idx, duckingClass ->
                                    extendedMethod.parameters[idx].name + ":" + duckingClass.canonicalName
                                }.joinToString()+
                                ") = \n"+
                                "    $extendedMethodName(\n" +
                                counter.mapIndexed{ idx, counter ->
                                    val param = extendedMethod.parameters[idx]
                                    val iface = param.type
                                    if (counter==0) { param.name } else {
                                        "object: ${iface.canonicalName} {\n" +
                                        iface.methods.map { mthd ->
                                            var params = (mthd.parameters?.map { "${it.name}:${it.type.canonicalName}" }?.joinToString{",\n"}?.indent(8)?:"")
                                            if (params.isNotEmpty()){
                                                params="\n$params\n            "
                                            }
                                            var arguments = mthd.parameters.map { it.name }.joinToString(",\n").indent(8)
                                            if (arguments.isNotEmpty()){
                                                arguments="\n$arguments\n            "
                                            }
                                            "override fun ${mthd.name}("+params+"){\n"+
                                            "    ${param.name}.${mthd.name}("+arguments+")\n"+
                                            "}\n"
                                        }.joinToString("\n").indent(4)+
                                        "}\n"
                                    }
                                }.joinToString(",\n").indent(8)+
                                "    )\n"
                            )

                        }

                    }
                }

            }

        }

    }

    fun createExtensionMethods(outputFile: String, requireAnnotations: Boolean = false)
        = createExtensionMethods(File(outputFile),requireAnnotations )
}

private fun Method.implements(other: Method): Boolean =
    other.parameterTypes.allIndexed { idx, clazz ->
        this.parameterTypes[idx].isAssignableFrom(clazz)
    }

fun String.indent(spaces: Int): String
    = this.split('\r','\n').map {
        if (it.isNotEmpty()){ " ".repeat(spaces)+it } else { it }
    }.joinToString("\n")

