package org.solovyev.common.model.producers;

/**
 * User: serso
 * Date: Oct 16, 2009
 * Time: 12:36:29 AM
 */
public interface SQLProducer<T> extends Producer<T> {
    public SQLQuery getQuery();
}
