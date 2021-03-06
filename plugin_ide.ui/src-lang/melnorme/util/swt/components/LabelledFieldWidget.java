/*******************************************************************************
 * Copyright (c) 2014 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.util.swt.components;

import static melnorme.utilbox.core.Assert.AssertNamespace.assertNotNull;
import static melnorme.utilbox.core.Assert.AssertNamespace.assertTrue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import melnorme.util.swt.SWTFactory;
import melnorme.utilbox.fields.Field;
import melnorme.utilbox.fields.NonNullField;

/**
 * AbstractFieldComponent extented with a label control.
 * By default, Label is created first, to the left. 
 * But subclasses can customize this behavior, even not creating a Label at all.
 */
public abstract class LabelledFieldWidget<VALUE> extends FieldWidget<VALUE>{
	
	public static enum Option_AllowNull { YES, NO ; public boolean isTrue() { return this == YES; } };
	
	protected String labelText;
	
	protected Label label; // Optional component! May be null even after creation
	
	public LabelledFieldWidget(String labelText) {
		this(new Field<VALUE>(), labelText);
	}
	
	public LabelledFieldWidget(String labelText, Option_AllowNull allowNull, VALUE defaultFieldValue) {
		this(
			allowNull.isTrue() ? new Field<>() : new NonNullField<>(assertNotNull(defaultFieldValue)), 
			labelText
		);
	}
	
	public LabelledFieldWidget(Field<VALUE> field, String labelText) {
		super(field);
		this.labelText = labelText;
	}
	
	public String getLabelText() {
		return labelText;
	}
	
	public void setLabelText(String newLabelText) {
		assertTrue(!isCreated());
		labelText = newLabelText;
	}
	
	/* -----------------  ----------------- */
	
	@Override
	protected final void createContents(Composite topControl) {
		createContents_all(topControl);
		createContents_layout();
	}
	
	protected void createContents_all(Composite topControl) {
		createContents_Label(topControl);
		createContents_Other(topControl);
	}
	
	@SuppressWarnings("unused") 
	protected void createContents_Other(Composite topControl) {
	}
	
	protected void createContents_Label(Composite parent) {
		if(labelText != null) {
			label = SWTFactory.createLabel(parent, SWT.NONE, labelText);
		}
	}
	
	protected abstract void createContents_layout();
	
}