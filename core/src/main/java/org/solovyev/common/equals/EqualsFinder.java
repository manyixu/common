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
import javax.annotation.Nullable;

import org.solovyev.common.Objects;
import org.solovyev.common.JPredicate;

/**
 * User: serso
 * Date: 9/17/11
 * Time: 10:16 PM
 */
public class EqualsFinder<T> implements JPredicate<T> {

    @Nonnull
    private final T modelObject;

    @Nullable
    private final Equalizer<T> equalizer;

    public EqualsFinder(@Nonnull T modelObject, @Nullable Equalizer<T> equalizer) {
        this.modelObject = modelObject;
        this.equalizer = equalizer;
    }

    @Override
    public boolean apply(@Nullable T object) {
        return Objects.areEqual(modelObject, object, equalizer);
    }
}
