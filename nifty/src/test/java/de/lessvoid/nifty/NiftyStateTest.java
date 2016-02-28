/*
 * Copyright (c) 2015, Nifty GUI Community
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.lessvoid.nifty;

import de.lessvoid.nifty.types.NiftyColor;
import org.junit.Test;

import static de.lessvoid.nifty.NiftyState.NiftyStandardState.*;
import static org.junit.Assert.*;

/**
 * Created by void on 17.08.15.
 */
public class NiftyStateTest {
  private final NiftyState niftyState = new NiftyState();

  @Test
  public void testDefaultState() {
    assertEquals(null, niftyState.getState(NiftyStateBackgroundFill, null));
  }

  @Test
  public void testSetNewState() {
    niftyState.setState(NiftyStateBackgroundFill, NiftyColor.black());
    assertEquals(NiftyColor.black(), niftyState.getState(NiftyStateBackgroundFill, null));
  }

  @Test
  public void testGetStateWithClassMatch() {
    niftyState.setState(NiftyStateBackgroundFill, NiftyColor.blue());
    assertEquals(NiftyColor.blue(), niftyState.getState(NiftyStateBackgroundFill, null, NiftyColor.class));
  }

  @Test
  public void testGetStateWithClassMissMatch() {
    niftyState.setState(NiftyStateBackgroundFill, new Object());
    assertNull(niftyState.getState(NiftyStateBackgroundFill, null, NiftyColor.class));
  }

  @Test
  public void testCopy() {
    niftyState.setState(NiftyStateBackgroundFill, NiftyColor.black());
    NiftyState copy = new NiftyState(niftyState);
    assertEquals(NiftyColor.black(), copy.getState(NiftyStateBackgroundFill, null));

    // changing the original state should not modify the copy
    niftyState.setState(NiftyStateBackgroundFill, NiftyColor.blue());
    assertEquals(NiftyColor.black(), copy.getState(NiftyStateBackgroundFill, null));
  }

  @Test
  public void testToString() {
    niftyState.setState(NiftyStateBackgroundFill, NiftyColor.black());
    assertEquals(
        "  NiftyStateBackgroundFill [#000000ff {0.0, 0.0, 0.0, 1.0}]\n",
        niftyState.toString());
  }
}
