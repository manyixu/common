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

package org.solovyev.common.collections.multiset;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class MultiSets {

	private MultiSets() {
		throw new AssertionError();
	}

	static void checkAdd(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("Number of elements to add must not be negative!");
		}
	}

	static void checkRemove(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("Number of elements to remove must be not negative!");
		}
	}

	static void checkSetCount(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("Number of elements must be not negative!");
		}
	}

	static final Iterator<Object> EMPTY_MODIFIABLE_ITERATOR =
			new Iterator<Object>() {
				@Override
				public boolean hasNext() {
					return false;
				}

				@Override
				public Object next() {
					throw new NoSuchElementException();
				}

				@Override
				public void remove() {
					throw new IllegalStateException();
				}
			};

	@Nonnull
	public static <E> OneInstanceMultiSet<E> newOneInstanceMultiSet() {
		return HashMapOneInstanceMultiSet.newInstance();
	}

	@Nonnull
	public static <E> ManyInstancesMultiSet<E> newManyInstancesMultiSet() {
		return HashMapManyInstancesMultiSet.newInstance();
	}
}
