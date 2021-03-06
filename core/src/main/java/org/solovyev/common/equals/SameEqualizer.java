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
 * ---------------------------------------------------------------------
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.common.equals;


import javax.annotation.Nonnull;

/**
 * User: serso
 * Date: 12/17/11
 * Time: 11:11 PM
 */
public class SameEqualizer<T> implements Equalizer<T> {

	@Nonnull
	private static SameEqualizer<?> instance = new SameEqualizer<Object>();

	@Nonnull
	public static <T> SameEqualizer<T> getInstance() {
		return (SameEqualizer<T>) instance;
	}

	private SameEqualizer() {
	}

	@Override
	public boolean areEqual(@Nonnull Object first, @Nonnull Object second) {
		return first == second;
	}
}
