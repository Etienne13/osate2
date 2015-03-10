/*
 * Copyright 2013-2014 Carnegie Mellon University

 * Any opinions, findings and conclusions or recommendations expressed in this 
 * Material are those of the author(s) and do not necessarily reflect the 
 * views of the United States Department of Defense. 

 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING 
 * INSTITUTE MATERIAL IS FURNISHED ON AN �AS-IS� BASIS. CARNEGIE MELLON 
 * UNIVERSITY MAKES NO WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, 
 * AS TO ANY MATTER INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR 
 * PURPOSE OR MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF 
 * THE MATERIAL. CARNEGIE MELLON UNIVERSITY DOES NOT MAKE ANY WARRANTY OF 
 * ANY KIND WITH RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT 
 * INFRINGEMENT.
 * 
 * This Material has been approved for public release and unlimited 
 * distribution except as restricted below. 
 * 
 * This Material is provided to you under the terms and conditions of the 
 * Eclipse Public License Version 1.0 ("EPL"). A copy of the EPL is 
 * provided with this Content and is also available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Carnegie Mellon� is registered in the U.S. Patent and Trademark 
 * Office by Carnegie Mellon University. 
 */

package org.osate.importer;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.PlatformUI;
import org.osate.aadl2.Element;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.util.OsateDebug;
import org.osate.importer.properties.InspectProperty;


public class Utils 
{

	public static final int INVALID_ID = -99;	
	
	public static String toAadl(String s)
	{
		String result;

		if (s.equalsIgnoreCase("set"))
		{
			return "set_t";
		}
		
		result = s.replaceAll("root", "");
		if (result.contains("::"))
		{
			result = result.substring(result.indexOf("::") + 2);
		}
		
		if (result.charAt(0) == '_')
		{
			result = "v" + result;
		}
		
		result = result.replace('\n', '_');
		result = result.replace('$', ' ');
		result = result.replace('.', ' ');
		result = result.replace("__", "_");
		result = result.replaceAll(" ", "");
		result = result.replaceAll("/", "_");
		result = result.toLowerCase();
		
		if (result.substring(result.length() - 1, result.length()).equalsIgnoreCase("_"))
		{
			result = result.substring(0, result.length() - 1);
		}
		
		/**
		 * Check for some reserved keywords in AADL.
		 */
		if (result.equalsIgnoreCase("constant"))
		{
			return "cconstant";
		}
		
		return result;
	}
	
	/**
	 * Refresh the complete Workspace. Useful when adding
	 * files in the workspace.
	 * 
	 * @param monitor The monitor used by the underlying action
	 * that performs the refresh.
	 */
	public static void refreshWorkspace (IProgressMonitor monitor)
	{
		for(IProject ip : ResourcesPlugin.getWorkspace().getRoot().getProjects())
		{
			try {
				ip.refreshLocal(IResource.DEPTH_INFINITE, monitor);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String getComponentName (ComponentInstance ci)
	{
		if (ci.getContainingComponentInstance() != null)
		{
			return getComponentName(ci.getContainingComponentInstance()).toLowerCase() + "." + ci.getName().toLowerCase();
		}
		else
		{
			return ci.getName().toLowerCase();
		}
		
	}
	
	public static boolean shallAnalyze (ComponentInstance ci)
	{
		if (ci.getContainingComponentInstance() != null)
		{
			if (InspectProperty.shallInspect(ci.getContainingComponentInstance()))
			{
				return true;
			}
			
			if (ci.getContainingComponentInstance() == ci.getSystemInstance())
			{
				return true;
			}
		}
		else
		{
			return true;
		}
		return false;
	}
	
	
	public static void listComponents (ComponentInstance si, List<ComponentInstance> componentList)
	{

		for (Element c : si.getChildren())
		{
			if (c instanceof ComponentInstance)
			{
		
				ComponentInstance ci = (ComponentInstance) c;
				
				if (InspectProperty.shallInspect(ci))
				{
					listComponents(ci, componentList);
				}
				else
				{
					componentList.add(ci);
				}
			}
		}
	}
	
	public static void listComponentsNames (ComponentInstance si, List<String> componentList)
	{

		for (Element c : si.getChildren())
		{
			if (c instanceof ComponentInstance)
			{
		
				ComponentInstance ci = (ComponentInstance) c;
				
				if (InspectProperty.shallInspect(ci))
				{
					listComponentsNames(ci, componentList);
				}
				else
				{
					String componentName = Utils.getComponentName(ci);
					System.out.println (componentName);
					componentList.add(componentName);
				}
			}
		}
	}
	
	
	/**
	 * Get the selected directory
	 * in the workspace, returns null otherwise.
	 * @return the OS-dependent path of the selected directory in the workspace
	 */
	public static String getSelectedDirectory ()
	{
		String selectedFolder;
		ISelection selection;
		IFolder selectedIFolder;;
		
		selectedFolder = null;
		
		 selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
	        if (selection != null) 
	        {
	        	try
	        	{
	            if (selection instanceof IStructuredSelection) 
	            {
	                if (selection instanceof ITreeSelection) 
	                {
	                    TreeSelection treeSelection = (TreeSelection) selection;
	                    TreePath[] treePaths = treeSelection.getPaths();
	                    TreePath treePath = treePaths[0];

	                    Object lastSegmentObj = treePath.getLastSegment();


	                    if(lastSegmentObj instanceof IFolder) 
	                    {
	                    	
	                    	selectedIFolder = (IFolder) ((IAdaptable) lastSegmentObj).getAdapter(IFolder.class);
	                        if(selectedIFolder != null) 
	                        {
	                        	selectedFolder = selectedIFolder.getRawLocation().toOSString();
	                        }
	                        
	                    }
	                }
	            }
	        	}
	        	catch (NullPointerException npe)
	        	{
	        		selectedFolder = null;
	        	}
	        }
	        return selectedFolder;
	}
}