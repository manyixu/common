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

package org.solovyev.common;

/**
 * User: serso
 * Date: 9/19/11
 * Time: 4:32 PM
 */

import javax.annotation.Nonnull;

/**
 * Interface converts one object to another
 *
 * @param <FROM> type of object to be converted
 * @param <TO>   type of result object (converted object)
 */
public interface Converter<FROM, TO> {

    @Nonnull
    TO convert(@Nonnull FROM from);
}

