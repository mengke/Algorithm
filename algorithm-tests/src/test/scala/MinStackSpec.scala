/*
 * Copyright 2014 mengke@me.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.ArrayIndexOutOfBoundsException
import java.util.EmptyStackException
import org.specs2.mutable._

/**
 * Created by ibntab on 14-3-2.
 */
class MinStackSpec extends Specification { isolated

  "A Stack with 'min' method can either be:".p

  "1. Empty".br
  "when the stack is empty"  >> {
                                                          step { stack = emptyStack }
    "it have a size == 0" !                               ( stack.size === 0 )
    "throw an exception when sent #top" !                 ( stack.top must throwA[EmptyStackException] )
    "throw an exception when sent #pop" !                 ( stack.pop must throwA[EmptyStackException] )
  }
  p

  "2. Non-empty and not full".br
  "when the stack is not empty and not full" >> {
                                                                   step { stack = normalStack }
    "have a size > 0" !                                            ( stack.size must be_>(0) )
      "return the top item and the minimum item" !                 { stack.top === normalStack.top
                                                                     stack.min === normalStack.min }
      "push the item to the top and the minimum item is changed" ! { stack push 1; stack.top === 1; stack.min === 1
                                                                     stack push 2; stack.top === 2; stack.min === 1
                                                                     stack.pop === 2; stack.top === 1; stack.min === 1
                                                                     stack.pop === 1; stack.top === 3; stack.min === 3 }
  }
  p

  "3. Full".br
  "when the stack is full" >> {
                                                          step { stack = fullStack }
    "throw an exception when sent #push" !                { (stack push 1) must throwAn[ArrayIndexOutOfBoundsException] }
  }


  def emptyStack = new MinStack(3)
  def normalStack = {
    val stack = new MinStack(3)
    stack.push(3)
    stack
  }
  def fullStack = {
    val stack = new MinStack(3)
    stack.push(3)
    stack.push(1)
    stack.push(2)
    stack
  }

  var stack = emptyStack
}

