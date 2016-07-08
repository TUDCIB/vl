package de.tudresden.bau.cib.vl.gui.bim.view.dialog;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.RuleSet;

/**
 * Dialog for selecting rule sets.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class OntologyRuleSelectionDialog extends TitleAreaDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3985942454932964909L;

	private FormToolkit toolkit;
	private CheckboxTableViewer viewer;
	private Collection<RuleSet> rules;
	private Collection<RuleSet> checkedRuleSets = new HashSet<RuleSet>();


	public OntologyRuleSelectionDialog(Shell parentShell, Collection<RuleSet> rules) {
		super(parentShell);
		this.rules = rules;
	}

	@Override
	public void create() {
		super.create();	
		setMessage("Apply rules");
		setTitle("Rule set catalogue");		
	}

	@Override
	protected Composite createDialogArea(Composite parent) {		
		Composite area = (Composite) super.createDialogArea(parent);

		toolkit = new FormToolkit(area.getDisplay());

		ScrolledForm  form = toolkit.createScrolledForm(area);
		Composite container = form.getBody();
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		createViewer(container);
		
		viewer.setInput(rules);

		return area;
	}

	private void createViewer(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		final Table table = new Table(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER | SWT.CHECK);	
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		viewer = new CheckboxTableViewer(table);
		createColumns(viewer);
		viewer.setContentProvider(new ArrayContentProvider());

		// define layout for the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	// create the columns for the table
	private void createColumns(final TableViewer viewer) {
		String[] titles = { "Apply", "Name", "Description" };
		int[] bounds = { 50, 300, 500 };

		// first column is for the edit action
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5687962576186135936L;

			@Override
			public String getText(Object element) {
				return null;
			}
		});

		// second column is for the name
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1145367741632947256L;

			@Override
			public String getText(Object element) {
				RuleSet rule = (RuleSet) element;
				return rule.getName();
			}
		});

		// third column is for the description
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {


			/**
			 * 
			 */
			private static final long serialVersionUID = -3284207340619400274L;

			@Override
			public String getText(Object element) {
				RuleSet rule = (RuleSet) element;
				return rule.getDescription();
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
		return viewerColumn;
	}

	@Override
	protected final void okPressed() {
		saveInput();
		super.okPressed();
	}

	// save content of the widget fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		checkedRuleSets = new HashSet<RuleSet>();
		Object[] checkedElements = viewer.getCheckedElements();
		for(Object checkedElement : checkedElements) {
			if(checkedElement instanceof RuleSet) {
				checkedRuleSets.add((RuleSet) checkedElement);
			}
		}
	}
	
	public Collection<RuleSet> getCheckedRuleSets() {
		return checkedRuleSets;
	}
	
	@Override
	protected boolean isResizable() {
		return true;
	}

}
