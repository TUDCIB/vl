package de.tudresden.bau.cib.vl.gui.bim.view.dialog;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.vocabulary.XSD;

import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.gui.bim.controller.OntologyController;

/**
 * Dialog for setting eeBim parameter.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class OntologyEeBimParameterDialog extends TitleAreaDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7141772649994565772L;
	private FormToolkit toolkit;
	
	private Statement[] statements;
	private Map<Integer, Text> saveContextMap;
	private OntologyModel ontologyModel;
	private OntologyController ontologyController;


	public OntologyEeBimParameterDialog(Shell parentShell, Collection<Statement> statements, 
			OntologyModel ontologyModel, OntologyController controller) {
		super(parentShell);
		saveContextMap = new HashMap<Integer, Text>();
		this.statements = statements.toArray(new Statement[statements.size()]);
		this.ontologyModel = ontologyModel;
		this.ontologyController = controller;
		sortStatements();
	}

	@Override
	public void create() {
		super.create();	
		setMessage("Set eeBIM parameters");
		setTitle("eeBIM parameters");		
	}

	@Override
	protected Composite createDialogArea(Composite parent) {		
		Composite area = (Composite) super.createDialogArea(parent);

		toolkit = new FormToolkit(area.getDisplay());

		ScrolledForm  form = toolkit.createScrolledForm(area);
		Composite container = form.getBody();
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		createForm(container);

		return area;
	}

	private void createForm(Composite parent) {
		parent.setLayout(new GridLayout(3, false));

		for(int i = 0; i < statements.length; i++) {
			final int j = i; // needed as index for saving
			Statement stmt = statements[i];
			Resource subject = stmt.getSubject();
			Property predicate = stmt.getPredicate();
			RDFNode objectNode = stmt.getObject();
			// create subject label
			String name = null;
			String uri = subject.getURI();
			if(uri.contains(Ifc2x3DataModelProxy.NS_IFCOWL)) {
				name = ontologyController.getNameOfIfcRoot(subject);
			}
			toolkit.createLabel(parent, StringUtils.isEmpty(name) ? subject.getLocalName() : name);
			// create predicate label
			toolkit.createLabel(parent, predicate.getLocalName());
			// create object text
			Text objectValue = toolkit.createText(
					parent, 
					objectNode.isLiteral() ? objectNode.asLiteral().getValue().toString() : objectNode.asNode().getURI());
			if(!objectNode.isLiteral()) objectValue.setEnabled(false); // disable it in the case of a Resource
			objectValue.addModifyListener(new ModifyListener() {

				
				/**
				 * 
				 */
				private static final long serialVersionUID = -6929354730398910697L;

				@Override
				public void modifyText(ModifyEvent event) {
					Text text = (Text) event.widget;
					saveContextMap.put(j, text);
				}
				
			});
			GridData gridData = new GridData();
			gridData.verticalAlignment = SWT.FILL;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = true;
			gridData.horizontalAlignment = SWT.FILL;
			objectValue.setLayoutData(gridData);
		}		
	}
	
	private void sortStatements() {
		Arrays.sort(statements, new Comparator<Statement>(){

			@Override
			public int compare(Statement s1, Statement s2) {
				return s1.getSubject().getURI().compareTo(s2.getSubject().getURI());
			}
		});
	}

	@Override
	protected final void okPressed() {
		saveInput();
		super.okPressed();
	}

	// save content of the widget fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		for (Map.Entry<Integer, Text> entry : saveContextMap.entrySet()) {
			Integer key = entry.getKey();
			Text text = entry.getValue();
			Statement stmt = statements[key];
			if(stmt.getObject().isLiteral()) {
				String datatypeUri = stmt.getObject().asLiteral().getDatatypeURI();
				// create the appropriate data type based on the previous statement (which should actually be based on the scheme)
				if(datatypeUri.equals(XSD.xstring.getURI())) {
					stmt.changeObject(text.getText());
				} else if(datatypeUri.equals(XSD.xlong.getURI())) {
					stmt.changeLiteralObject(Long.valueOf(text.getText()));
				} else if(datatypeUri.equals(XSD.xint.getURI())) {
					stmt.changeLiteralObject(Integer.valueOf(text.getText()));
				} else if(datatypeUri.equals(XSD.xfloat.getURI())) {
					stmt.changeLiteralObject(Float.valueOf(text.getText()));
				} else if(datatypeUri.equals(XSD.xdouble.getURI())) {
					stmt.changeLiteralObject(Double.valueOf(text.getText()));
				} else if(datatypeUri.equals(XSD.xboolean.getURI())) {
					stmt.changeLiteralObject(Boolean.valueOf(text.getText()));
				} else {
					throw new IllegalArgumentException("Datatype: "+datatypeUri+" not supported");
				}
			} else {
				Individual ind = ontologyModel.getIndividualByURI(text.getText());
				stmt.changeObject(ind);
				System.out.println("s");
			}
		}
	}
	
	@Override
	protected boolean isResizable() {
		return true;
	}

}
