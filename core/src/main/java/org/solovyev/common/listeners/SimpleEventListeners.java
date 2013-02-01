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
 * Site:  http://se.solovyev.org/java/jcl
 */

package org.solovyev.common.listeners;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * User: serso
 * Date: 2/1/13
 * Time: 9:21 PM
 */
public class SimpleEventListeners<L extends JEventListener<?>, E extends JEvent> implements EventListeners<L, E> {

    @NotNull
    private final JListeners<L> listeners;

    private SimpleEventListeners(@NotNull JListeners<L> listeners) {
        this.listeners = listeners;
    }

    @NotNull
    public static <L extends JEventListener<?>, E extends JEvent> SimpleEventListeners<L, E> newInstance(@NotNull JListeners<L> listeners) {
        return new SimpleEventListeners<L, E>(listeners);
    }

    @Override
    public void fireEvent(@NotNull E event) {
        for (L listener : getListeners()) {
            if  (listener.getEventType().isAssignableFrom(event.getClass())) {
                ((JEventListener<E>) listener).onEvent(event);
            }
        }
    }

    @Override
    public boolean addListener(@NotNull L listener) {
        return listeners.addListener(listener);
    }

    @Override
    public boolean removeListener(@NotNull L listener) {
        return listeners.removeListener(listener);
    }

    @NotNull
    @Override
    public Collection<L> getListeners() {
        return listeners.getListeners();
    }

    @NotNull
    @Override
    public <LE extends L> Collection<LE> getListenersOfType(@NotNull Class<LE> type) {
        return listeners.getListenersOfType(type);
    }
}
