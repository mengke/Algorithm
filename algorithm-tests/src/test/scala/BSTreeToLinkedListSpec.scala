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

import org.specs2.mutable._
import org.specs2.specification.Scope
import scala.annotation.tailrec


/**
 * Created by ibntab on 14-3-2.
 */
class BSTreeToLinkedListSpec extends Specification {

  "The sorted binary tree" should {
    "properly transfer to a doubly linked list" in new TestTransfer {
      assertFromFirst(head)
      assertFromLast(tail)
    }
  }

  @tailrec
  private def assertFromFirst(node: BSTreeNode): Unit = {
    node.right match {
      case null => node.value must be_>=(node.left.value)
      case _    => assertFromFirst(node.right)
    }
  }

  @tailrec
  private def assertFromLast(node: BSTreeNode): Unit = {
    node.left match {
      case null => node.value must be_<=(node.right.value)
      case _    => assertFromLast(node.left)
    }
  }

  class TestTransfer extends Scope {
    val treeRoot = BSTreeNode(10, BSTreeNode(6, BSTreeNode(4), BSTreeNode(8)),
                      BSTreeNode(14, BSTreeNode(12), BSTreeNode(16)))
    val (head, tail) = BSTreeToLinkedList.rockIt(treeRoot)
  }

}
