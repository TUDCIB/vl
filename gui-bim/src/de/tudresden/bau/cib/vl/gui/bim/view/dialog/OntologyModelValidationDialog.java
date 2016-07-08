package de.tudresden.bau.cib.vl.gui.bim.view.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;

import de.tudresden.bau.cib.vl.gui.bim.Activator;

/**
 * Dialog for selecting rule sets.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class OntologyModelValidationDialog extends TitleAreaDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9053100840866641673L;
	
	private TableViewer viewer;
	private Composite area;
	private Table table;
	private TableViewerComparator comparator;
	private ValidityReport validityReport;

	private static final String ERROR = Activator.ICONS_16x16_PATH+"messagebox_critical.png";
	private static final String WARNING = Activator.ICONS_16x16_PATH+"messagebox_info.png";
	

	public OntologyModelValidationDialog(Shell parentShell, ValidityReport validityReport) {
		super(parentShell);
		this.validityReport = validityReport;
	}

	@Override
	public void create() {
		super.create();	
		setMessage("Model validated by eeBIM rules");
		setTitle("Validation results");		
		// Force the shell size
		Point size = getShell().computeSize( 800, 600 );
		getShell().setSize(size);
	}

	@Override
	protected Composite createDialogArea(Composite parent) {	
		area = (Composite) super.createDialogArea(parent);
		area.setLayout(new GridLayout(1, false));
		createViewer(area);
		List<Report> reports = new ArrayList<ValidityReport.Report>();
		Iterator<Report> iterator = validityReport.getReports();
		while(iterator.hasNext()) {
			Report report = iterator.next();
			reports.add(report);
		}
		viewer.setInput(reports);
		return area;
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
		String[] titles = { "Error", "Type", "Description", "Extension" };
		int[] bounds = { 50, 250, 250 , 250};

		// first column 
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				return null;
			}

			@Override
			public Image getImage(Object element) {
				if (((Report) element).isError) {
					return Activator.getImageDescriptor(ERROR).createImage();
				} else {
					return Activator.getImageDescriptor(WARNING).createImage();
				}
			}
		});

		// second column 
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				Report report = (Report) element;
				return report.getType();
			}
		});

		// third column 
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				Report report = (Report) element;
				return report.getDescription();
			}
		});
		
		// fourth column 
		col = createTableViewerColumn(titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				Report report = (Report) element;
				return report.getExtension().toString();
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
			  Report report1 = (Report) e1;
			  Report report2 = (Report) e2;
			  int rc = 0;
			  rc = report1.getType().compareTo(report2.getType());
//			    // If descending order, flip the direction
			    if (direction == DESCENDING) {
			      rc = -rc;
			    }
			   return rc;
		  }

		} 

}
