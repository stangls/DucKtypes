fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enters (arg0:net.q1cc.stefan.ducKtypes.test.easy.ActualFrog) = 
    enters(
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg0.quack()
            }
        }
    )
fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enters (arg0:net.q1cc.stefan.ducKtypes.test.easy.ActualDuck) = 
    enters(
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg0.quack()
            }
        }
    )
fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enterBoth (arg0:net.q1cc.stefan.ducKtypes.test.easy.ActualFrog, arg1:net.q1cc.stefan.ducKtypes.test.easy.Duck) = 
    enterBoth(
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg0.quack()
            }
        }
        ,
        arg1    )
fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enterBoth (arg0:net.q1cc.stefan.ducKtypes.test.easy.ActualDuck, arg1:net.q1cc.stefan.ducKtypes.test.easy.Duck) = 
    enterBoth(
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg0.quack()
            }
        }
        ,
        arg1    )
fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enterBoth (arg0:net.q1cc.stefan.ducKtypes.test.easy.Duck, arg1:net.q1cc.stefan.ducKtypes.test.easy.ActualFrog) = 
    enterBoth(
        arg0,
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg1.quack()
            }
        }
    )
fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enterBoth (arg0:net.q1cc.stefan.ducKtypes.test.easy.ActualFrog, arg1:net.q1cc.stefan.ducKtypes.test.easy.ActualFrog) = 
    enterBoth(
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg0.quack()
            }
        }
        ,
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg1.quack()
            }
        }
    )
fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enterBoth (arg0:net.q1cc.stefan.ducKtypes.test.easy.ActualDuck, arg1:net.q1cc.stefan.ducKtypes.test.easy.ActualFrog) = 
    enterBoth(
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg0.quack()
            }
        }
        ,
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg1.quack()
            }
        }
    )
fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enterBoth (arg0:net.q1cc.stefan.ducKtypes.test.easy.Duck, arg1:net.q1cc.stefan.ducKtypes.test.easy.ActualDuck) = 
    enterBoth(
        arg0,
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg1.quack()
            }
        }
    )
fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enterBoth (arg0:net.q1cc.stefan.ducKtypes.test.easy.ActualFrog, arg1:net.q1cc.stefan.ducKtypes.test.easy.ActualDuck) = 
    enterBoth(
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg0.quack()
            }
        }
        ,
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg1.quack()
            }
        }
    )
fun net.q1cc.stefan.ducKtypes.test.easy.Sea.enterBoth (arg0:net.q1cc.stefan.ducKtypes.test.easy.ActualDuck, arg1:net.q1cc.stefan.ducKtypes.test.easy.ActualDuck) = 
    enterBoth(
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg0.quack()
            }
        }
        ,
        object: net.q1cc.stefan.ducKtypes.test.easy.Duck {
            override fun quack(){
                arg1.quack()
            }
        }
    )
