DucKtypes
===

Static duck-typing for Kotlin.<br />
Filling the gap of Kotlin ducktyping without risking runtime-errors.<br />
Sometimes this is also known as "structural typing".

How to use it
---

Write some code using interfaces, for example:
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

Advantages
---
* Use a Go-like interface system :D
* Compared to other languages like for example Python or Go, DucKtypes still allows for normal static type-checking using interfaces. None of your code or code using a ducKtyped library automatically ducks any types into other interfaces "without permission". So Kotlin stays of course type-safe. Only by importing the extension-methods, duck-typing is enabled (use ALT+ENTER or CMD+ENTER in IntelliJ IDEA).


Future plan
---

 * find classes automatically
 * integrate code-generation into gradle build process
 * solve conflicting-overload situations (see known problems)

Known problems
---

* JavaDoc is not copied to generated extension-methods (yet?)
* The codegenerator may generate `conflicting overloads` which are quite logical because ducktyping is a very loose concept which leads to methods accepting more types and thus maybe accepting the same types.<br />
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
    The current solution is to use the annotation @Duckable for method parameters and the compiler-parameter `reguireAnnotations=true`
    Other suggestions for solutions are welcome.
