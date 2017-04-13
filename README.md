DucKtypes
===

Static duck-typing for Kotlin.<br />
Filling the gap of Kotlin ducktyping without risking runtime-errors.

How to use it
---

Write some code working on an interfaces, for example:
```
interface Duck {
    fun quack()
}
object Sea {
	fun enter( duck:Duck ) {
    	duck.quack()
    }
}
```
Create or include any classes which ducktype the interfaces.<br />
For example:
```
class ActualDuck {
    fun quack() = println( "quack" )
}
class ActualFrog {
    fun quack() = println( "quaak" )
    fun borg() = println( "borg" )
}
```
Call the code-generator like
```CodeGenerator.createExtensionMethods("outputfile.kt")```.<br />
Use ducktyped classes as if they implemented the interfaces:
```
Sea.enter( ActualDuck() )
Sea.enter( ActualFrog() )
```


How it works
---

The code-generator searches for pairs of classes and interfaces where the class implements all methods of the interface. Based on this knowledge he scans all existing classes and creates extension-methods for those methods where types can be ducked. These generated extension-methods enable ducking by anonymous wrapper-objects.

Future plan
---

 * find classes automatically
 * integrate code-generation into gradle build process
 * solve conflicting-overload situations (see known problems)

Known problems
---

* JavaDoc is not copied to generated extension-methods (yet?)
* The codegenerator may generate `conflicting overloads` which are quite logical because ducktyping is a very loose concept.<br />
  Compared to static typing, this error message corresponds to Kotlin's `overload resoultion ambiguity` like in this example:
	```
    interface A
	interface B
	object Both : A,B
	object Receiver {
	    fun doSth( a: A ){}
	    fun doSth( b: B ){}
	}
	fun callIt() {
	    Receiver.doSth(Both) // overload resoultion ambiguity: it is not clear which method is correct
	}
	```
    Suggestions for solutions are welcome.
