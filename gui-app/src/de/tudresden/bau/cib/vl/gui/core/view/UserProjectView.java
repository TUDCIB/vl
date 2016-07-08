package de.tudresden.bau.cib.vl.gui.core.view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.User;
import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.service.BimFitService;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.core.util.VirtualFile;
import de.tudresden.bau.cib.vl.gui.Activator;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;
import de.tudresden.bau.cib.vl.gui.core.controller.SessionManagementController;
import de.tudresden.bau.cib.vl.gui.core.controller.UserProjectController;
import de.tudresden.bau.cib.vl.gui.core.messages.Messages;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNodeDirectory;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNodeFile;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNodeProject;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNodeSimulationProject;
import de.tudresden.bau.cib.vl.gui.core.view.dialog.NewProjectDialog;



/**
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 * @author <a href="mailto:Mario.Guertler@tu-dresden.de">Mario Guertler</a>
 *
 */
public class UserProjectView extends AbstractView<UserProjectController> {

	public static final String ID = "de.tudresden.bau.cib.vl.gui.core.view.UserProjectView";
	
	protected TreeViewer viewer;
	private IToolBarManager toolBarManager;
	
	private Action selectFileAction;	
	private Action newProjectAction;
	private Action deleteProjectAction;
	private Action deleteSimulationProjectAction;
	private Action uploadAction;
	private Action deleteFileAction;
	private Action downloadAction;
	private Action checkSimulationsAction;
	//private Action doubleClickAction;
	private Display display;
	private Browser downloadBrowser;
//	private UrlLauncher launcher = new UrlLauncherImpl();
	
	
	private VirtualFile selectedFile;
	
	private BimFitService modelService;
	private FileService fileService;
	private OntologyService ontologyService;
	private UserService userService;
	private ConfigurationService configurationService;
	
	private UserProjectController controller;
	
	private Composite parentComposite;
	
	
	@Override
	protected UserProjectController createController() {
		controller = new UserProjectController();
		controller.setFileService(fileService);
		controller.setBimFitService(modelService);
		controller.setOntologyService(ontologyService);
		controller.setUserService(userService);
		controller.setConfigurationService(configurationService);
		return controller;
	}
	

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		this.parentComposite = parent;
		this.display = parent.getDisplay();			
		
		parentComposite.setLayout(new GridLayout(1, false));
		
			
		viewer = new TreeViewer(parentComposite, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL );
		//drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());	
//		viewer.setAutoExpandLevel(2);
		
	
		
		toolBarManager = getViewSite().getActionBars().getToolBarManager();	
		
		
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener(){

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;

					
					controller.clearSelectedElements();
					
					Object firstSelection = structuredSelection.getFirstElement();
					
					
					controller.setSelectedNode((TreeNode)firstSelection);
					
					
					
					if(firstSelection instanceof TreeNodeFile)
					{
						TreeNodeFile to = (TreeNodeFile) firstSelection;
						FileInformation file = to.getFile();
						if(file != null)
						{
								TreeNode parent = to.getParent();
								if(parent instanceof TreeNodeProject)
								{
									controller.setSelectedProject(((TreeNodeProject) parent).getProject());
								}
							
								controller.setSelectedFile(file);
								
							
						}
					}
					
					if(firstSelection instanceof TreeNodeProject)
					{
						TreeNodeProject to = (TreeNodeProject) firstSelection;
						Project proj = to.getProject();
						if(proj != null) {
							controller.setSelectedProject(proj);
						}
					}
					
					if(firstSelection instanceof TreeNodeSimulationProject)
					{
						TreeNodeSimulationProject to = (TreeNodeSimulationProject) firstSelection;
						SimulationProject proj = to.getSimulationProject();
						if(proj != null)
						{
							
							TreeNode parent = to.getParent();
							if(parent instanceof TreeNodeProject)
							{
								controller.setSelectedProject(((TreeNodeProject) parent).getProject());
							}
							
							
							controller.setSelectedSimulationProj(proj);
						}
					}
					
				}
			}		
		});
		
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {				
				
				ISelection selection = event.getSelection();
				
				if(selection instanceof IStructuredSelection)
				{
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
					Object firstSelection = structuredSelection.getFirstElement();
					if(firstSelection instanceof TreeNodeProject)
					{
						controller.publishSelectedProject();
					}
					
					if(firstSelection instanceof TreeNodeSimulationProject)
					{
						
//						controller.publishSelectedProject();
						controller.publishSelectedSimulationProject();
					}
					
					if(firstSelection instanceof TreeNodeFile)
					{
						
//						controller.publishSelectedProject();
						try {
							controller.publishSelectedFile();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParsingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
					
				}				
				
			}
		});
		
		User currentUser = (User)SessionManagementController.getInstance().getUser();
		if(currentUser != null) {
			controller.setCurrentUser(currentUser);
			UserRepository model = userService.getUserRepositoryByUserId(currentUser.getId());
			controller.setModel(model);
			setInput(model);
		}
		
//		UserRepository repo = controller.getModel();
//		if(repo != null) setInput(repo);

		createActions();
		hookContextMenu();	
		createToolBar();
	}

	public void setInput(final UserRepository repo) {
		if(!viewer.getTree().isDisposed()) {
			display.asyncExec(new Runnable() {
	
				@Override
				public void run() {
					viewer.setInput(repo);
				}			
			});	
		}
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager manager) {
				
				 final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				
				 manager.add(newProjectAction);
				 
				 
				 if(!selection.isEmpty())
				 {
					 if(selection.size() == 1)
					 {
						 if(selection.getFirstElement() instanceof TreeNodeProject)
						 {
							 manager.add(deleteProjectAction);
							 manager.add(uploadAction);
							 manager.add(downloadAction);
						 }
						 if(selection.getFirstElement() instanceof TreeNodeFile)
						 {
							 //manager.add(selectFileAction);
							 manager.add(deleteFileAction);
							 manager.add(downloadAction);
						 }
						 if(selection.getFirstElement() instanceof TreeNodeSimulationProject) {
							 TreeNodeSimulationProject to = (TreeNodeSimulationProject) selection.getFirstElement();
							 SimulationProject sproj = to.getSimulationProject();
							 controller.setSelectedSimulationProj(sproj);
							 manager.add(deleteSimulationProjectAction);
							 manager.add(downloadAction);
						 }
					 }
				 }			
				
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	
	
	private void createToolBar()
	{
		toolBarManager.add(newProjectAction);
		toolBarManager.add(checkSimulationsAction);
	}	


	protected void createActions() {
		selectFileAction = new Action() {

			public void run() {
				try {
					controller.publishSelectedFile();
				} catch (ParsingException e) {
					e.printStackTrace();
//					showMessage(selectedFilePath+" " + LocalizationFactory.getInstance().getText("MSG_FILE_SELECTED"));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		selectFileAction.setText(Messages.ACTION_SELECT_FILE);
		selectFileAction.setToolTipText(Messages.ACTION_SELECT_FILE);
		ImageDescriptor imageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_22x22_PATH+"button_accept.png");
		selectFileAction.setImageDescriptor(imageDescriptor);
		
		uploadAction = new Action() {

			public void run() {
				Shell shell = new Shell(Display.getCurrent());
				shell.setLocation(0, 0);
				shell.setSize(640, 480);
				FileDialog fileDialog = new FileDialog(shell, SWT.TITLE | SWT.MULTI);
				fileDialog.setText( "Upload Files" );
				String filePath = fileDialog.open();
				controller.uploadFile(filePath);
				
			
				

			}
		};
		uploadAction.setText(Messages.ACTION_UPLOAD_FILE);
		uploadAction.setToolTipText(Messages.ACTION_UPLOAD_FILE);
		ImageDescriptor uploadImageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"filenew.png");
		uploadAction.setImageDescriptor(uploadImageDescriptor);		
		
		
		newProjectAction = new Action(){

			public void run() {
				
				NewProjectDialog dialog = new NewProjectDialog(Display.getCurrent().getActiveShell());
				if( dialog.open() == Dialog.OK)
				{
					controller.createProject(dialog.getStrProjectName());					
					viewer.setInput(controller.getModel());
					
				}			
				
			}
		};
		
		newProjectAction.setText(Messages.NEW_PROJECT);
		newProjectAction.setToolTipText(Messages.NEW_PROJECT);
		ImageDescriptor newProjectImageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"folder_new.png");
		newProjectAction.setImageDescriptor(newProjectImageDescriptor);		
		
		deleteProjectAction = new Action(){

			public void run()
			{		
				
				controller.deleteSelectedProject();					
				viewer.setInput(controller.getModel());						
				
			}
		};
		
		deleteProjectAction.setText(Messages.DELETE_PROJECT);
		deleteProjectAction.setToolTipText(Messages.DELETE_PROJECT);
		ImageDescriptor deleteProjectImageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"cancel.png");
		deleteProjectAction.setImageDescriptor(deleteProjectImageDescriptor);
		
		deleteSimulationProjectAction = new Action(){

			public void run()
			{		
				
				controller.deleteSelectedSimulationProject();					
				viewer.setInput(controller.getModel());						
				
			}
		};
		
		deleteSimulationProjectAction.setText(Messages.DELETE_SIMULATION_PROJECT);
		deleteSimulationProjectAction.setToolTipText(Messages.DELETE_SIMULATION_PROJECT);
		ImageDescriptor deleteSimulationProjectImageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"cancel.png");
		deleteSimulationProjectAction.setImageDescriptor(deleteSimulationProjectImageDescriptor);
		
		
		
		deleteFileAction = new Action(){

			
			/**
			 * 
			 */
			private static final long serialVersionUID = -2901648947659800036L;

			public void run()
			{		
				controller.deleteSelectedFile();					
				viewer.setInput(controller.getModel());						
				
			}
		};
		
		deleteFileAction.setText(Messages.DELETE_FILE);
		deleteFileAction.setToolTipText(Messages.DELETE_FILE);
		ImageDescriptor deleteFileImageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"cancel.png");
		deleteFileAction.setImageDescriptor(deleteFileImageDescriptor); 
		
		
		
		
		
		downloadAction = new Action() {
			

			public void run() {				
				
				
				if(downloadBrowser != null)
					downloadBrowser.dispose();
				
				downloadBrowser = new Browser(parentComposite, SWT.NONE);
				downloadBrowser.setSize(0,0);
				downloadBrowser.setBounds(0,0,0,0);
				downloadBrowser.setVisible(false);			
				downloadBrowser.setUrl(controller.getDownloadURL());					
			
			
				
//				launcher.openURL(controller.getDownloadURL());
				

			}
		};
		downloadAction.setText(Messages.DOWNLOAD);
		downloadAction.setToolTipText(Messages.DOWNLOAD);
		ImageDescriptor downloadImageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"filenew.png");
		downloadAction.setImageDescriptor(downloadImageDescriptor);	
		
		checkSimulationsAction = new Action() {

			@Override
			public void run() {	
				controller.publishCheckOfSimulations();
			}
		};
		checkSimulationsAction.setText(Messages.UPDATE_SIMULATION_STATUS);
		checkSimulationsAction.setToolTipText(Messages.UPDATE_SIMULATION_STATUS);
		ImageDescriptor checkSimulationsImageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"reload.png");
		checkSimulationsAction.setImageDescriptor(checkSimulationsImageDescriptor);	
	}
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
			this.getViewSite().getShell(),
			Messages.VIEW_DATAMANAGEMENT,
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	public void update()
	{
			display.asyncExec(new Runnable() {
			
			@Override
			public void run() {
				viewer.setInput(controller.getModel());
			}
		});
	}
	
	

	protected class ViewContentProvider implements IStructuredContentProvider, 
										   ITreeContentProvider {
		
		private TreeNode invisibleRoot;
	

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			
		}
		public void dispose() {
			
		}
		public Object[] getElements(Object parent) {
			
			if(parent instanceof UserRepository)
			{
				
				UserRepository userRep = (UserRepository) parent;			
				invisibleRoot = new TreeNode("");
				
				Set<Project> projects = userRep.getProjects();
				for(Project proj : projects)
				{
					TreeNodeProject treeProj = new TreeNodeProject(proj.getName());
					treeProj.setProject(proj);
					invisibleRoot.addChild(treeProj);
					
					
					Set<FileInformation> files = proj.getFiles();
					for(FileInformation file : files)
					{
						TreeNodeFile treeFile = new TreeNodeFile(file.getName());
						treeFile.setFile(file);
						treeProj.addChild(treeFile);		
						
					}	
					
					Set<SimulationProject> simProjects = proj.getSimProjects();
					for(SimulationProject simProject : simProjects)
					{
						TreeNodeSimulationProject treeSimProj = new TreeNodeSimulationProject(simProject.getName());
						treeSimProj.setSimulationProject(simProject); 
						
						TreeNodeDirectory treeDirInput = new TreeNodeDirectory("INPUT");
						treeDirInput.setPathDirectory(simProject.getPathInputDirectory());
						treeSimProj.addChild(treeDirInput);
						
						for(FileInformation inputFile : simProject.getInputFiles())
						{
							
							TreeNodeFile treeFile = new TreeNodeFile(inputFile.getName());
							treeFile.setFile(inputFile);
							treeDirInput.addChild(treeFile);
							
						}
						
						
						TreeNodeDirectory treeDirOutput = new TreeNodeDirectory("OUTPUT");
						treeDirOutput.setPathDirectory(simProject.getPathOutputDirectory());
						treeSimProj.addChild(treeDirOutput);
						
						for(FileInformation outputFile : simProject.getOutputFiles())
						{
							
							TreeNodeFile treeFile = new TreeNodeFile(outputFile.getName());
							treeFile.setFile(outputFile);
							treeDirOutput.addChild(treeFile);
							
						}
						
						
						treeProj.addChild(treeSimProj);		
						
					}					
					
				}		
				
			}
			else
			{
				
				invisibleRoot =  new TreeNode("");
				
				TreeNodeFile root = new TreeNodeFile("No User repository loaded");
				invisibleRoot.addChild(root);
			}		
				
				
			return getChildren(invisibleRoot);
		}
		public Object getParent(Object child) {
			if (child instanceof TreeNode) {
				return ((TreeNode)child).getParent();
			}
			return null;
		}
		public Object [] getChildren(Object parent) {
			if (parent instanceof TreeNode) {
				return ((TreeNode)parent).getChildren();
			}
			return new Object[0];
		}
		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeNode)
				return ((TreeNode)parent).hasChildren();
			return false;
		}


	}
	
	protected class ViewLabelProvider extends LabelProvider  {
		
		public String getText(Object obj)
		{
			String suffix ="";
			
			if (obj instanceof TreeNodeSimulationProject)
			{
				SimulationProject sp = ((TreeNodeSimulationProject)obj).getSimulationProject();
				switch(sp.getSimulationStatus())
				{
				
				case NEW: suffix ="NEW"; break;
				case RUNNING: suffix ="RUNNING"; break;
				case COMPLETED: suffix ="COMPLETED"; break;
				case ERROR: suffix ="ERROR"; break;
				
				
				}
				
				
				return obj.toString() + " [" + suffix + "]";
			}			
			
			return obj.toString();
			
		}
		public Image getImage(Object obj) {
			
			if (obj instanceof TreeNodeDirectory && ((TreeNodeDirectory)obj).getPathDirectory().endsWith("INPUT")) {
				return AppearanceFactory.getInstance().createImage(
						Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"folder_inbox.png");
			} else if (obj instanceof TreeNodeDirectory && ((TreeNodeDirectory)obj).getPathDirectory().endsWith("OUTPUT")) {
				return AppearanceFactory.getInstance().createImage(
						Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"folder_outbox.png");
			} else if (obj instanceof TreeNodeDirectory) {
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);	
			}
			
			if (obj instanceof TreeNodeFile)
			{
				TreeNodeFile node = (TreeNodeFile) obj;
				switch(node.getFile().getFileType())
				{
					case IFC: return AppearanceFactory.getInstance().createImage(
								Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"ifc_extension.png", 16, 16);							 
					case RDF: return AppearanceFactory.getInstance().createImage(
								Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"rdf32.png", 16, 16);
					case SIMMATRIX: return AppearanceFactory.getInstance().createImage(
							Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"matrix_ss_icon.png", 16, 16);
					
					case ZIP: return AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
									Activator.ICONS_16x16_PATH+"FreeFileIcons"+File.separator+"zip.png", 16, 16);
					case PNG: return AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
							Activator.ICONS_16x16_PATH+"FreeFileIcons"+File.separator+"png.png", 16, 16);
					case TXT: return AppearanceFactory.getInstance().createImage(Activator.PLUGIN_ID,	
							Activator.ICONS_16x16_PATH+"FreeFileIcons"+File.separator+"txt.png", 16, 16);
					case X3D: return AppearanceFactory.getInstance().createImage(
							Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"X3d.png", 16, 16);

					default: break;
				}				
			}
			
			if (obj instanceof TreeNodeProject)
			{
				return AppearanceFactory.getInstance().createImage(
						Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"project_alt_1.png", 16, 16);
			}
			
			if (obj instanceof TreeNodeSimulationProject)
			{
				
				TreeNodeSimulationProject node = (TreeNodeSimulationProject) obj;
				switch(node.getSimulationProject().getSimulationTypeId())
				{
					case NANDRAD_PASSIV:	return AppearanceFactory.getInstance().createImage(
							Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"Home-icon_red.png", 16, 16);
					case THERAKLES:	return AppearanceFactory.getInstance().createImage(
							Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"Home-icon_yellow.png", 16, 16);
					case NANDRAD:	return AppearanceFactory.getInstance().createImage(
							Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"Home-icon_green.png", 16, 16);
					case CFD:	return AppearanceFactory.getInstance().createImage(
							Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"Home-icon_blue.png", 16, 16);
						
				}
				
				
				return AppearanceFactory.getInstance().createImage(
						Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"project_alt_1.png", 16, 16);
			}
			
			
			return AppearanceFactory.getInstance().createImage(
					Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"ifc_extension.png", 16, 16);
		}
		
	}
	
	protected class DecoratingViewLabelProvider extends  DecoratingLabelProvider  {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1848067652948298316L;
		
		private ILabelDecorator decorator;
		
		public DecoratingViewLabelProvider(ILabelProvider provider,
				ILabelDecorator decorator) {
			super(provider, decorator);
			// TODO Auto-generated constructor stub
			
			this.decorator = decorator;
		}
		
		
		public String getText(Object obj) {
			return obj.toString();
		}
		public Image getImage(Object obj) {			
			
//			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//			if (obj instanceof TreeDirectory)
//			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
//			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
			
			Image img = null;
			
			if (obj instanceof TreeNodeDirectory) {
				
				img=  PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);	
			}
			else
			{
				img= AppearanceFactory.getInstance().createImage(
					Activator.PLUGIN_ID, Activator.ICONS_32x32_PATH+"ifc_extension.png", 16, 16);
			}
			
			
			return decorator.decorateImage(img, obj);
			
		}
	}
	
	
	protected class NameSorter extends ViewerSorter {
		
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) 
		{
			if(e1 instanceof TreeNodeSimulationProject &&  !(e2 instanceof TreeNodeSimulationProject) || 
					e2 instanceof TreeNodeSimulationProject &&  !(e1 instanceof TreeNodeSimulationProject))
			{
				return 1;
			}
			else
			{
				String name1 = ((TreeNode)e1).getName();
				String name2 = ((TreeNode)e2).getName();				
				
				return name1.compareToIgnoreCase(name2);
			}		
		}
		
	}


	public void setBimFitService(BimFitService modelService) {
		this.modelService = modelService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setOntologyService(OntologyService ontologyService) {
		this.ontologyService = ontologyService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public ConfigurationService getConfigurationService() {
		return configurationService;
	}


	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}	
	
	
}