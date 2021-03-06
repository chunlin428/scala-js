/*
 * Scala.js (https://www.scala-js.org/)
 *
 * Copyright EPFL.
 *
 * Licensed under Apache License 2.0
 * (https://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package org.scalajs.junit

import org.junit.Assume._
import org.junit._

import org.scalajs.junit.utils._

class ExceptionAfterAssume {
  @After def after(): Unit =
    throw new IllegalArgumentException("after() must be called")

  @Test def assumeFail(): Unit = {
    assumeTrue("This assume should not pass", false)
  }
}

class ExceptionAfterAssumeAssertions extends JUnitTest {
  protected def expectedOutput(builder: OutputBuilder): OutputBuilder = {
    builder.exceptionAndAnotherExceptionInAfter("assumeFail",
        "This assume should not pass",
        classOf[org.junit.internal.AssumptionViolatedException],
        "after() must be called",
        classOf[IllegalArgumentException])
  }
}
