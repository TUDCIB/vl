//package de.tudresden.bau.cib.vl.gui.core.view;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.ArrayList;
//import java.util.Set;
//
//import org.eclipse.core.runtime.IAdaptable;
//import org.eclipse.jface.action.Action;
//import org.eclipse.jface.action.IMenuListener;
//import org.eclipse.jface.action.IMenuManager;
//import org.eclipse.jface.action.IToolBarManager;
//import org.eclipse.jface.action.MenuManager;
//import org.eclipse.jface.action.Separator;
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.jface.resource.ImageDescriptor;
//import org.eclipse.jface.viewers.DoubleClickEvent;
//import org.eclipse.jface.viewers.IDoubleClickListener;
//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.ISelectionChangedListener;
//import org.eclipse.jface.viewers.IStructuredContentProvider;
//import org.eclipse.jface.viewers.IStructuredSelection;
//import org.eclipse.jface.viewers.ITreeContentProvider;
//import org.eclipse.jface.viewers.LabelProvider;
//import org.eclipse.jface.viewers.SelectionChangedEvent;
//import org.eclipse.jface.viewers.TreeViewer;
//import org.eclipse.jface.viewers.Viewer;
//import org.eclipse.jface.viewers.ViewerSorter;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.FileDialog;
//import org.eclipse.swt.widgets.Menu;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.ui.IActionBars;
//import org.eclipse.ui.ISharedImages;
//import org.eclipse.ui.IWorkbenchActionConstants;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.part.DrillDownAdapter;
//import org.eclipse.ui.part.ViewPart;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.tudresden.bau.cib.vl.core.exception.ParsingException;
//import de.tudresden.bau.cib.vl.core.platform.exception.ManagementException;
//import de.tudresden.bau.cib.vl.core.service.ModelService;
//import de.tudresden.bau.cib.vl.core.service.UserService;
//import de.tudresden.bau.cib.vl.core.util.VirtualFile;
//import de.tudresden.bau.cib.vl.gui.Activator;
//import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
//import de.tudresden.bau.cib.vl.gui.core.controller.FileController;
//import de.tudresden.bau.cib.vl.gui.core.messages.Messages;
//import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;
//
//
//
///**
// * @author Ken
// *
// */
//public class FileView extends ViewPart {
//	
//	/**
//	 * The ID of the view as specified by the extension.
//	 */
//	public static final String ID = "de.tudresden.bau.cib.vl.gui.core.view.FileView";
////	public static final String ID = Constants.PLACEHOLDER_PREFIX+".core."+Constants.PLACEHOLDER_WINDOW_LEFT+".FileView";
//
//	protected TreeViewer viewer;
//	private DrillDownAdapter drillDownAdapter;
//	private Action uploadAction;
//	private Action doubleClickAction;
//	
//	private static final Logger LOG = LoggerFactory.getLogger(FileView.class);
//	
//	private Action selectFileAction;
//	
//	private VirtualFile selectedFile;
//	
//	private UserService userService;
//	private ModelService modelService;
//	private CommunicationService communicationService;
//	
//	private FileController controller = FileController.getInstance();
//
//
//	/**
//	 * This is a callback that will allow us
//	 * to create the viewer and initialize it.
//	 */
//	public void createPartControl(Composite parent) {
////		parent.setLayout(new FillLayout(SWT.VERTICAL));
////		Composite ownUrlComposite = new Composite(parent, SWT.NONE);
////		ownUrlComposite.setLayout(new GridLayout(3, true));
////		Label urlLabel = new Label(ownUrlComposite, SWT.NONE);
////		urlLabel.setText("GUID: ");
////		final Text guidSelectionText = new Text(ownUrlComposite, SWT.NONE);
////		Button sendUrlButton = new Button(ownUrlComposite, SWT.PUSH);
////		sendUrlButton.setText("Select");
////		sendUrlButton.addSelectionListener(new SelectionListener() {
////			
////			@Override
////			public void widgetSelected(SelectionEvent e) {
////				String guid = guidSelectionText.getText();			
////				controller.setSelection(guid);
////			}
////			
////			@Override
////			public void widgetDefaultSelected(SelectionEvent e) {
////				widgetSelected(e);
////			}
////		});
//		
//		controller.setFileView(this);
//		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
//		drillDownAdapter = new DrillDownAdapter(viewer);
//		viewer.setContentProvider(new ViewContentProvider());
//		viewer.setLabelProvider(new ViewLabelProvider());
//		viewer.setSorter(new NameSorter());
//		viewer.setInput(getViewSite());
//		
//		viewer.addSelectionChangedListener(new ISelectionChangedListener(){
//
//			@Override
//			public void selectionChanged(SelectionChangedEvent event) {
//				ISelection selection = event.getSelection();
//				if(selection instanceof IStructuredSelection) {
//					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
//
//					Object firstSelection = structuredSelection.getFirstElement();
//					if(firstSelection instanceof TreeFile){
//						TreeFile to = (TreeFile) firstSelection;
//						VirtualFile file = to.getFile();
//						if(file != null) {
//							selectedFile = file;
//						}
//					}
//				}
//			}		
//		});
//
//		makeActions();
//		hookContextMenu();
//		hookDoubleClickAction();
//		contributeToActionBars();
//	}
//
//	private void hookContextMenu() {
//		MenuManager menuMgr = new MenuManager("#PopupMenu");
//		menuMgr.setRemoveAllWhenShown(true);
//		menuMgr.addMenuListener(new IMenuListener() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -7176402400892611737L;
//
//			public void menuAboutToShow(IMenuManager manager) {
//				FileView.this.fillContextMenu(manager);
//			}
//		});
//		Menu menu = menuMgr.createContextMenu(viewer.getControl());
//		viewer.getControl().setMenu(menu);
//		getSite().registerContextMenu(menuMgr, viewer);
//	}
//
//	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalPullDown(bars.getMenuManager());
//		fillLocalToolBar(bars.getToolBarManager());
//	}
//
//	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(selectFileAction);
//		manager.add(new Separator());
//		manager.add(uploadAction);
//	}
//
//	private void fillContextMenu(IMenuManager manager) {
//		manager.add(selectFileAction);
//		manager.add(uploadAction);
//		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
//		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//	}
//	
//	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(selectFileAction);
//		manager.add(uploadAction);
//		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
//	}
//
//	protected void makeActions() {
//		selectFileAction = new Action() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 6768249098718379578L;
//
//			public void run() {
//				try {
//					controller.setSelectedFile(selectedFile);
//				} catch (ParsingException e) {
//					e.printStackTrace();
////					showMessage(selectedFilePath+" " + LocalizationFactory.getInstance().getText("MSG_FILE_SELECTED"));
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} catch (ManagementException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		selectFileAction.setText(Messages.get().ACTION_SELECT_FILE);
//		selectFileAction.setToolTipText(Messages.get().ACTION_SELECT_FILE);
//		ImageDescriptor imageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
//				Activator.PLUGIN_ID, FileController.ICONS_22x22_PATH+"button_accept.png");
//		selectFileAction.setImageDescriptor(imageDescriptor);
//		
//		uploadAction = new Action() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -124853728260834826L;
//
//			public void run() {
//				Shell shell = new Shell(Display.getCurrent());
//				shell.setLocation(0, 0);
//				shell.setSize(640, 480);
//				FileDialog fileDialog = new FileDialog(shell, SWT.TITLE | SWT.MULTI);
////				FileDialog fileDialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.TITLE | SWT.MULTI);
//				fileDialog.setText( "Upload Files" );
////				fileDialog.setAutoUpload( true );
//				fileDialog.open();
//				String[] filePaths = fileDialog.getFileNames();
//				boolean success = controller.uploadFiles(filePaths);
////				if(success) {
////					showMessage("File(s) uploaded successfully.");
////				}
//			}
//		};
//		uploadAction.setText(Messages.get().ACTION_UPLOAD_FILE);
//		uploadAction.setToolTipText(Messages.get().ACTION_UPLOAD_FILE);
//		uploadAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
//		
//		doubleClickAction = new Action() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -8749312588115619510L;
//
//			public void run() {
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection)selection).getFirstElement();
////				showMessage(LocalizationFactory.getInstance().getText("MSG_FILE_SELECTED"));
//				TreeFile tf = (TreeFile)obj;
//				VirtualFile selected = tf.getFile();
//				if(selected != null) {
//					selectedFile = selected;
//					try {
//						controller.setSelectedFile(selectedFile);
////						showMessage("Selected file: "+selected.getName());
//					} catch (ParsingException e) {
//					} catch (MalformedURLException e) {
//						e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//					} catch (ManagementException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//	}
//
//	protected void hookDoubleClickAction() {
//		viewer.addDoubleClickListener(new IDoubleClickListener() {
//			public void doubleClick(DoubleClickEvent event) {
//				doubleClickAction.run();
//			}
//		});
//	}
//	
//	public void updateTreeViewer() {
//		Display.getCurrent().asyncExec(new Runnable() {
//			
//			@Override
//			public void run() {
//				viewer.setInput(getViewSite());
//			}
//		});
//		
//	}
//	
//	private void showMessage(String message) {
//		MessageDialog.openInformation(
//			this.getViewSite().getShell(),
//			Messages.get().VIEW_DATAMANAGEMENT,
//			message);
//	}
//
//	/**
//	 * Passing the focus request to the viewer's control.
//	 */
//	public void setFocus() {
//		viewer.getControl().setFocus();
//	}
//	
//	protected class TreeFile implements IAdaptable {
//		private String name;
//		private TreeDirectory parent;
//		private VirtualFile treeFile;
//		
//		public TreeFile(String name) {
//			this.name = name;
//		}
//		
//		public String getName() {
//			return name;
//		}
//		
//		public void setParent(TreeDirectory parent) {
//			this.parent = parent;
//		}
//		
//		public TreeDirectory getParent() {
//			return parent;
//		}
//		
//		public String toString() {
//			return getName();
//		}
//		
//		public Object getAdapter(Class key) {
//			return null;
//		}
//		
//		public VirtualFile getFile() {
//			return treeFile;
//		}
//		
//		public void setTreeFile(VirtualFile file) {
//			this.treeFile = file;
//		}
//	}
//	
//	protected class TreeDirectory extends TreeFile {
//		private ArrayList<TreeFile> children;
//		
//		public TreeDirectory(String name) {
//			super(name);
//			children = new ArrayList<TreeFile>();
//		}
//		
//		public void addChild(TreeFile child) {
//			children.add(child);
//			child.setParent(this);
//		}
//		
//		public void removeChild(TreeFile child) {
//			children.remove(child);
//			child.setParent(null);
//		}
//		
//		public TreeFile [] getChildren() {
//			return (TreeFile [])children.toArray(new TreeFile[children.size()]);
//		}
//		
//		public boolean hasChildren() {
//			return children.size()>0;
//		}
//	}
//
//	protected class ViewContentProvider implements IStructuredContentProvider, 
//										   ITreeContentProvider {
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = -446259282942973426L;
//		protected TreeDirectory invisibleRoot;
//
//		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
//			
//		}
//		public void dispose() {
//			
//		}
//		public Object[] getElements(Object parent) {
//			if (parent.equals(getViewSite())) {
////				if (invisibleRoot==null) {
//					initialize();
////				}
//				return getChildren(invisibleRoot);
//			}
//			return getChildren(parent);
//		}
//		public Object getParent(Object child) {
//			if (child instanceof TreeFile) {
//				return ((TreeFile)child).getParent();
//			}
//			return null;
//		}
//		public Object [] getChildren(Object parent) {
//			if (parent instanceof TreeDirectory) {
//				return ((TreeDirectory)parent).getChildren();
//			}
//			return new Object[0];
//		}
//		public boolean hasChildren(Object parent) {
//			if (parent instanceof TreeDirectory)
//				return ((TreeDirectory)parent).hasChildren();
//			return false;
//		}
//
//		protected void initialize() {
//			TreeDirectory root = new TreeDirectory(Messages.get().FILES);
//			invisibleRoot = new TreeDirectory("");
//			invisibleRoot.addChild(root);	
//			
////			add files of the current user
//			Set<VirtualFile> allFiles;
//			try {
//				allFiles = controller.getFilesOfCurrentUser("ifc", true);
//				allFiles.addAll(controller.getFilesOfCurrentUser("x3d", true));			
//				if(allFiles != null) {
//					for(VirtualFile file : allFiles) {
//						String name = file.getName();
//						TreeFile tf = new TreeFile(name);
//						tf.setTreeFile(file);
//						root.addChild(tf);	
//					}
//	//				TODO currenty we can't read ZipInputStreams from a URL
//	//				allFiles = controller.getFilesOfCurrentUser(SUPPORTED_FILE_FORMAT.IFC_ZIP.toString(), true);
//	//				for(VirtualFile file : allFiles) {
//	//					String name = file.getName();
//	//					TreeFile tf = new TreeFile(name);
//	//					tf.setTreeFile(file);
//	//					root.addChild(tf);	
//	//				}
//				}
//			} catch (MalformedURLException e) {
//				LOG.error(e.getMessage());
//			}
//
//		}
//	}
//	protected class ViewLabelProvider extends LabelProvider {
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 5540058305244207256L;
//		public String getText(Object obj) {
//			return obj.toString();
//		}
//		public Image getImage(Object obj) {
////			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
////			if (obj instanceof TreeDirectory)
////			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
////			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
//			
//			if (obj instanceof TreeDirectory) {
//				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);	
//			}
//			return AppearanceFactory.getInstance().getImage(
//					Activator.PLUGIN_ID, FileController.ICONS_32x32_PATH+"ifc_extension.png", 16, 16);
//		}
//	}
//	protected class NameSorter extends ViewerSorter {
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = -1879836032391337546L;
//	}
//	
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//		controller.setUserService(userService);
//	}
//
//	public void setModelService(ModelService modelService) {
//		this.modelService = modelService;
//		controller.setModelService(modelService);
//	}
//
//	public void setCommunicationService(CommunicationService communicationService) {
//		this.communicationService = communicationService;
//		controller.setCommunicationService(communicationService);
//	}
//
//
//}