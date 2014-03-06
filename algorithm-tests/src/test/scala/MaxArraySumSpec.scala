import org.specs2.mutable._

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
 * Created by ibntab on 14-3-6.
 */
class MaxArraySumSpec extends Specification {

  "验证求子数组最大和的结果".p

  "对于给定的数组，最大子数组和为18".br
  eg { MaxArraySum.sum(anArray) === 18 }

  val anArray = Array[Int](1, -2, 3, 10, -4, 7, 2, -5)
}
