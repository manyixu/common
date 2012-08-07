package org.solovyev.common.collections.tree;

import com.google.common.base.Predicate;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * User: serso
 * Date: 4/1/12
 * Time: 1:39 PM
 */
public interface MutableTree<T> extends Tree<T> {

    @NotNull
    MutableTreeNode<T> getRoot();

    void removeNodeIf(@NotNull Predicate<? super TreeNode<T>> filter);

    @NotNull
    @Override
    List<? extends MutableTreeNode<T>> getAllNodes();
}