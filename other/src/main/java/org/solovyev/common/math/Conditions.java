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

package org.solovyev.common.math;

import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 3/6/11
 * Time: 3:20 PM
 */
public class Conditions {

	@NotNull
	private Function entryConditions;

	@NotNull
	private Function startCondition0;

	@NotNull
	private Function startCondition1;

	public Conditions(@NotNull Function startCondition0, @NotNull Function startCondition1, @NotNull Function entryConditions) {
		this.startCondition0 = startCondition0;
		this.startCondition1 = startCondition1;
		this.entryConditions = entryConditions;
	}

	@NotNull
	public Function getEntryCondition() {
		return this.entryConditions;
	}

	@NotNull
	public Function getStartCondition0() {
		return this.startCondition0;
	}

	@NotNull
	public Function getStartCondition1() {
		return this.startCondition1;
	}
}
