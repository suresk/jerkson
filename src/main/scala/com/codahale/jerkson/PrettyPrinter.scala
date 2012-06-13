package com.codahale.jerkson

import com.fasterxml.jackson.core.{JsonEncoding, JsonGenerator}
import java.io.{StringWriter, Writer, OutputStream, File}


trait PrettyPrinter extends Factory {
  /**
   * Generate JSON from the given object and return it as a string.
   */
  def prettyPrint[A](obj: A): String = {
    val writer = new StringWriter
    prettyPrint(obj, writer)
    writer.toString
  }

  /**
   * Generate JSON from the given object and write to the given Writer.
   */
  def prettyPrint[A](obj: A, output: Writer) {
    prettyPrint(obj, factory.createJsonGenerator(output))
  }

  /**
   * Generate JSON from the given object and write it to the given OutputStream.
   */
  def prettyPrint[A](obj: A, output: OutputStream) {
    prettyPrint(obj, factory.createJsonGenerator(output, JsonEncoding.UTF8))
  }

  /**
   * Generate JSON from the given object and write it to the given File.
   */
  def prettyPrint[A](obj: A, output: File) {
    prettyPrint(obj, factory.createJsonGenerator(output, JsonEncoding.UTF8))
  }

  private def prettyPrint[A](obj: A, generator: JsonGenerator) {
    generator.setPrettyPrinter(new com.fasterxml.jackson.core.util.DefaultPrettyPrinter)
    generator.writeObject(obj)
    generator.close()
  }
}
