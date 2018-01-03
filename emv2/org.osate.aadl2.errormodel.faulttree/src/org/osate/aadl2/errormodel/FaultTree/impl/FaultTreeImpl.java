/**
 */
package org.osate.aadl2.errormodel.FaultTree.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osate.aadl2.errormodel.FaultTree.Event;
import org.osate.aadl2.errormodel.FaultTree.FaultTree;
import org.osate.aadl2.errormodel.FaultTree.FaultTreePackage;
import org.osate.aadl2.errormodel.FaultTree.FaultTreeType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fault Tree</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.aadl2.errormodel.FaultTree.impl.FaultTreeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.osate.aadl2.errormodel.FaultTree.impl.FaultTreeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.osate.aadl2.errormodel.FaultTree.impl.FaultTreeImpl#getFaultTreeType <em>Fault Tree Type</em>}</li>
 *   <li>{@link org.osate.aadl2.errormodel.FaultTree.impl.FaultTreeImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link org.osate.aadl2.errormodel.FaultTree.impl.FaultTreeImpl#getInstanceRoot <em>Instance Root</em>}</li>
 *   <li>{@link org.osate.aadl2.errormodel.FaultTree.impl.FaultTreeImpl#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FaultTreeImpl extends MinimalEObjectImpl.Container implements FaultTree {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getFaultTreeType() <em>Fault Tree Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFaultTreeType()
	 * @generated
	 * @ordered
	 */
	protected static final FaultTreeType FAULT_TREE_TYPE_EDEFAULT = FaultTreeType.FAULT_TREE;

	/**
	 * The cached value of the '{@link #getFaultTreeType() <em>Fault Tree Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFaultTreeType()
	 * @generated
	 * @ordered
	 */
	protected FaultTreeType faultTreeType = FAULT_TREE_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRoot() <em>Root</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoot()
	 * @generated
	 * @ordered
	 */
	protected Event root;

	/**
	 * The cached value of the '{@link #getInstanceRoot() <em>Instance Root</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceRoot()
	 * @generated
	 * @ordered
	 */
	protected EObject instanceRoot;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> events;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FaultTreeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FaultTreePackage.Literals.FAULT_TREE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaultTreePackage.FAULT_TREE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaultTreePackage.FAULT_TREE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FaultTreeType getFaultTreeType() {
		return faultTreeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFaultTreeType(FaultTreeType newFaultTreeType) {
		FaultTreeType oldFaultTreeType = faultTreeType;
		faultTreeType = newFaultTreeType == null ? FAULT_TREE_TYPE_EDEFAULT : newFaultTreeType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaultTreePackage.FAULT_TREE__FAULT_TREE_TYPE, oldFaultTreeType, faultTreeType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getEvents() {
		if (events == null) {
			events = new EObjectContainmentEList<Event>(Event.class, this, FaultTreePackage.FAULT_TREE__EVENTS);
		}
		return events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event getRoot() {
		if (root != null && root.eIsProxy()) {
			InternalEObject oldRoot = (InternalEObject)root;
			root = (Event)eResolveProxy(oldRoot);
			if (root != oldRoot) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaultTreePackage.FAULT_TREE__ROOT, oldRoot, root));
			}
		}
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event basicGetRoot() {
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(Event newRoot) {
		Event oldRoot = root;
		root = newRoot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaultTreePackage.FAULT_TREE__ROOT, oldRoot, root));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getInstanceRoot() {
		if (instanceRoot != null && instanceRoot.eIsProxy()) {
			InternalEObject oldInstanceRoot = (InternalEObject)instanceRoot;
			instanceRoot = eResolveProxy(oldInstanceRoot);
			if (instanceRoot != oldInstanceRoot) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaultTreePackage.FAULT_TREE__INSTANCE_ROOT, oldInstanceRoot, instanceRoot));
			}
		}
		return instanceRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetInstanceRoot() {
		return instanceRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanceRoot(EObject newInstanceRoot) {
		EObject oldInstanceRoot = instanceRoot;
		instanceRoot = newInstanceRoot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaultTreePackage.FAULT_TREE__INSTANCE_ROOT, oldInstanceRoot, instanceRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FaultTreePackage.FAULT_TREE__EVENTS:
				return ((InternalEList<?>)getEvents()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FaultTreePackage.FAULT_TREE__NAME:
				return getName();
			case FaultTreePackage.FAULT_TREE__DESCRIPTION:
				return getDescription();
			case FaultTreePackage.FAULT_TREE__FAULT_TREE_TYPE:
				return getFaultTreeType();
			case FaultTreePackage.FAULT_TREE__ROOT:
				if (resolve) return getRoot();
				return basicGetRoot();
			case FaultTreePackage.FAULT_TREE__INSTANCE_ROOT:
				if (resolve) return getInstanceRoot();
				return basicGetInstanceRoot();
			case FaultTreePackage.FAULT_TREE__EVENTS:
				return getEvents();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FaultTreePackage.FAULT_TREE__NAME:
				setName((String)newValue);
				return;
			case FaultTreePackage.FAULT_TREE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case FaultTreePackage.FAULT_TREE__FAULT_TREE_TYPE:
				setFaultTreeType((FaultTreeType)newValue);
				return;
			case FaultTreePackage.FAULT_TREE__ROOT:
				setRoot((Event)newValue);
				return;
			case FaultTreePackage.FAULT_TREE__INSTANCE_ROOT:
				setInstanceRoot((EObject)newValue);
				return;
			case FaultTreePackage.FAULT_TREE__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection<? extends Event>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FaultTreePackage.FAULT_TREE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FaultTreePackage.FAULT_TREE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case FaultTreePackage.FAULT_TREE__FAULT_TREE_TYPE:
				setFaultTreeType(FAULT_TREE_TYPE_EDEFAULT);
				return;
			case FaultTreePackage.FAULT_TREE__ROOT:
				setRoot((Event)null);
				return;
			case FaultTreePackage.FAULT_TREE__INSTANCE_ROOT:
				setInstanceRoot((EObject)null);
				return;
			case FaultTreePackage.FAULT_TREE__EVENTS:
				getEvents().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FaultTreePackage.FAULT_TREE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FaultTreePackage.FAULT_TREE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case FaultTreePackage.FAULT_TREE__FAULT_TREE_TYPE:
				return faultTreeType != FAULT_TREE_TYPE_EDEFAULT;
			case FaultTreePackage.FAULT_TREE__ROOT:
				return root != null;
			case FaultTreePackage.FAULT_TREE__INSTANCE_ROOT:
				return instanceRoot != null;
			case FaultTreePackage.FAULT_TREE__EVENTS:
				return events != null && !events.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", faultTreeType: ");
		result.append(faultTreeType);
		result.append(')');
		return result.toString();
	}

} //FaultTreeImpl
