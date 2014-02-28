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

import scala.annotation.tailrec

/**
 * Created by ibntab on 14-2-28.
 *
 * 把二元查找树转变成排序的双向链表
 * 题目：
 * 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只调整指针的指向。
 *        10
 *       /  \
 *      6   14
 *     / \ / \
 *    4 8 12 16
 * 转换成双向链表
 * 4=6=8=10=12=14=16。
 * 首先我们定义的二元查找树节点的数据结构如下：
 * struct BSTreeNode
 * {
 *   int m_nValue; // value of node
 *   BSTreeNode *m_pLeft; // left child of node
 *   BSTreeNode *m_pRight; // right child of node
 * };
 */
object BSTreeToLinkedList extends App {
  val treeRoot = BSTreeNode(10, BSTreeNode(6, BSTreeNode(4), BSTreeNode(8)), BSTreeNode(14, BSTreeNode(12), BSTreeNode(16)))

  def rockIt(node: BSTreeNode): (BSTreeNode, BSTreeNode) = {
    if (node == null) {
      null
    } else {
      if (node.left == null && node.right == null) {
        (node, node)
      } else {
        var (head, tail) = (node, node)
        if (node.left != null) {
          val (lHead, tail) = rockIt(node.left)
          node.left = tail
          tail.right = node
          head = lHead
        }
        if (node.right != null) {
          val (head, rTail) = rockIt(node.right)
          node.right = head
          head.left = node
          tail = rTail
        }
        (head, tail)
      }
    }
  }

  @tailrec
  def printRight(node: BSTreeNode): Unit = {
    if (node != null) {
      print(node.value + " ")
      if (node.right != null) {
        printRight(node.right)
      }
    }
  }

  @tailrec
  def printLeft(node: BSTreeNode): Unit = {
    if (node != null) {
      print(node.value + " ")
      if (node.left != null) {
        printLeft(node.left)
      }
    }
  }

  val (head, tail) = rockIt(treeRoot)
  printRight(head)
  println()
  printLeft(tail)
}

abstract class Node(value: Int)

case class BSTreeNode(value: Int, var left: BSTreeNode = null, var right: BSTreeNode = null) extends Node(value)


