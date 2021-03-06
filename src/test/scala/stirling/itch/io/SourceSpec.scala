/*
 * Copyright 2012 the original author or authors.
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
package stirling.itch.io

import java.io.{File, FileOutputStream, FileWriter, PrintWriter}
import java.util.zip.{ZipEntry, ZipOutputStream}
import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers
import stirling.itch.messages.MessageType.{Milliseconds, Seconds}

abstract class SourceSpec extends WordSpec with MustMatchers with SourceFixtures {
  "Source" when {
    "reading a message stream" should {
      val source = newSource(stream)
      "yield a Seconds message" in {
        source.next.messageType must equal(Seconds.toByte)
      }
      "yield a Milliseconds message" in {
        source.next.messageType must equal(Milliseconds.toByte)
      }
      "not yield more messages" in {
        source.hasNext must equal(false)
      }
    }
  }
  def newSource(stream: String): Source
}

class CompressedSourceSpec extends SourceSpec {
  def newSource(stream: String) = {
    val tempFile = File.createTempFile("SourceSpec", ".zip")
    tempFile.deleteOnExit()
    val tempStream = new FileOutputStream(tempFile)
    val zipEntry = new ZipEntry("SourceSpec.txt")
    val zipStream = new ZipOutputStream(tempStream)
    zipStream.putNextEntry(zipEntry)
    val tempWriter = new PrintWriter(zipStream)
    tempWriter.write(stream)
    tempWriter.close()
    zipStream.close()
    tempStream.close()
    Source.fromFile(tempFile)
  }
}

class UncompressedSourceSpec extends SourceSpec {
  def newSource(stream: String) = {
    val tempFile = File.createTempFile("SourceSpec", ".txt")
    tempFile.deleteOnExit()
    val tempWriter = new FileWriter(tempFile)
    tempWriter.write(stream)
    tempWriter.close()
    Source.fromFile(tempFile)
  }
}

trait SourceFixtures {
  def stream = "\r\nT    1\r\nM  1\r\n\r\n"
}
