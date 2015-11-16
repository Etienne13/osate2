/**
 */
package org.osate.alisa.workbench.alisa.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;

import org.osate.alisa.common.common.Description;

import org.osate.alisa.workbench.alisa.AlisaPackage;
import org.osate.alisa.workbench.alisa.AssurancePlan;

import org.osate.verify.verify.VerificationPlan;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assurance Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#getAssureOwn <em>Assure Own</em>}</li>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#getAssureGlobal <em>Assure Global</em>}</li>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#getAssureSubsystemPlans <em>Assure Subsystem Plans</em>}</li>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#getAssumeSubsystems <em>Assume Subsystems</em>}</li>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#isAssumeAll <em>Assume All</em>}</li>
 *   <li>{@link org.osate.alisa.workbench.alisa.impl.AssurancePlanImpl#getIssues <em>Issues</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssurancePlanImpl extends MinimalEObjectImpl.Container implements AssurancePlan
{
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
   * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTitle()
   * @generated
   * @ordered
   */
  protected static final String TITLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTitle()
   * @generated
   * @ordered
   */
  protected String title = TITLE_EDEFAULT;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected ComponentImplementation target;

  /**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected Description description;

  /**
   * The cached value of the '{@link #getAssureOwn() <em>Assure Own</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssureOwn()
   * @generated
   * @ordered
   */
  protected EList<VerificationPlan> assureOwn;

  /**
   * The cached value of the '{@link #getAssureGlobal() <em>Assure Global</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssureGlobal()
   * @generated
   * @ordered
   */
  protected EList<VerificationPlan> assureGlobal;

  /**
   * The cached value of the '{@link #getAssureSubsystemPlans() <em>Assure Subsystem Plans</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssureSubsystemPlans()
   * @generated
   * @ordered
   */
  protected EList<AssurancePlan> assureSubsystemPlans;

  /**
   * The cached value of the '{@link #getAssumeSubsystems() <em>Assume Subsystems</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssumeSubsystems()
   * @generated
   * @ordered
   */
  protected EList<ComponentClassifier> assumeSubsystems;

  /**
   * The default value of the '{@link #isAssumeAll() <em>Assume All</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAssumeAll()
   * @generated
   * @ordered
   */
  protected static final boolean ASSUME_ALL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isAssumeAll() <em>Assume All</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAssumeAll()
   * @generated
   * @ordered
   */
  protected boolean assumeAll = ASSUME_ALL_EDEFAULT;

  /**
   * The cached value of the '{@link #getIssues() <em>Issues</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIssues()
   * @generated
   * @ordered
   */
  protected EList<String> issues;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AssurancePlanImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return AlisaPackage.Literals.ASSURANCE_PLAN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlisaPackage.ASSURANCE_PLAN__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTitle(String newTitle)
  {
    String oldTitle = title;
    title = newTitle;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlisaPackage.ASSURANCE_PLAN__TITLE, oldTitle, title));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentImplementation getTarget()
  {
    if (target != null && ((EObject)target).eIsProxy())
    {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (ComponentImplementation)eResolveProxy(oldTarget);
      if (target != oldTarget)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlisaPackage.ASSURANCE_PLAN__TARGET, oldTarget, target));
      }
    }
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentImplementation basicGetTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(ComponentImplementation newTarget)
  {
    ComponentImplementation oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlisaPackage.ASSURANCE_PLAN__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Description getDescription()
  {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDescription(Description newDescription, NotificationChain msgs)
  {
    Description oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlisaPackage.ASSURANCE_PLAN__DESCRIPTION, oldDescription, newDescription);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescription(Description newDescription)
  {
    if (newDescription != description)
    {
      NotificationChain msgs = null;
      if (description != null)
        msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlisaPackage.ASSURANCE_PLAN__DESCRIPTION, null, msgs);
      if (newDescription != null)
        msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlisaPackage.ASSURANCE_PLAN__DESCRIPTION, null, msgs);
      msgs = basicSetDescription(newDescription, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlisaPackage.ASSURANCE_PLAN__DESCRIPTION, newDescription, newDescription));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<VerificationPlan> getAssureOwn()
  {
    if (assureOwn == null)
    {
      assureOwn = new EObjectResolvingEList<VerificationPlan>(VerificationPlan.class, this, AlisaPackage.ASSURANCE_PLAN__ASSURE_OWN);
    }
    return assureOwn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<VerificationPlan> getAssureGlobal()
  {
    if (assureGlobal == null)
    {
      assureGlobal = new EObjectResolvingEList<VerificationPlan>(VerificationPlan.class, this, AlisaPackage.ASSURANCE_PLAN__ASSURE_GLOBAL);
    }
    return assureGlobal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AssurancePlan> getAssureSubsystemPlans()
  {
    if (assureSubsystemPlans == null)
    {
      assureSubsystemPlans = new EObjectResolvingEList<AssurancePlan>(AssurancePlan.class, this, AlisaPackage.ASSURANCE_PLAN__ASSURE_SUBSYSTEM_PLANS);
    }
    return assureSubsystemPlans;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ComponentClassifier> getAssumeSubsystems()
  {
    if (assumeSubsystems == null)
    {
      assumeSubsystems = new EObjectResolvingEList<ComponentClassifier>(ComponentClassifier.class, this, AlisaPackage.ASSURANCE_PLAN__ASSUME_SUBSYSTEMS);
    }
    return assumeSubsystems;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAssumeAll()
  {
    return assumeAll;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAssumeAll(boolean newAssumeAll)
  {
    boolean oldAssumeAll = assumeAll;
    assumeAll = newAssumeAll;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlisaPackage.ASSURANCE_PLAN__ASSUME_ALL, oldAssumeAll, assumeAll));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getIssues()
  {
    if (issues == null)
    {
      issues = new EDataTypeEList<String>(String.class, this, AlisaPackage.ASSURANCE_PLAN__ISSUES);
    }
    return issues;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case AlisaPackage.ASSURANCE_PLAN__DESCRIPTION:
        return basicSetDescription(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case AlisaPackage.ASSURANCE_PLAN__NAME:
        return getName();
      case AlisaPackage.ASSURANCE_PLAN__TITLE:
        return getTitle();
      case AlisaPackage.ASSURANCE_PLAN__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case AlisaPackage.ASSURANCE_PLAN__DESCRIPTION:
        return getDescription();
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_OWN:
        return getAssureOwn();
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_GLOBAL:
        return getAssureGlobal();
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_SUBSYSTEM_PLANS:
        return getAssureSubsystemPlans();
      case AlisaPackage.ASSURANCE_PLAN__ASSUME_SUBSYSTEMS:
        return getAssumeSubsystems();
      case AlisaPackage.ASSURANCE_PLAN__ASSUME_ALL:
        return isAssumeAll();
      case AlisaPackage.ASSURANCE_PLAN__ISSUES:
        return getIssues();
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
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case AlisaPackage.ASSURANCE_PLAN__NAME:
        setName((String)newValue);
        return;
      case AlisaPackage.ASSURANCE_PLAN__TITLE:
        setTitle((String)newValue);
        return;
      case AlisaPackage.ASSURANCE_PLAN__TARGET:
        setTarget((ComponentImplementation)newValue);
        return;
      case AlisaPackage.ASSURANCE_PLAN__DESCRIPTION:
        setDescription((Description)newValue);
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_OWN:
        getAssureOwn().clear();
        getAssureOwn().addAll((Collection<? extends VerificationPlan>)newValue);
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_GLOBAL:
        getAssureGlobal().clear();
        getAssureGlobal().addAll((Collection<? extends VerificationPlan>)newValue);
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_SUBSYSTEM_PLANS:
        getAssureSubsystemPlans().clear();
        getAssureSubsystemPlans().addAll((Collection<? extends AssurancePlan>)newValue);
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSUME_SUBSYSTEMS:
        getAssumeSubsystems().clear();
        getAssumeSubsystems().addAll((Collection<? extends ComponentClassifier>)newValue);
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSUME_ALL:
        setAssumeAll((Boolean)newValue);
        return;
      case AlisaPackage.ASSURANCE_PLAN__ISSUES:
        getIssues().clear();
        getIssues().addAll((Collection<? extends String>)newValue);
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
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case AlisaPackage.ASSURANCE_PLAN__NAME:
        setName(NAME_EDEFAULT);
        return;
      case AlisaPackage.ASSURANCE_PLAN__TITLE:
        setTitle(TITLE_EDEFAULT);
        return;
      case AlisaPackage.ASSURANCE_PLAN__TARGET:
        setTarget((ComponentImplementation)null);
        return;
      case AlisaPackage.ASSURANCE_PLAN__DESCRIPTION:
        setDescription((Description)null);
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_OWN:
        getAssureOwn().clear();
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_GLOBAL:
        getAssureGlobal().clear();
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_SUBSYSTEM_PLANS:
        getAssureSubsystemPlans().clear();
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSUME_SUBSYSTEMS:
        getAssumeSubsystems().clear();
        return;
      case AlisaPackage.ASSURANCE_PLAN__ASSUME_ALL:
        setAssumeAll(ASSUME_ALL_EDEFAULT);
        return;
      case AlisaPackage.ASSURANCE_PLAN__ISSUES:
        getIssues().clear();
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
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case AlisaPackage.ASSURANCE_PLAN__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case AlisaPackage.ASSURANCE_PLAN__TITLE:
        return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      case AlisaPackage.ASSURANCE_PLAN__TARGET:
        return target != null;
      case AlisaPackage.ASSURANCE_PLAN__DESCRIPTION:
        return description != null;
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_OWN:
        return assureOwn != null && !assureOwn.isEmpty();
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_GLOBAL:
        return assureGlobal != null && !assureGlobal.isEmpty();
      case AlisaPackage.ASSURANCE_PLAN__ASSURE_SUBSYSTEM_PLANS:
        return assureSubsystemPlans != null && !assureSubsystemPlans.isEmpty();
      case AlisaPackage.ASSURANCE_PLAN__ASSUME_SUBSYSTEMS:
        return assumeSubsystems != null && !assumeSubsystems.isEmpty();
      case AlisaPackage.ASSURANCE_PLAN__ASSUME_ALL:
        return assumeAll != ASSUME_ALL_EDEFAULT;
      case AlisaPackage.ASSURANCE_PLAN__ISSUES:
        return issues != null && !issues.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", title: ");
    result.append(title);
    result.append(", assumeAll: ");
    result.append(assumeAll);
    result.append(", issues: ");
    result.append(issues);
    result.append(')');
    return result.toString();
  }

} //AssurancePlanImpl
