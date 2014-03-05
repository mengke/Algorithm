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

import java.util.EmptyStackException

/**
 * Created by ibntab on 14-2-28.
 *
 * 设计包含min函数的栈（栈）
 * 定义栈的数据结构，要求添加一个min函数，能够得到栈的最小元素。
 * 要求函数min、push以及pop的时间复杂度都是O(1)。
 *
 * push与pop的时间复杂度很容易做到O(1)，但常规情况下min操作可能需要O(n)，
 * 另外考虑到每次进栈操作将栈的状态由M(n－1)变为M(n)，而出栈操作又使其由
 * M(n)变回为M(n-1), 那么如果M(n)表示的是n个元素时堆栈的最小值呢？所以我们
 * 需要将这个状态保存到每一个元素中。(入栈的不只是一个元素，而且还是一个状态)
 */
private class MinStackElement(val data: Int, val minIndex: Int)

class MinStack(val maxCapacity: Int) {
  private val stack = new Array[MinStackElement](maxCapacity)
  private var offset = 0

  def push(data: Int) = {
    if (isFull) {
      throw new ArrayIndexOutOfBoundsException("The stack is full")
    }
    val minIndex = offset match {
      case 0       => 0
      case _ : Int =>
        val preMinValue = stack(offset - 1).data
        if (data < preMinValue) {
          offset
        } else {
          offset - 1
        }
    }
    stack(offset) = new MinStackElement(data, minIndex)
    offset += 1
  }

  def pop(): Int = {
    if (isEmpty) {
      throw new EmptyStackException
    }
    offset -= 1
    stack(offset).data
  }

  def min(): Int = {
    if (isEmpty) {
      throw new EmptyStackException
    }
    stack(stack(offset - 1).minIndex).data
  }

  def isEmpty: Boolean = {
    offset == 0
  }

  def isFull: Boolean = {
    offset == maxCapacity
  }

  def size() = offset

  // used for testing
  def top(): Int = {
    if (isEmpty) {
      throw new EmptyStackException
    }
    stack(offset - 1).data
  }

}
