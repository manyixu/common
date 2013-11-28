/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org/java/jcl
 */

package org.solovyev.common.text;

import org.junit.Assert;
import org.junit.Test;
import org.solovyev.common.ByteArrayEqualizer;
import org.solovyev.common.Bytes;
import org.solovyev.common.Objects;

/**
 * User: serso
 * Date: 2/7/13
 * Time: 10:31 PM
 */
public class HexStringTest {

	@Test
	public void testGetBytes() throws Exception {
		final String s = "/]'23']324;32,4p9j;ret";
		final HexString sHex = HexString.fromString(s);

		Assert.assertTrue(Objects.areEqual(s, sHex.getOriginal(), null));
		Assert.assertTrue(Objects.areEqual(Bytes.hexToBytes(sHex), sHex.getOriginal().getBytes(), ByteArrayEqualizer.getInstance()));
	}
}
