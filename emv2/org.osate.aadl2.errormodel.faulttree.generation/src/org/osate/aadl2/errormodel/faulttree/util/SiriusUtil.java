package org.osate.aadl2.errormodel.faulttree.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.MoveRepresentationCommand;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.api.session.danalysis.SimpleAnalysisSelector;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.internal.command.PrepareNewAnalysisCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.actions.creation.CreateRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionLabelProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * Utilities around Sirius, sessions and representations
 *
 * @author St?phane Thibaudeau <stephane.thibaudeau@obeo.fr>
 *
 */
@SuppressWarnings("restriction")
public class SiriusUtil {
	public static SiriusUtil INSTANCE = new SiriusUtil();

	private SiriusUtil() {
		super();
	}

	/**
	 *
	 * @param session
	 * @param monitor
	 */
	public void saveSession(Session session, IProgressMonitor monitor) {
		if (SessionStatus.DIRTY.equals(session.getStatus())) {
			session.save(monitor);
		}
	}

	public Viewpoint getViewpoint(final Session session, URI viewpointURI, final IProgressMonitor monitor) {
		Viewpoint result = getViewpointFromSession(session, viewpointURI);
		if (result != null) {
			return result;
		}
		ViewpointRegistry registry = ViewpointRegistry.getInstance();
		try {
			final Viewpoint regViewpoint = registry.getViewpoint(viewpointURI);
			// make sure it is selected
			if (regViewpoint != null) {

				IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
				uiSession.open();

				// Ensure the viewpoint is selected for the session
				session.getTransactionalEditingDomain().getCommandStack()
						.execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

							@Override
							protected void doExecute() {

								// Check if already selected
								if (!session.getSelectedViewpoints(false).contains(regViewpoint)) {
									ViewpointSelectionCallback selection = new ViewpointSelectionCallback();
									selection.selectViewpoint(regViewpoint, session, monitor);
								}
							}
						});
			}
		} catch (Exception e) {
			// Unable to retrieve viewpoint
		}
		return getViewpointFromSession(session, viewpointURI);
	}

	public Viewpoint getViewpointFromSession(Session session, URI viewpointURI) {
		String viewpointName = viewpointURI.lastSegment();
		Collection<Viewpoint> viewpoints = session.getSelectedViewpoints(false);
		for (Viewpoint viewpoint : viewpoints) {
			if (viewpointName.endsWith(viewpoint.getName())) {
				return viewpoint;
			}
		}
		return null;
	}

	/**
	 * Retrieves a viewpoint from its URI
	 * @param viewpointURI
	 * @return Viewpoint from the viewpoints registry
	 */
	public Viewpoint getViewpointFromRegistry(URI viewpointURI) {
		ViewpointRegistry registry = ViewpointRegistry.getInstance();
		try {
			return registry.getViewpoint(viewpointURI);
		} catch (Exception e) {
			// Unable to retrieve viewpoint
		}
		return null;
	}

	/**
	 * Retrieves a representation description
	 * @param viewpoint
	 * @param id Id of the representation description (the id is set in the odesign model)
	 * @return
	 */
	public RepresentationDescription getRepresentationDescription(Viewpoint viewpoint, String id) {
		for (RepresentationDescription description : new ViewpointQuery(viewpoint).getAllRepresentationDescriptions()) {
			if (id.equals(description.getName())) {
				return description;
			}
		}
		return null;
	}

	public DRepresentation findRepresentation(final Session existingSession, final Viewpoint viewpoint,
			final RepresentationDescription description, final String representationName) {

		// Step 2: get the DRepresentation to open
		DAnalysis root = (DAnalysis) existingSession.getSessionResource().getContents().get(0);
		for (DView dView : root.getOwnedViews()) {
			if (dView.getViewpoint().getName().equals(viewpoint.getName())) {
				DViewQuery q = new DViewQuery(dView);
				List<DRepresentation> myRepresentations = q.getLoadedRepresentations();
				for (DRepresentation dRepresentation : myRepresentations) {
					if (representationName.equalsIgnoreCase(dRepresentation.getName())) {
						return dRepresentation;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Creates and opens a representation on the specified object
	 * @param existingSession Sirius session
	 * @param viewpoint Viewpoint containing the representation description
	 * @param description Representation description used to indicate which kind of representation to create
	 * @param representationName Name of the new representation
	 * @param object Semantic object
	 * @param monitor Progress monitor
	 */
	public void createAndOpenRepresentation(final Session existingSession, final Viewpoint viewpoint,
			final RepresentationDescription description, final String representationName, final EObject object,
			final IProgressMonitor monitor) {

		// Create and open the representation
		Display.getDefault().syncExec(() -> {
			CreateRepresentationAction action = new CreateRepresentationAction(existingSession, object, description,
					new SessionLabelProvider(ViewHelper.INSTANCE.createAdapterFactory())) {
				@Override
				protected String getRepresentationName() {
					return representationName;
				}
			};
			action.run();
		});

	}

	/**
	 * Returns a session for the project. The session is loaded and references the URI as a semantid resource
	 * @param project
	 * @param semanticResourceURI
	 * @param monitor
	 * @return loaded session or null
	 */
	public Session getSessionForProjectAndResource(IProject project, URI semanticResourceURI,
			IProgressMonitor monitor) {
		// find existing session for semantic resource URI
		Session existingSession = getSessionForSemanticURI(semanticResourceURI);
		if (existingSession != null) {
			return existingSession;
		}
		final Option<ModelingProject> prj = ModelingProject.asModelingProject(project);
		if (prj.some()) {
			// get session from modeling project
			ModelingProject modelingProject = prj.get();
			existingSession = modelingProject.getSession();
			// make sure it is loaded
			if (existingSession == null) {
				loadSession(modelingProject, monitor);
				waitWhileSessionLoads(monitor);
				existingSession = modelingProject.getSession();
			}
			if (existingSession != null) {
				// add semantic resource URI
				addSemanticResource(existingSession, semanticResourceURI, monitor);
				return existingSession;
			}
		}
		// search through all sessions in the workspace
		for (Session session : SessionManager.INSTANCE.getSessions()) {
			ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
			for (Resource res : set.getResources()) {
				if (res.getURI() != null) {
					if (set.getURIConverter().normalize(res.getURI())
							.equals(set.getURIConverter().normalize(semanticResourceURI))) {
						existingSession = session;
					}
				}
			}
		}
		if (existingSession != null) {
			addSemanticResource(existingSession, semanticResourceURI, monitor);
			return existingSession;
		}
		return existingSession;
	}

	/**
	 * Adds a resource as a semantic resource for the session
	 * @param session
	 * @param semanticResourceURI
	 * @param monitor
	 */
	private void addSemanticResource(final Session session, final URI semanticResourceURI,
			final IProgressMonitor monitor) {
		Resource resource = getResourceFromSession(session, semanticResourceURI);
		if (resource == null) {
			TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					session.addSemanticResource(semanticResourceURI, monitor);

				}
			});
		}
	}

	/**
	 * Retrieves the Sirius session referencing the URI as a semantic resource
	 *
	 * @param uri URI of the potential semantic resource
	 * @return the Sirius session or null if no corresponding session was found
	 */
	public Session getSessionForSemanticURI(URI uri) {
		for (Session session : SessionManager.INSTANCE.getSessions()) {
			ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
			// Loop on resources to check if the URI matches one of the semantic resources
			for (Resource res : set.getResources()) {
				if (res.getURI() != null) {
					if (set.getURIConverter().normalize(res.getURI()).equals(uri)) {
						return session;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Loads a Sirius session for a modeling project
	 * @param project
	 * @param monitor
	 */
	private void loadSession(ModelingProject project, IProgressMonitor monitor) {
		Option<URI> optionalMainSessionFileURI = project.getMainRepresentationsFileURI(monitor, false, false);
		if (optionalMainSessionFileURI.some()) {
			// Load the main representations file of this modeling
			// project if it's not already loaded or during loading.
			ModelingProjectManager.INSTANCE.loadAndOpenRepresentationsFile(optionalMainSessionFileURI.get());
		}
	}

	/**
	 * Waits until all sessions are loaded
	 * @param monitor
	 */
	private void waitWhileSessionLoads(IProgressMonitor monitor) {
		if (OpenRepresentationsFileJob.shouldWaitOtherJobs()) {
			// We are loading session(s), wait loading is finished
			// before continuing.
			try {
				Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, monitor);
			} catch (InterruptedException e) {
				// Do nothing
			}
		}
	}

	/**
	 * Returns a session's semantic resource from its URI
	 * @param session
	 * @param uri
	 * @return the semantic resource or null if the session references no resource with the specified URI
	 */
	public Resource getResourceFromSession(Session session, URI uri) {
		ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
		URI normalizeduri = set.getURIConverter().normalize(uri);
		for (Resource resource : session.getSemanticResources()) {
			if (resource.getURI() != null) {
				if (set.getURIConverter().normalize(resource.getURI()).equals(normalizeduri)) {
					return resource;
				}
			}
		}
		return null;
	}

	public String getPrintName(EObject obj) {
		Object res = obj.eGet(obj.eClass().getEStructuralFeature("name"));
		if (res != null) {
			return (String) res;
		}
		return obj.eResource().getURI().trimFileExtension().toString();
	}

	/**
	 * Opens a model targetroot with the specified viewpoint and representation. Creates representation if necessary
	 * 	 * @param targetroot root of EMF based model
	 * @param project
	 * @param viewpoint
	 * @param representation
	 * @param monitor
	 */
	public void autoOpenModel(final EObject targetroot, final IProject project, final String viewPoint,
			final String representation, final String jobName) {
		IProgressMonitor monitor = new NullProgressMonitor();
		URI viewpointURI = URI.createURI(viewPoint);

		URI semanticResourceURI = EcoreUtil.getURI(targetroot);
		// enable Modeling Nature
		if (!ModelingProject.hasModelingProjectNature(project)) {
			try {
				ModelingProjectManager.INSTANCE.convertToModelingProject(project, monitor);
			} catch (Exception e) {
			}
		}

		Session existingSession = getSessionForProjectAndResource(project, semanticResourceURI, monitor);
		if (existingSession != null) {
			createSubAird(existingSession, semanticResourceURI);
			EObject model = getModelFromSession(existingSession, semanticResourceURI);
			// XXX this next piece of code tries to compensate for a bug in Sirius where it cannot find the model
			// It should be there since the getSessionForProjectandResource would have put it there.
			if (model == null) {
				model = targetroot;
			}
			final Viewpoint vPoint = getViewpoint(existingSession, viewpointURI, monitor);
			final RepresentationDescription description = getRepresentationDescription(vPoint, representation);
			String modelRootName = getPrintName(model);
			String representationName = modelRootName + " " + representation;
			DRepresentation rep = findRepresentation(existingSession, vPoint, description, representationName);
			if (rep == null) {
				try {
					createAndOpenRepresentation(existingSession, vPoint, description, representationName, model,
							monitor);
					saveSession(existingSession, monitor);
					project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				} catch (Exception e) {
				}
			} else {
				try {
					DialectUIManager.INSTANCE.openEditor(existingSession, rep, monitor);
					saveSession(existingSession, monitor);
					project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				} catch (Exception e) {
				}
			}
		}
		if (ModelingProject.hasModelingProjectNature(project)) {
			try {
				ModelingProjectManager.INSTANCE.removeModelingNature(project, monitor);
			} catch (Exception e) {
			}
		}

	}

	/**
	 * Retrieves a Model instance from a semantic resource
	 * The model element must be one of the root objects in the specified semantic resource
	 * @param session
	 * @param uri
	 * @return
	 */
	private EObject getModelFromSession(Session session, URI uri) {
		Resource resource = SiriusUtil.INSTANCE.getResourceFromSession(session, uri);
		if (resource != null) {
			for (EObject object : resource.getContents()) {
				return object;
			}
		}
		return null;
	}

	private void moveRepresentations(final Session session, final DAnalysis slaveAnalysis,
			Collection<DRepresentationDescriptor> repDescriptors) {
		final IRunnableWithProgress moveReps = mon -> session.getTransactionalEditingDomain().getCommandStack()
				.execute(new MoveRepresentationCommand(session, slaveAnalysis, repDescriptors));
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().run(false, true, moveReps);
		} catch (final InterruptedException e) {
		} catch (final InvocationTargetException e) {
		}
	}

	private Resource createAIRDFile(Session session, URI uri) {
		final ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
		return set.createResource(uri);
	}

	private Resource getAIRDFile(Session session, URI uri) {
		final ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
		if (set.getURIConverter().exists(uri, null)) {
			return set.getResource(uri, false);
		}
		return null;
	}

	private boolean existsAIRDFile(Session session, URI uri) {
		final ResourceSet set = session.getTransactionalEditingDomain().getResourceSet();
		return set.getURIConverter().exists(uri, null);
	}

	private DAnalysis prepareNewAnalysis(final Session session, Resource pickedResource) {
		final DAnalysis slaveAnalysis = ViewpointFactory.eINSTANCE.createDAnalysis();
		session.getTransactionalEditingDomain().getCommandStack().execute(new PrepareNewAnalysisCommand(
				session.getTransactionalEditingDomain(), pickedResource, slaveAnalysis, session));
		return slaveAnalysis;
	}

	private void closeRepresentations(Session session, Collection<DRepresentationDescriptor> repDescriptors) {
		final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
		if (uiSession != null) {
			for (DRepresentationDescriptor repDescriptor : repDescriptors) {
				closeOpenedEditor(uiSession, repDescriptor.getRepresentation());
			}
		}
	}

	private void closeOpenedEditor(final IEditingSession uiSession, final DRepresentation representation) {
		final IEditorPart editor = uiSession.getEditor(representation);
		if (editor != null) {
			editor.getEditorSite().getPage().closeEditor(editor, false);
		}
	}

	public void createSubAird(Session session, URI targetmodelURI) {
		Collection<DRepresentationDescriptor> repDescriptors = DialectManager.INSTANCE
				.getAllRepresentationDescriptors(session);

//		closeRepresentations(session, repDescriptors);
//		URI target = targetmodelURI.trimFragment().trimFileExtension().appendFileExtension("aird");
		URI target = targetmodelURI.trimFragment().trimFileExtension().trimSegments(1).appendSegment("faulttree")
				.appendFileExtension("aird");
		Resource res = getAIRDFile(session, target);
		if (res != null) {
			return;
		}
		res = createAIRDFile(session, target);

		final DAnalysis slaveAnalysis = prepareNewAnalysis(session, res);
		DAnalysisSelector mine = new SimpleAnalysisSelector(slaveAnalysis);
		((DAnalysisSession) session).setAnalysisSelector(mine);
		moveRepresentations(session, slaveAnalysis, repDescriptors);
	}

}
