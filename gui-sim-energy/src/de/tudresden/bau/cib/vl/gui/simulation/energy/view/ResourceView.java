package de.tudresden.bau.cib.vl.gui.simulation.energy.view;

import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import de.tudresden.bau.cib.vl.core.model.Resource;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.gui.common.Constants;
import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;
import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.ResourceController;
import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.ResourceController.ResourceType;
import de.tudresden.bau.cib.vl.gui.simulation.energy.utility.ResourceDragListener;

public class ResourceView extends AbstractView<ResourceController> {

	public static final String ID = Constants.PLACEHOLDER_PREFIX+".simulation.energy."+Constants.PLACEHOLDER_WINDOW_TOP+".ResourceView";

	private FormToolkit toolkit;
	private TreeViewer constructionViewer, occupancyViewer, climateViewer;
	private ResourceFilter resourceFilter;

	private ResourceController controller;

	private TemplateService templateService;
	
	private static Image RES_IMG = AppearanceFactory.getInstance().createImage(
			Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"resource.png", 16, 16);

	@Override
	protected ResourceController createController() {
		controller = new ResourceController();
		controller.setTemplateService(templateService);
		return controller;
	}


	@Override
	public void createPartControl(final Composite parent) {
		parent.setLayout(new FillLayout(SWT.VERTICAL));
		toolkit = new FormToolkit(parent.getDisplay());

		ScrolledForm form = toolkit.createScrolledForm(parent);
		toolkit.decorateFormHeading(form.getForm());
		form.setText("Resources");
		Composite body = form.getBody();
		body.setLayout(new GridLayout(1, false));

		Section constructionResourcesSection 		= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
		constructionResourcesSection.setText("Constructions");
		Composite constructionsComposite	=	toolkit.createComposite(constructionResourcesSection, SWT.WRAP);
		constructionsComposite.setLayout(new GridLayout(2, false));

		toolkit.createLabel(constructionsComposite, "Search construction: ");
		final Text constructionSearchText = toolkit.createText(constructionsComposite, "", SWT.BORDER | SWT.SEARCH);
		constructionSearchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));

		constructionViewer = createConstructionResourcesTree(constructionsComposite);
		constructionResourcesSection.setClient(constructionsComposite);
		constructionResourcesSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// New to support the search
		constructionSearchText.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {
				resourceFilter.setSearchText(constructionSearchText.getText());
				constructionViewer.refresh();
				parent.layout(true, true);
			}

		});
		resourceFilter = new ResourceFilter();
		constructionViewer.addFilter(resourceFilter);

//		Section occupancyResourcesSection 		= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
//		occupancyResourcesSection.setText("Occupancy");
//		Composite occupancyResourcesComposite	=	toolkit.createComposite(occupancyResourcesSection, SWT.WRAP);
//		occupancyResourcesComposite.setLayout(new FillLayout());
//		occupancyViewer = createOccupancyResourcesTree(occupancyResourcesComposite);
//		occupancyResourcesSection.setClient(occupancyResourcesComposite);
//		occupancyResourcesSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Section climateResourcesSection 		= 	toolkit.createSection(body, Section.TITLE_BAR | Section.TWISTIE | Section.COMPACT);
		climateResourcesSection.setText("Climate");
		Composite climateResourcesComposite	=	toolkit.createComposite(climateResourcesSection, SWT.WRAP);
		climateResourcesComposite.setLayout(new FillLayout());
		climateViewer = createClimateResourcesTree(climateResourcesComposite);
		climateResourcesSection.setClient(climateResourcesComposite);
		climateResourcesSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		initializeViewers();

		initializeDND(constructionViewer);
//		initializeDND(occupancyViewer);
		initializeDND(climateViewer);
	}

	public void initializeViewers() {
		controller.loadConstructionResources();
//		controller.loadOccupancyResources();
		controller.loadClimateResources();
	}

	public void setInput(final ResourceType type) {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				switch(type) {
				case CONSTRUCTION:
					if(!constructionViewer.getTree().isDisposed()) {
						constructionViewer.setInput(ResourceController.ResourceType.CONSTRUCTION);
					}
					break;
//				case OCCUPANCY:
//					if(!occupancyViewer.getTree().isDisposed()) {
//						occupancyViewer.setInput(ResourceController.ResourceType.OCCUPANCY);
//					}
//					break;
				case CLIMATE:
					if(!climateViewer.getTree().isDisposed()) {
						climateViewer.setInput(ResourceController.ResourceType.CLIMATE);
					}
					break;
				default: break;
				}
			}		
		});
	}

	private void initializeDND(StructuredViewer viewer) {
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
		Transfer[] transferTypes = new Transfer[]{TextTransfer.getInstance()};
		ResourceDragListener dragListener = new ResourceDragListener(viewer);
		viewer.addDragSupport(operations, transferTypes, dragListener);
	}

	private TreeViewer createConstructionResourcesTree(Composite parent) {
		Tree tree = toolkit.createTree(parent, 
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		TreeViewer viewer = new TreeViewer(tree);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		tree.setLayoutData(gridData);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());

		//		viewer.addSelectionChangedListener(new ISelectionChangedListener(){
		//
		//			@Override
		//			public void selectionChanged(SelectionChangedEvent event) {
		//				ISelection selection = event.getSelection();
		//				if(selection instanceof IStructuredSelection) {
		//					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		//
		//					Object firstSelection = structuredSelection.getFirstElement();
		//					if(firstSelection instanceof Resource){
		//						Resource item = (Resource) firstSelection;
		//					
		//					}
		//				}
		//			}		
		//		});
		return viewer;	
	}

	private TreeViewer createOccupancyResourcesTree(Composite parent) {
		Tree tree = toolkit.createTree(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		TreeViewer viewer = new TreeViewer(tree);

		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());

		//		viewer.addSelectionChangedListener(new ISelectionChangedListener(){
		//
		//			@Override
		//			public void selectionChanged(SelectionChangedEvent event) {
		//				ISelection selection = event.getSelection();
		//				if(selection instanceof IStructuredSelection) {
		//					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		//
		//					Object firstSelection = structuredSelection.getFirstElement();
		//					if(firstSelection instanceof Resource){
		//						Resource item = (Resource) firstSelection;
		//					
		//					}
		//				}
		//			}		
		//		});
		return viewer;
	}

	private TreeViewer createClimateResourcesTree(Composite parent) {
		Tree tree = toolkit.createTree(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		TreeViewer viewer = new TreeViewer(tree);

		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());

		//		viewer.addSelectionChangedListener(new ISelectionChangedListener(){
		//
		//			@Override
		//			public void selectionChanged(SelectionChangedEvent event) {
		//				ISelection selection = event.getSelection();
		//				if(selection instanceof IStructuredSelection) {
		//					IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		//
		//					Object firstSelection = structuredSelection.getFirstElement();
		//					if(firstSelection instanceof Resource){
		//						Resource item = (Resource) firstSelection;
		//					
		//					}
		//				}
		//			}		
		//		});
		return viewer;	
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {

	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	private class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {

		}
		public void dispose() {

		}
		public Object[] getElements(Object parent) {
			List<? extends Resource> resources = null;
			if (parent instanceof ResourceController.ResourceType) {
				ResourceController.ResourceType type = (ResourceController.ResourceType) parent;

				switch(type) {
				case CONSTRUCTION: resources = controller.getConstructionResources();
				break;
				case OCCUPANCY: resources = controller.getOccupancyResources();
				break;
				case CLIMATE: resources = controller.getClimateResources(); 
				break;
				default: throw new AssertionError();
				}
			}
			return resources.toArray();
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return null;
		}
		@Override
		public Object getParent(Object element) {
			return null;
		}
		@Override
		public boolean hasChildren(Object element) {
			return false;
		}
	}
	private class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			if(obj instanceof Resource) {
				Resource resource = (Resource) obj;
				String name = resource.getName();
				if(resource instanceof ConstructionTemplate) {
					ConstructionTemplate construction = (ConstructionTemplate) resource;
					if(construction.getUValue() != null) { // if u-value is set
						double uValue = construction.getUValue();
						uValue = Precision.round(uValue, 2);
						name += " [u-value: "+uValue+"]";
					}
				}
				return name;
			}
			return obj.toString();
		}
		public Image getImage(Object obj) {
			return RES_IMG;
		}
	}

	private class ResourceFilter extends ViewerFilter {

		private String searchString;

		public void setSearchText(String s) {
			// ensure that the value can be used for matching 
			this.searchString = ".*" + s + ".*";
		}

		@Override
		public boolean select(Viewer viewer, 
				Object parentElement, 
				Object element) {
			if (searchString == null || searchString.length() == 0) {
				return true;
			}
			Resource r = (Resource) element;
			if (r.getName().toLowerCase().matches(searchString.toLowerCase())) {
				return true;
			}

			return false;
		}
	} 

	private class NameSorter extends ViewerSorter {

	}
}