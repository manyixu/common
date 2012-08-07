/*
 * Copyright (c) 2009-2010. Created by serso.
 *
 * For more information, please, contact serso1988@gmail.com.
 */

package org.solovyev.common.html;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: Apr 29, 2010
 * Time: 11:13:24 PM
 */
public class Button implements Cloneable{

	@NotNull
	private final String value;

	@NotNull
	private final String action;

	public Button(@NotNull String value, @NotNull String action) {
		this.value = value;
		this.action = action;
	}

	@NotNull
	public String getValue() {
		return value;
	}

	@NotNull
	public String getAction() {
		return action;
	}

	@Override
	public Button clone() {
		Button clone = null;
		try {
			clone = (Button)super.clone();
		} catch (CloneNotSupportedException e) {
			Logger.getLogger(this.getClass()).error(e);
		}
		return clone;
	}
}