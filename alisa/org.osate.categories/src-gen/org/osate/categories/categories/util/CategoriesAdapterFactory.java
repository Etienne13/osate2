/**
 * Copyright 2015 Carnegie Mellon University. All Rights Reserved.
 * 
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 * 
 * Released under the Eclipse Public License (http://www.eclipse.org/org/documents/epl-v10.php)
 * 
 * See COPYRIGHT file for full details.
 */
package org.osate.categories.categories.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.osate.categories.categories.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.osate.categories.categories.CategoriesPackage
 * @generated
 */
public class CategoriesAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static CategoriesPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CategoriesAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = CategoriesPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CategoriesSwitch<Adapter> modelSwitch =
    new CategoriesSwitch<Adapter>()
    {
      @Override
      public Adapter caseCategoriesDefinitions(CategoriesDefinitions object)
      {
        return createCategoriesDefinitionsAdapter();
      }
      @Override
      public Adapter caseCategories(Categories object)
      {
        return createCategoriesAdapter();
      }
      @Override
      public Adapter caseCategory(Category object)
      {
        return createCategoryAdapter();
      }
      @Override
      public Adapter caseCategoryFilters(CategoryFilters object)
      {
        return createCategoryFiltersAdapter();
      }
      @Override
      public Adapter caseRequirementCategories(RequirementCategories object)
      {
        return createRequirementCategoriesAdapter();
      }
      @Override
      public Adapter caseMethodCategories(MethodCategories object)
      {
        return createMethodCategoriesAdapter();
      }
      @Override
      public Adapter caseSelectionCategories(SelectionCategories object)
      {
        return createSelectionCategoriesAdapter();
      }
      @Override
      public Adapter caseQualityCategories(QualityCategories object)
      {
        return createQualityCategoriesAdapter();
      }
      @Override
      public Adapter caseComponentCategories(ComponentCategories object)
      {
        return createComponentCategoriesAdapter();
      }
      @Override
      public Adapter caseRequirementCategory(RequirementCategory object)
      {
        return createRequirementCategoryAdapter();
      }
      @Override
      public Adapter caseMethodCategory(MethodCategory object)
      {
        return createMethodCategoryAdapter();
      }
      @Override
      public Adapter caseSelectionCategory(SelectionCategory object)
      {
        return createSelectionCategoryAdapter();
      }
      @Override
      public Adapter caseQualityCategory(QualityCategory object)
      {
        return createQualityCategoryAdapter();
      }
      @Override
      public Adapter casePhaseCategory(PhaseCategory object)
      {
        return createPhaseCategoryAdapter();
      }
      @Override
      public Adapter caseComponentCategory(ComponentCategory object)
      {
        return createComponentCategoryAdapter();
      }
      @Override
      public Adapter caseCategorySet(CategorySet object)
      {
        return createCategorySetAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.CategoriesDefinitions <em>Definitions</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.CategoriesDefinitions
   * @generated
   */
  public Adapter createCategoriesDefinitionsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.Categories <em>Categories</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.Categories
   * @generated
   */
  public Adapter createCategoriesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.Category <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.Category
   * @generated
   */
  public Adapter createCategoryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.CategoryFilters <em>Category Filters</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.CategoryFilters
   * @generated
   */
  public Adapter createCategoryFiltersAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.RequirementCategories <em>Requirement Categories</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.RequirementCategories
   * @generated
   */
  public Adapter createRequirementCategoriesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.MethodCategories <em>Method Categories</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.MethodCategories
   * @generated
   */
  public Adapter createMethodCategoriesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.SelectionCategories <em>Selection Categories</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.SelectionCategories
   * @generated
   */
  public Adapter createSelectionCategoriesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.QualityCategories <em>Quality Categories</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.QualityCategories
   * @generated
   */
  public Adapter createQualityCategoriesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.ComponentCategories <em>Component Categories</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.ComponentCategories
   * @generated
   */
  public Adapter createComponentCategoriesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.RequirementCategory <em>Requirement Category</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.RequirementCategory
   * @generated
   */
  public Adapter createRequirementCategoryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.MethodCategory <em>Method Category</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.MethodCategory
   * @generated
   */
  public Adapter createMethodCategoryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.SelectionCategory <em>Selection Category</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.SelectionCategory
   * @generated
   */
  public Adapter createSelectionCategoryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.QualityCategory <em>Quality Category</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.QualityCategory
   * @generated
   */
  public Adapter createQualityCategoryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.PhaseCategory <em>Phase Category</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.PhaseCategory
   * @generated
   */
  public Adapter createPhaseCategoryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.ComponentCategory <em>Component Category</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.ComponentCategory
   * @generated
   */
  public Adapter createComponentCategoryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.osate.categories.categories.CategorySet <em>Category Set</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.osate.categories.categories.CategorySet
   * @generated
   */
  public Adapter createCategorySetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //CategoriesAdapterFactory