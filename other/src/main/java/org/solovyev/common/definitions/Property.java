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

package org.solovyev.common.definitions;

import org.solovyev.common.Identifiable;

/**
 * User: serso
 * Date: 29.04.2009
 * Time: 23:48:39
 */
public class Property<VALUE, ID> implements Identifiable<ID> {

    private ID id = null;
    private VALUE value = null;

    public Property( VALUE value, ID id ) {
        this.setValue(value);
        this.setId(id);
    }

    public void setValue( VALUE value ) {
        this.value = value;
    }

    public VALUE getValue() {
        return this.value;
    }

    public void setId( ID id ) {
        this.id = id;
    }

    public ID getId() {
        return this.id;
    }

	public static <VALUE, ID> Property<VALUE, ID> create (VALUE value, ID id) {
		return new Property<VALUE, ID>(value, id);
	}
}
