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
 * Site:  http://se.solovyev.org
 */

package org.solovyev.common.graphs;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static java.util.Collections.unmodifiableList;

public class AdjacencyListNode<V> {

	@Nonnull
	private final List<AdjacencyListNode<V>> neighbours = new ArrayList<AdjacencyListNode<V>>();

	@Nullable
	private V value;

	List<AdjacencyListNode<V>> getNeighbours() {
		return unmodifiableList(neighbours);
	}

	@Nullable
	V getValue() {
		return value;
	}

	void setValue(@Nullable V value) {
		this.value = value;
	}

	void addNeighbour(@Nonnull AdjacencyListNode<V> neighbour) {
		neighbours.add(neighbour);
	}
}
