package de.tudresden.bau.cib.vl.gui.bim.view.dialog;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.ontology.EnergyEnhancedBimModel;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.RuleSet;
import de.tudresden.bau.cib.vl.gui.bim.controller.OntologyController;

/**
 * Dialog for selecting rule sets.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class OntologyInferenceModelDialog extends TitleAreaDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -730962648910621587L;
	private TableViewer viewer;
	private OntologyController ontologyController;
	private InfModel infModel;
	private Collection<RuleSet> appliedRuleSets;
	private Composite area;
	private Table table;
	private static final String TYPE_PATTERN_IFC = Ifc2x3DataModelProxy.NS_IFCOWL;
	private static final String TYPE_PATTERN_EEBIM = EnergyEnhancedBimModel.NS_EEBIM;
	private TableViewerComparator comparator;


	public OntologyInferenceModelDialog(Shell parentShell, InfModel infModel, 
			Collection<RuleSet> appliedRuleSets, OntologyController ontologyController) {
		super(parentShell);
		this.infModel = infModel;
		this.ontologyController = ontologyController;
		this.appliedRuleSets = appliedRuleSets;
	}

	@Override
	public void create() {
		super.create();	
		setMessage("Inferences evaluated by eeBIM rules");
		setTitle("eeBIM Inferences");		
		// Force the shell size
		Point size = getShell().computeSize( 800, 600 );
		getShell().setSize(size);
	}

	@Override
	protected Composite createDialogArea(Composite parent) {	
		area = (Composite) super.createDialogArea(parent);
		area.setLayout(new GridLayout(1, false));
		createDropDownMenu(area);
		createViewer(area);

		return area;
	}

	private void createDropDownMenu(final Composite container) {
		final Combo resourceDropDown = new Combo(container, SWT.NONE);
		resourceDropDown.setText("Select type");
		
		Collection<Resource> resources = ontologyController.listResourceClasses();
		Resource[] resourcesArr = resources.toArray(new Resource[resources.size()]);
		sortResources(resourcesArr);
		for(Resource res : resourcesArr) {
			// convert http://141.30.143.53/ontology/ifcOWL#IfcWallStandardCase => WallStandardCase
			String name = res.getURI();
			name = name.replace(TYPE_PATTERN_IFC, "");
			name = name.replace(TYPE_PATTERN_EEBIM, "");
			resourceDropDown.add(name);
		}
		
		resourceDropDown.addSelectionListener(new SelectionAdapter() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 3401971431432645221L;

			@Override
			public void widgetSelected(SelectionEvent evt) {
				int selectionIndex = resourceDropDown.getSelectionIndex();
				String name = resourceDropDown.getItem(selectionIndex);
				String selectedResTypeUri = null;
				if(name.startsWith("Ifc")) selectedResTypeUri = TYPE_PATTERN_IFC+name;
				else selectedResTypeUri = TYPE_PATTERN_EEBIM+name;

				Resource selectedResource = infModel.getResource(selectedResTypeUri);
				Collection<Statement> statements = ontologyController.listEnergyRelatedStatementsOfResource(selectedResource);
				viewer.setInput(statements);
			}
		});
		
	}

	private void sortResources(Resource[] resourcesArr) {
		Arrays.sort(resourcesArr, new Comparator<Resource>(){

			@Override
			public int compare(Resource r1, Resource r2) {
				return r1.getURI().compareTo(r2.getURI());
			}
		});
	}

	private void createViewer(Composite parent) {			
		table = new Table(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.READ_ONLY | SWT.BORDER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		viewer = new TableViewer(table);
		createColumns(viewer);
		viewer.setContentProvider(new ArrayContentProvider());

		// define layout for the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalSpan = 1;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		viewer.getControl().setLayoutData(gridData);
		
		// sort according to due date
		comparator = new TableViewerComparator();
		viewer.setComparator(comparator); 
	}

	// create the columns for the table
	private void createColumns(final TableViewer viewer) {
		String[] titles = { "Subject", "Predicate", "Object" };
		int[] bounds = { 250, 250, 250 };

		// first column 
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5202152286158534619L;

			@Override
			public String getText(Object element) {
				Statement statement = (Statement) element;
				Resource subject = statement.getSubject();
				String uri = subject.getURI();
				String name = null;
				if(uri.contains(TYPE_PATTERN_IFC)) {
					name = ontologyController.getNameOfIfcRoot(subject);
				}
				
				if(StringUtils.isEmpty(name)) return subject.getURI();
				return name;
			}
		});

		// second column 
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {


			/**
			 * 
			 */
			private static final long serialVersionUID = -7893004670572463874L;

			@Override
			public String getText(Object element) {
				Statement statement = (Statement) element;
				String uri = statement.getPredicate().getURI();
				String name = null;
				if(uri.contains(TYPE_PATTERN_IFC)) name = uri.replace(TYPE_PATTERN_IFC, "");
				if(uri.contains(TYPE_PATTERN_EEBIM)) name = uri.replace(TYPE_PATTERN_EEBIM, "");
				return name;
			}
		});

		// third column 
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {



			/**
			 * 
			 */
			private static final long serialVersionUID = 2611099653126539167L;

			@Override
			public String getText(Object element) {
				Statement statement = (Statement) element;
				RDFNode object = statement.getObject();
				if(object.isLiteral()) {
					return ""+object.asLiteral().getValue();
				}
				return statement.getObject().toString();
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(String title, final int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column,final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2806193848746838486L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	@Override
	protected final void okPressed() {
		saveInput();
		super.okPressed();
	}

	// save content of the widget fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {

	}
	
	@Override
	protected boolean isResizable() {
		return true;
	}
	
	private class TableViewerComparator extends ViewerComparator {
		
		  /**
		 * 
		 */
		private static final long serialVersionUID = -5898458884009755875L;
		private int propertyIndex;
		  private static final int DESCENDING = 1;
		  private int direction = DESCENDING;

		  public TableViewerComparator() {
		    this.propertyIndex = 0;
		    direction = DESCENDING;
		  }

		  public int getDirection() {
		    return direction == 1 ? SWT.DOWN : SWT.UP;
		  }

		  public void setColumn(int column) {
		    if (column == this.propertyIndex) {
		      // Same column as last sort; toggle the direction
		      direction = 1 - direction;
		    } else {
		      // New column; do an ascending sort
		      this.propertyIndex = column;
		      direction = DESCENDING;
		    }
		  }

		  @Override
		  public int compare(Viewer viewer, Object e1, Object e2) {
			  Statement statement1 = (Statement) e1;
			  Statement statement2 = (Statement) e2;
			  int rc = 0;
			  rc = statement1.getSubject().getURI().compareTo(statement2.getSubject().getURI());
//			    // If descending order, flip the direction
			    if (direction == DESCENDING) {
			      rc = -rc;
			    }
			   return rc;
		  }

		} 

}
