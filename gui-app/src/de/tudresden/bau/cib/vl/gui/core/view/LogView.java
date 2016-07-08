package de.tudresden.bau.cib.vl.gui.core.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import de.tudresden.bau.cib.vl.gui.common.appearance.AppearanceFactory;
import de.tudresden.bau.cib.vl.gui.core.logging.LoggingAppender;

public class LogView extends ViewPart implements PropertyChangeListener {
	
	
	private LoggingAppender<Object> appender;
	private TableViewer transcript;
	private List<String> messages = new ArrayList<String>();
	private Display display;
	
	private enum Level {
		ALL ("All"),
		TRACE ("Trace"),
		DEBUG ("Debug"),
		INFO ("Info"),
		ERROR ("Error"),
		FATAL ("Fatal");
		
		private String name;
		
		private Level(String name) {
			this.name = name;
		}
		
		public static Level findByAbbreviation(String name) {
			for(Level level : values()) {
				if(level.name.equalsIgnoreCase(name)) return level;
			}
			return null;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	

	@Override
	public void createPartControl(Composite parent) {
		display = parent.getDisplay();
		parent.setLayout(new FillLayout(SWT.VERTICAL));
		
		FormToolkit toolkit = new FormToolkit(display);
		
	    Form form = toolkit.createForm(parent);
		toolkit.decorateFormHeading(form);
		form.setText("Log messages");
		Composite header = form.getHead();
		header.setLayout(new GridLayout());
		
		Composite body = form.getBody();
		body.setLayout(new GridLayout());
	    
		Composite headerContainer = new Composite(header, SWT.NONE);
		headerContainer.setLayout(new GridLayout(2, false));
		
		final Combo chooseLogCombo = new Combo(headerContainer, SWT.NONE);
		chooseLogCombo.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, true));
		chooseLogCombo.add(Level.ALL.name);
		chooseLogCombo.add(Level.TRACE.name);
		chooseLogCombo.add(Level.DEBUG.name);
		chooseLogCombo.add(Level.INFO.name);
		chooseLogCombo.add(Level.ERROR.name);
		chooseLogCombo.add(Level.FATAL.name);
		chooseLogCombo.select(0);
		
		// important
		form.setHeadClient(headerContainer);

		this.transcript = createTable(body);
		
		final LevelFilter filter = new LevelFilter();
		transcript.addFilter(filter);
		
		chooseLogCombo.addSelectionListener(new SelectionListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1318822439618933415L;

			@Override
			public void widgetSelected(SelectionEvent se) {
				String level = chooseLogCombo.getItem(chooseLogCombo.getSelectionIndex());
				filter.setSearchText(Level.findByAbbreviation(level));
				transcript.refresh();
				alternateColoringAfterFilter();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent se) {
				widgetSelected(se);
			}
		});
	}
	
	private class LevelFilter extends ViewerFilter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6276200704049861243L;
		private String searchString;

		public void setSearchText(Level level) {
			switch(level) {
			case TRACE: 
			case DEBUG:
			case INFO:
			case ERROR:
			case FATAL: 
				this.searchString = "["+level.name.toUpperCase()+"]";
				break;
			case ALL:
				this.searchString = null;
				break;
			default:
				this.searchString = null;
				break;
			}			
		}

		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if (searchString == null || searchString.length() == 0) {
				return true;
			}
			String text = (String) element;
			if (text.startsWith(searchString)) {
				return true;
			}
			return false;
		}
	}
	
	private void alternateColoringAfterFilter() {
		TableItem[] items = transcript.getTable().getItems();
		for(int i = 0; i < items.length; i++) {
			items[i].setBackground(getColorByIndex(i));
		}
	}
	
	private TableViewer createTable(Composite parent) {
		TableViewer viewer = new TableViewer(parent);

		viewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setLabelProvider(new LabelProvider());	
		viewer.setContentProvider(new IStructuredContentProvider() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -232644620286334587L;

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
			
			@Override
			public void dispose() {
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				@SuppressWarnings("unchecked")
				List<String> text = (List<String>) inputElement;
				return text.toArray();
			}
		});

		viewer.setInput(messages);
		return viewer;
	}
	
	private Color getColorByIndex(int index) {
		if(index % 2 == 0) {
			// light grey
			return AppearanceFactory.LIGHT_GREY;
		} else {
			return display.getSystemColor(SWT.COLOR_WHITE);
		}
	}
	
	private void addEntry(Object eventObject) {
		String entry = eventObject.toString();
		messages.add(entry);
		// insert new TableItem
		transcript.add(entry);
		
		// alternate coloring of the TableItem
		Table table = transcript.getTable();
		int numberOfItems = table.getItemCount();
		if(numberOfItems > 0) {
			TableItem lastItem = table.getItem(numberOfItems-1);
			lastItem.setBackground(getColorByIndex(numberOfItems));
		}
	}
	
	private void scrollDown() {
		int itemCount = transcript.getTable().getItemCount();
		if(itemCount > 0) {
			TableItem item = transcript.getTable().getItem(itemCount-1);
			if(item != null) transcript.getTable().showItem(item);
		}
	}

	@Override
	public void setFocus() {
	}

	public void setAppender(LoggingAppender<Object> appender) {
		this.appender = appender;
		this.appender.addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		final Object eventObject = evt.getNewValue();
		
		if(transcript == null) return;
		synchronized (transcript) {
			if(transcript.getControl().isDisposed()) return;
			transcript.getControl().getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (transcript.getControl().isDisposed()) return;
					addEntry(eventObject);
					// scroll down
					scrollDown();
				}
			});
		}
	}

}
