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

import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 7/9/12
 * Time: 4:02 PM
 */
public abstract class SynchronizedObject<D> {

    @NotNull
    private final D delegate;

    @NotNull
    protected final Object mutex;

    protected SynchronizedObject(@NotNull D delegate) {
        this.delegate = delegate;
        this.mutex = this;
    }

    protected SynchronizedObject(@NotNull D delegate, @NotNull Object mutex) {
        this.delegate = delegate;
        this.mutex = mutex;
    }

    @NotNull
    protected D delegate() {
        return delegate;
    }

    // for manually synchronization it is allows to use mutex
    @NotNull
    public Object getMutex() {
        return mutex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SynchronizedObject)) return false;

        final SynchronizedObject that = (SynchronizedObject) o;

        synchronized (mutex) {
            if (!delegate.equals(that.delegate)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        synchronized (mutex) {
            return delegate.hashCode();
        }
    }

    @Override
    public String toString() {
        synchronized (mutex) {
            return delegate.toString();
        }
    }
}
