/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package stirling.itch.elements

import java.nio.ByteBuffer
import stirling.itch.types.FieldType

case class Field[T](name: String, fieldType: FieldType[T]) {
  def decode(buffer: ByteBuffer) = {
    fieldType.decode(buffer)
  }
  def encode(buffer: ByteBuffer, value: AnyRef) {
    fieldType.encode(buffer, value.asInstanceOf[T])
  }
  def length = fieldType.length
}
