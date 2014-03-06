import scala.annotation.tailrec

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

/**
 * Created by ibntab on 14-3-5.
 *
 * 求子数组的最大和（数组）
 *
 * 输入一个整形数组，数组里有正数也有负数。
 * 数组中连续的一个或多个整数组成一个子数组，每个子数组都有一个和。
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)。
 * 例如输入的数组为1, -2, 3, 10, -4, 7, 2, -5，和最大的子数组为3, 10, -4, 7, 2，
 * 因此输出为该子数组的和18。
 *
 * 数组叠加的话，只要遇到负数，就会将数组的和降低，如第一个元素形成的子数组和为1，前两个元素组成
 * 的和为-1，子数组的和降低；另外再看整个数组的和为12，而除去前两个元素组成的子数组和为13
 * 这样，就有一个规律，如果数组前几位形成的子数组和为负数，会降低整个子数组的和。
 *
 */
object MaxArraySum {

  def sum(arr: Array[Int]): Int = {
    var max = Int.MinValue
    var sum = 0
    arr.map { elem =>
      sum += elem
      if (sum >= max) {
        max = sum
      } else if (sum < 0) {
        sum = 0
      }
    }
    max
  }
}
