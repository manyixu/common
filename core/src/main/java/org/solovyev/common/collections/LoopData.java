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

package org.solovyev.common.collections;


import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * User: serso
 * Date: Mar 29, 2010
 * Time: 10:56:24 PM
 */
/**
 * Simple flag that indicates that this element is first in iteration
 * Class must be instanciated before loop and method isFirst() or next() used only one time per loop
 */
public class LoopData {

	private Long length = null;

	public LoopData() {
	}

	public LoopData(@Nullable Collection collection) {
		if (JCollections.isEmpty(collection)) {
			this.length = (long) 0;
		} else {
			this.length = (long)collection.size();
		}
	}

	public LoopData(@Nullable Object... objects) {
		if (JCollections.isEmpty(objects)) {
			this.length = (long)0;
		} else {
			this.length = (long)objects.length;
		}
	}


	private long index = 0;

	/**
	 * Indicates if it is the first time of usage of method or not (used in loops)
	 * @return 'true' if it is the first loop step, 'false' otherwise
	 */
	public boolean isFirstAndNext() {
		boolean result = index == 0;
		next();
		return result;
	}

	/**
	 * Increases the counter
	 * @return current index in loop (before increasing counter)
	 */
	public long next() {
		long result = index;
		index++;
		return result;
	}

	/**
	 * Indicates if it is the last element in collection
	 * @return 'true' if current position is last in collection, 'false' otherwise
	 */
	public boolean isLast () {
		if ( this.length == null ) throw new IllegalArgumentException("To use isLast() method you need to initialize LoopData with collection object!");
		return this.length.equals(this.index + 1);
	}

	/**
	 * Indicates if it is the last element in collection and goes to the next element
	 * @return 'true' if current position is last in collection, 'false' otherwise
	 */
	public boolean isLastAndNext () {
		boolean result = isLast();
		next();
		return result;
	}

	/**
	 * Rewinds to start of loop
	 */
	public void rewind() {
		index = 0;
	}

	public long getIndex() {
		return index;
	}
}

