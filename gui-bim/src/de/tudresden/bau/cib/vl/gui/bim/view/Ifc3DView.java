package de.tudresden.bau.cib.vl.gui.bim.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.tudresden.bau.cib.ui.widgets.ifcviewer.IfcViewer;
import de.tudresden.bau.cib.vl.gui.bim.controller.Ifc3DViewController;
import de.tudresden.bau.cib.vl.gui.bim.view.dnd.IFCViewerDropListener;
import de.tudresden.bau.cib.vl.gui.common.view.AbstractView;



public class Ifc3DView extends AbstractView<Ifc3DViewController> implements PropertyChangeListener {
	
	private IfcViewer viewer;
	
	private URL url;
	private Collection<String> selection;
	private boolean isHidden;
	private Display display;
	private Composite parent;
	private Ifc3DViewController controller;
	private IToolBarManager toolBarManager;
	private Action decolorizeAction;
	
	
	@Override
	protected Ifc3DViewController createController() {
		controller = new Ifc3DViewController();
		return controller;
	}


	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		this.display = parent.getDisplay();
		
		toolBarManager = getViewSite().getActionBars().getToolBarManager();
		
//		show IFC viewer
	    viewer = new IfcViewer(parent);	 
	    viewer.addPropertyChangeListener(this);
	    
		makeActions();
		createToolBar();
	    initializeDND();
	    
	    retrieveIfc();
	}
	
	private void retrieveIfc() {
		if(this.url == null) {
			// look if there is an IFC model available
			if(controller.getIfcModel() == null) {
				controller.retrieveLatestState(30000);
			} else { // only the view is enabled another time
				synchronized(url) {
					controller.retrieveLatestState(5000);
				}
			}
		}

	}
	
	public void createToolBar(){				
		toolBarManager.add(decolorizeAction);		
	}
	
	private void makeActions() {
//		FIXME funktioniert zur Zeit nicht!
		decolorizeAction = new Action() {

			public void run() {
				Collection<String> colorizedEntities = viewer.getColorizedEntities();
				viewer.unsetColorizedEntities(colorizedEntities);
			}
		};
		decolorizeAction.setText("Set default colors");
		decolorizeAction.setToolTipText("Set default colors");
//		ImageDescriptor imageDescriptor = AppearanceFactory.getInstance().getImageDescriptor(
//				Activator.PLUGIN_ID, Activator.ICONS_16x16_PATH+"viewmagfit.png");
//		decolorizeAction.setImageDescriptor(imageDescriptor);	
	}

	@Override
	public void setFocus() {
		if(viewer != null && !viewer.isDisposed()) {
			viewer.setFocus();
			retrieveIfc();
		}
	}

	private void initializeDND() {
		//		DND.DROP_MOVE | DND.DROP_COPY
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
		Transfer[] transferTypes = new Transfer[] { 
				TextTransfer.getInstance() 
		};

		// Create the drop target on the composite
		DropTarget dt = new DropTarget(viewer, operations);
		dt.setTransfer(transferTypes);
		dt.addDropListener(new IFCViewerDropListener(controller));
	}
	
	public void setIfcFile(final URL url) {
		this.url = url;
		if(viewer != null && !viewer.isDisposed()) {
			display.syncExec(
					new Runnable() {
						public void run(){
							viewer.setIfcURL(url);
						}
			});	
		}
	}
	
	public Collection<String> getSelection() {
		return selection;
	}
	
	public void setSelection(final Collection<String> s) {
		this.selection = s;	
		if(selection != null) {
			if(!viewer.isDisposed() && parent.isVisible()) {
			
				display.syncExec(
						  new Runnable() {
						    public void run(){
								viewer.selectItems(selection);
						    }
				});	
			}
		}
	}
	
	public void setTransparency(final Collection<String> guids, final boolean isTransparent) {
		if(guids != null) {
			if(!viewer.isDisposed() && parent.isVisible()) {

				display.syncExec(
						new Runnable() {
							public void run(){
								viewer.setTransparentEntities(guids, isTransparent);
							}
						});	
			}
		}
	}
	
	public void uncolorizeEntities(final Collection<String> guids) {
		if(guids != null) {
			if(!viewer.isDisposed() && parent.isVisible()) {

				display.syncExec(
						new Runnable() {
							public void run(){
								viewer.unsetColorizedEntities(guids);
							}
						});	
			}
		}
	}
	
	public void colorizeEntities(final Map<Color, Collection<String>> entities) {
		if(entities != null) {
			if(!viewer.isDisposed() && parent.isVisible()) {

				display.asyncExec(
						new Runnable() {
							public void run(){
								viewer.colorizeEntities(entities);
							}
						});	
			}
		}
	}
	
	/**
	 * @param guids GlobalId of IFC entities
	 * @param visible true: show the entities, false: hide the entities
	 */
	public void showOrHideEntities(final Collection<String> guids, final boolean visible) {
		if(guids != null) {
			if(!viewer.isDisposed() && parent.isVisible()) {

				display.syncExec(
						new Runnable() {
							public void run(){
								viewer.showOrHideEntities(guids, visible);
							}
						});	
			}
		}
	}
	
	public void hideIfcViewer(final boolean isHidden) {
		this.isHidden = isHidden;
		if(!viewer.isDisposed()) {
			display.syncExec(
					new Runnable() {
						public void run(){
							viewer.setIsHidden(isHidden);
					    }
					}
			);	
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(IfcViewer.PROP_SELECTION)) {
			this.selection = (Collection<String>) evt.getNewValue();
		}
	}

}
