package net.q1cc.stefan.ducKtypes

import net.q1cc.stefan.missingInKotlin.allIndexed
import java.io.File

/**
 * Created by stefan on 13.04.17.
 */
object CodeGenerator {
    fun createExtensionMethods(outputFile: File) {

        val javaClasses = ClassRegistry.toSet()

        val interfaces = javaClasses.filter { it.isInterface }

        val classesDuckingInterfaces =
            javaClasses.flatMap { jClass ->
                val jClassMethods = jClass.methods.groupBy { it.name }
                val ducktypes = interfaces.filter { iface ->
                    iface != jClass &&
                    iface.methods.all { iMthd ->
                        jClassMethods[iMthd.name]?.any { jcMthd ->
                            iMthd.parameterTypes.allIndexed { idx, clazz ->
                                jcMthd.parameterTypes[idx].isAssignableFrom(clazz)
                            }
                        } ?: false
                    }

                }

                ducktypes.forEach { iface ->
                    println("$jClass can ducktype $iface")
                }

                ducktypes.map { it.to(jClass) }
            }.groupBy({ it.first },{ it.second })


        // search for duckable method-parameters

        outputFile.bufferedWriter().use { writer ->

            writer.write("\n\n// This file was dynamically created by the DucKtypes code generator\n")

            javaClasses.forEach { extendedClass ->

                extendedClass.declaredMethods.forEach { extendedMethod ->

                    val possibleDucktyings : List<List<Class<*>>> = extendedMethod.parameterTypes.mapIndexed{ paramIdx,paramType ->
                        listOf(paramType) +
                        if (extendedMethod.parameterAnnotations[paramIdx].any { it.annotationClass==Duckable::class }){
                            // this parameter should be duckable so we search for ducktyping classes
                            if (!paramType.isInterface){
                                println("WARNING: $paramType is not an interface so duckability is limited to zero :-/ (maybe will change in later versions)")
                                listOf()
                            }else{
                                classesDuckingInterfaces[paramType]?:listOf()
                            }
                        }else{
                            listOf()
                        }
                    }

                    // iterate over all possibile combinations of parameter-duckings
                    if (possibleDucktyings.isNotEmpty()) {

                        writer.write("\n// Methods for ${extendedClass.canonicalName}.${extendedMethod.name}\n\n")

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
                            writer.write(
                                "fun ${extendedClass.canonicalName}.${extendedMethod.name} ("+
                                permutation.mapIndexed { idx, duckingClass ->
                                    extendedMethod.parameters[idx].name + ":" + duckingClass.canonicalName
                                }.joinToString()+
                                ") = \n"+
                                "    ${extendedMethod.name}(\n" +
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

    fun createExtensionMethods(outputFile: String)
        = createExtensionMethods(File(outputFile))
}

fun String.indent(spaces: Int): String
    = this.split('\r','\n').map {
        if (it.isNotEmpty()){ " ".repeat(spaces)+it } else { it }
    }.joinToString("\n")

