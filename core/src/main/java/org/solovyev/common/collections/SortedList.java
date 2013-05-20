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

import javax.annotation.Nonnull;
import java.util.*;
import java.util.Collections;

/**
 * User: serso
 * Date: 11/12/11
 * Time: 2:28 PM
 */
public class SortedList<T> implements List<T> {

	/*
	**********************************************************************
	*
	*                           FIELDS
	*
	**********************************************************************
	*/

	@Nonnull
	private List<T> list = new ArrayList<T>();

	@Nonnull
	private final Comparator<? super T> comparator;

	/*
	**********************************************************************
	*
	*                           CONSTRUCTORS
	*
	**********************************************************************
	*/

	private SortedList(@Nonnull Comparator<? super T> comparator) {
		this.comparator = comparator;
	}

	private SortedList(@Nonnull List<T> list, @Nonnull Comparator<? super T> comparator) {
		this.list = list;
		this.comparator = comparator;
	}

	@Nonnull
	public static <T> SortedList<T> newInstance(@Nonnull Comparator<? super T> comparator) {
		return new SortedList<T>(comparator);
	}

	@Nonnull
	public static <T> SortedList<T> newInstance(@Nonnull List<T> list, @Nonnull Comparator<? super T> comparator) {
		return new SortedList<T>(list, comparator);
	}

	/*
	**********************************************************************
	*
	*                           METHODS
	*
	**********************************************************************
	*/

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Nonnull
	@Override
	public Iterator<T> iterator() {
		final Iterator<T> it = list.iterator();
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public T next() {
				return it.next();
			}

			@Override
			public void remove() {
				it.remove();
				// todo serso: think
				Collections.sort(list, comparator);
			}
		};
	}

	@Nonnull
	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Nonnull
	@Override
	public <T> T[] toArray(@Nonnull T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(T t) {
		boolean result = list.add(t);
		Collections.sort(list, comparator);
		return result;
	}

	@Override
	public boolean remove(Object o) {
		boolean result = list.remove(o);
		Collections.sort(list, comparator);
		return result;
	}

	@Override
	public boolean containsAll(@Nonnull Collection<?> c) {
		return this.list.containsAll(c);
	}

	@Override
	public boolean addAll(@Nonnull Collection<? extends T> c) {
		boolean result = this.list.addAll(c);
		Collections.sort(list, comparator);
		return result;
	}

	@Override
	public boolean addAll(int index, @Nonnull Collection<? extends T> c) {
		boolean result = this.list.addAll(index, c);
		Collections.sort(list, comparator);
		return result;
	}

	@Override
	public boolean removeAll(@Nonnull Collection<?> c) {
		boolean result = this.list.removeAll(c);
		Collections.sort(list, comparator);
		return result;
	}

	@Override
	public boolean retainAll(@Nonnull Collection<?> c) {
		boolean result = this.list.retainAll(c);
		Collections.sort(list, comparator);
		return result;
	}

	@Override
	public void clear() {
		this.list.clear();
	}

	@Override
	public T get(int index) {
		return this.list.get(index);
	}

	@Override
	public T set(int index, T element) {
		T result = this.list.set(index, element);
		Collections.sort(list, comparator);
		return result;
	}

	@Override
	public void add(int index, T element) {
		this.list.add(index, element);
		Collections.sort(list, comparator);
	}

	@Override
	public T remove(int index) {
		T result = this.list.remove(index);
		Collections.sort(list, comparator);
		return result;
	}

	@Override
	public int indexOf(Object o) {
		return this.list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.list.lastIndexOf(o);
	}

	@Nonnull
	@Override
	public ListIterator<T> listIterator() {
		return listIterator(0);
	}

	@Nonnull
	@Override
	public ListIterator<T> listIterator(int index) {
		final ListIterator<T> it = this.list.listIterator(index);
		return new ListIterator<T>() {
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public T next() {
				return it.next();
			}

			@Override
			public boolean hasPrevious() {
				return it.hasPrevious();
			}

			@Override
			public T previous() {
				return it.previous();
			}

			@Override
			public int nextIndex() {
				return it.nextIndex();
			}

			@Override
			public int previousIndex() {
				return it.previousIndex();
			}

			@Override
			public void remove() {
				it.remove();
				Collections.sort(list, comparator);
			}

			@Override
			public void set(T t) {
				it.set(t);
				Collections.sort(list, comparator);
			}

			@Override
			public void add(T t) {
				it.add(t);
				Collections.sort(list, comparator);
			}
		};
	}

	@Nonnull
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return this.list.subList(fromIndex, toIndex);
	}
}
