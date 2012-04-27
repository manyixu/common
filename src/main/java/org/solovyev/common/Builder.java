package org.solovyev.common;

import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 4/28/12
 * Time: 1:46 AM
 */
public interface Builder<T> {

    @NotNull
    T build();
}
