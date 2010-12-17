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
package fixengine.messages

abstract class EnumTag[T](value: Int) extends Tag[EnumField[Value[T]]](value, classOf[EnumField[Value[T]]]) {
  def parse(v: T) = values.find(_.v == v).getOrElse(throw new InvalidValueForTagException(v.toString))
  private def values: Array[Value[T]] = {
    getClass.getDeclaredFields.filter { field =>
      classOf[Value[T]].equals(field.getType)
    }.map{ field =>
      method(field).invoke(this).asInstanceOf[Value[T]]
    }
  }
  private def method(field: java.lang.reflect.Field) = getClass.getDeclaredMethod(field.getName)
}

case class Value[T](val v: T) extends Formattable {
  def value = v.toString
}

abstract class BooleanTag(value: Int) extends Tag[BooleanField](value, classOf[BooleanField])
abstract class FloatTag(value: Int) extends Tag[FloatField](value, classOf[FloatField])
abstract class IntegerTag(value: Int) extends Tag[IntegerField](value, classOf[IntegerField])
abstract class StringTag(value: Int) extends Tag[StringField](value, classOf[StringField])

abstract class ExchangeTag(value: Int) extends Tag[ExchangeField](value, classOf[ExchangeField])
abstract class LocalMktDateTag(value: Int) extends Tag[LocalMktDateField](value, classOf[LocalMktDateField])
abstract class MonthYearTag(value: Int) extends Tag[MonthYearField](value, classOf[MonthYearField])
abstract class UtcTimestampTag(value: Int) extends Tag[UtcTimestampField](value, classOf[UtcTimestampField])
abstract class NumInGroupTag(value: Int) extends Tag[NumInGroupField](value, classOf[NumInGroupField])
abstract class PriceTag(value: Int) extends Tag[PriceField](value, classOf[PriceField])
abstract class PriceOffsetTag(value: Int) extends Tag[PriceOffsetField](value, classOf[PriceOffsetField])

abstract class AmtTag(value: Int) extends IntegerTag(value)
abstract class QtyTag(value: Int) extends FloatTag(value)
