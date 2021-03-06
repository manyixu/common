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

package org.solovyev.common.text;

import org.solovyev.common.collections.Collections;
import org.solovyev.common.collections.LoopData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringCollections {

	protected StringCollections() {
		throw new AssertionError();
	}

	/**
	 * @param string    - string where stored list of objects separated by delimiter
	 * @param delimiter - delimiter with which string will be split
	 * @param parser    - object that will create objects of specified type
	 * @param <T>       - type of object
	 * @return list of objects, not null
	 */
	@Nonnull
	public static <T> List<T> split(@Nullable String string,
									@Nonnull String delimiter,
									@Nonnull Parser<T> parser) {
		final List<T> result = new ArrayList<T>();

		split(result, string, delimiter, parser);

		return result;
	}

	@Nonnull
	public static <C extends Collection<T>, T> C split(@Nonnull C result,
													   @Nullable String string,
													   @Nonnull String delimiter,
													   @Nonnull Parser<T> parser) {
		if (!Strings.isEmpty(string)) {
			@SuppressWarnings({"ConstantConditions"}) final String[] parts = string.split(delimiter);

			if (!Collections.isEmpty(parts)) {
				for (String part : parts) {
					result.add(parser.parseValue(part));
				}
			}
		}

		return result;
	}

	/**
	 * @param string    - string where stored list of objects separated by delimiter
	 * @param delimiter - delimiter with which string will be split
	 * @return list of objects, not null
	 */
	@Nonnull
	public static List<String> split(@Nullable String string, @Nonnull String delimiter) {
		return split(string, delimiter, StringMapper.getInstance());
	}

	@Nullable
	public static <T> String formatValue(@Nullable Collection<T> values,
										 @Nonnull String delimiter,
										 @Nonnull org.solovyev.common.text.Formatter<T> formatter) {
		String result = null;

		if (!Collections.isEmpty(values)) {
			final StringBuilder sb = new StringBuilder();

			final LoopData ld = new LoopData(values);
			for (T value : values) {
				sb.append(formatter.formatValue(value));
				if (!ld.isLastAndNext()) {
					sb.append(delimiter);
				}
			}

			result = sb.toString();
		}

		return result;
	}
}
