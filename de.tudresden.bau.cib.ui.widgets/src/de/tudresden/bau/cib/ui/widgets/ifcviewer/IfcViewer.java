package de.tudresden.bau.cib.ui.widgets.ifcviewer;



import gm3d.CadObject;

import java.awt.Frame;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import openifctools.com.api.ifc3dviewer.Ifc3DViewer;
import openifctools.com.openifcjavatoolbox.ifc2x3tc1.IfcClass;
import openifctools.com.openifcjavatoolbox.ifc2x3tc1.IfcRoot;
import openifctools.com.openifcjavatoolbox.ifcmodel.IfcModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import sas.controller.ApplicationController;
import sas.model.applicationmodel.ApplicationModel;
import sas.model.selectionmodel.SelectionModelListener;
import de.tudresden.bau.cib.ui.widgets.Widget;



public class IfcViewer extends Widget implements IIfcViewer {

	private IfcModel ifcModel;
	private boolean isHidden = false;
	private ApplicationModel applicationModel;
	private List<String> currentSelection = new ArrayList<String>();
	private Map<String,CadObject> removedCadObjects = new HashMap<String,CadObject>();

	private Collection<String> transparentEntities;
	private Collection<String> hiddenEntities;
	private Collection<String> colorizedEntities;

	private SelectionModelListener selectionModelListener;
	private java.awt.Color defaultSelectionColor;
	private java.awt.Color selectionColor;
	private Map<String, Color> originalColorsForEntity = new HashMap<String, Color>();
	
	public static final String PROP_SELECTION = "selection";


	public IfcViewer(final Composite parent) {		
		super(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		init();
	}

	public void init() {		
		ifcModel = new IfcModel();
		applicationModel = new ApplicationModel();
		applicationModel.getSelectionModel().addSelectionType(IfcClass.class);
		applicationModel.getSelectionModel().addSelectionType(CadObject.class);
		ApplicationController appController = applicationModel.getApplicationController();
		defaultSelectionColor = selectionColor = appController.getSelectionColor();

		selectionModelListener = new SelectionModelListener(){

			@Override
			public void objectDeselected(Object deselected) {
				IfcRoot root = (IfcRoot) deselected;
				if(root != null) {
					String guid = root.getGlobalId().getDecodedValue();
					if(currentSelection.contains(guid)) {
						deselectItem(guid);
					}
				}
			}

			@Override
			public void objectSelected(Object selected) {
				IfcRoot root = (IfcRoot) selected;
				if(root != null) {
					String guid = root.getGlobalId().getDecodedValue();
					if(!currentSelection.contains(guid)) {
						selectItem(guid);
					}
				}
			}

			@Override
			public void objectsDeselected(Collection deselected) {
				// send the change to the client if it is not coming from it (then the collection is in a different state)
				boolean sendToClient = false;
				if(deselected != null && deselected.size() > 0) {
					Collection<String> guids = new HashSet<>();
					for(Object o : deselected) {
						IfcRoot root = (IfcRoot) o;
						if(root != null) {		
							String guid = root.getGlobalId().getDecodedValue();
							if(currentSelection.contains(guid)) {
								guids.add(guid);
								sendToClient = true;
							}
						}
					}
					if(sendToClient) {
						deselectItems(guids);
					}
				}
			}

			@Override
			public void objectsSelected(Collection selected) {
				// send the change to the client if it is not coming from it (then the collection is in a different state)
				boolean sendToClient = false;
				if(selected != null && selected.size() > 0) {
					Collection<String> guids = new HashSet<>();
					for(Object o : selected) {
						IfcRoot root = (IfcRoot) o;
						if(root != null) {	
							String guid = root.getGlobalId().getDecodedValue();
							if(!currentSelection.contains(guid)) {
								sendToClient = true;
								guids.add(guid);
							}
						}
					}
					if(sendToClient) {
						selectItems(guids);
					}
				}
			}

		};
		applicationModel.getSelectionModel().addSelectionModelListener(selectionModelListener);

        Frame frame3d = SWT_AWT.new_Frame(this);
        frame3d.add(new Ifc3DViewer(applicationModel));

//        Frame infoFrame = SWT_AWT.new_Frame(new Composite(splitter, SWT.EMBEDDED));
//        org.eclipse.swt.graphics.Color bg = parent.getBackground();
//        infoFrame.setBackground(new java.awt.Color(bg.getRed(), bg.getGreen(), bg.getBlue()));
//        JTabbedPane infoPane = new JTabbedPane(JTabbedPane.VERTICAL, JTabbedPane.SCROLL_TAB_LAYOUT);
//        infoPane.add(new IfcTypeView(applicationModel), "Type View");
//        infoPane.add(new IfcSpatialView(applicationModel), "Structure View");
//        infoPane.add(new IfcModelView(applicationModel), "IFC Model View");
//        infoFrame.add(infoPane);
	}
	
	public boolean isHidden() {
		checkWidget();
		return isHidden;
	}
	
	public void setIsHidden(boolean isHidden) {
		checkWidget();
		this.isHidden = isHidden;
	}

	@Override
	public void selectItems(Collection<String> guids) {
		final HashMap<String,IfcClass> ifcObjects = ifcModel.getIfcObjectsByIDs(currentSelection);
		if(ifcObjects != null) {
			Collection<String> oldSelection = new HashSet<String>(currentSelection);
			currentSelection.addAll(guids);
			applicationModel.getSelectionModel().select(selectionModelListener, ifcObjects.values());
			firePropertyChange(PROP_SELECTION, oldSelection, currentSelection);
		}
	}

	@Override
	public void clearSelection() {
		if(currentSelection != null) {
			Collection<String> oldSelection = new HashSet<String>(currentSelection);
			applicationModel.getSelectionModel().clearSelection(selectionModelListener, IfcClass.class);
			currentSelection.clear();
			firePropertyChange(PROP_SELECTION, oldSelection, currentSelection);
		}
	}

	@Override
	public void deselectItems(Collection<String> guids) {
		Collection<String> oldSelection = new HashSet<String>(currentSelection);
		final HashMap<String,IfcClass> ifcObjects = ifcModel.getIfcObjectsByIDs(guids);
		if(ifcObjects != null) {
			currentSelection.removeAll(guids);
			applicationModel.getSelectionModel().deselect(selectionModelListener, ifcObjects.values());
		}

		currentSelection.removeAll(guids);
		firePropertyChange(PROP_SELECTION, oldSelection, currentSelection);
	}

	@Override
	public Collection<String> getCurrentSelection() {
		return currentSelection;
	}

	@Override
	public void selectItem(String id) {
		Collection<String> oldSelection = new HashSet<String>(currentSelection);
		final HashMap<String,IfcClass> ifcObjects = ifcModel.getIfcObjectsByIDs(Arrays.asList(id));
		if(ifcObjects != null) {
			applicationModel.getSelectionModel().select(selectionModelListener, ifcObjects.values());
		}
		currentSelection.add(id);
		firePropertyChange(PROP_SELECTION, oldSelection, currentSelection);
	}

	@Override
	public void deselectItem(String id) {
		Collection<String> oldSelection = new HashSet<String>(currentSelection);
		currentSelection.remove(id);
		final HashMap<String,IfcClass> ifcObjects = ifcModel.getIfcObjectsByIDs(Arrays.asList(id));
		if(ifcObjects != null) {
			applicationModel.getSelectionModel().deselect(selectionModelListener, ifcObjects.values());
		}
		firePropertyChange(PROP_SELECTION, oldSelection, currentSelection);
	}

	public void resizeTo(int width, int height) {
		this.setSize(width, height);
	}
	
	public void restoreSelectionColor() {
		this.selectionColor = defaultSelectionColor;
		setSelectionColor(new Color(Display.getDefault(), 
				defaultSelectionColor.getRed(), defaultSelectionColor.getGreen(), defaultSelectionColor.getBlue()));
	}

	@Override
	public Color getSelectionColor() {
		java.awt.Color c = applicationModel.getApplicationController().getSelectionColor();
		return new Color(Display.getDefault(), c.getRed(), c.getGreen(), c.getBlue());
	}

	@Override
	public void setSelectionColor(Color color) {
		this.selectionColor = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
		applicationModel.getApplicationController().setSelectionColor(selectionColor);
	}

	@Override
	public void colorizeEntities(Map<Color, Collection<String>> colorizedEntities) {
		if(this.colorizedEntities == null) {
			this.colorizedEntities = new HashSet<>();
		} else {
			for (Map.Entry<Color, Collection<java.lang.String>> entry : colorizedEntities.entrySet()) {
				this.colorizedEntities.addAll(entry.getValue());
			}
		}

		for (Map.Entry<Color, Collection<java.lang.String>> entry : colorizedEntities.entrySet()) {
			Color key = entry.getKey();
			int red = key.getRed();
			int green = key.getGreen();
			int blue = key.getBlue();
			Collection<java.lang.String> guids = entry.getValue();

			for(String guid : guids) {
				@SuppressWarnings("unchecked")
				Collection<CadObject> cadObjects = applicationModel.getCadObjectsByGuid(Arrays.asList(guid));
				if(cadObjects != null && cadObjects.size() > 0) {
					//	System.out.println(cadObjects.size()+" CAD-3D-Objects found");

					for(CadObject cadObject : cadObjects) {
						@SuppressWarnings("unchecked")
						Vector<java.awt.Color> colors = cadObject.getColors();
						Iterator<java.awt.Color> colorIter = colors.iterator();
						// only put the GUId to the map if it is not already included (because if it is included the default color will be overwritten)
						if(!originalColorsForEntity.containsKey(guid) && colorIter.hasNext()) {
							java.awt.Color previousColor = colors.iterator().next();
							originalColorsForEntity.put(guid, new Color(Display.getDefault(), previousColor.getRed(), previousColor.getGreen(), previousColor.getBlue()));
						}
						// set new color
						cadObject.colorize(new java.awt.Color(red, green, blue));
					}
				}
			}		
		}
	}

	@Override
	public void showOrHideEntities(Collection<String> guids, boolean isVisible) {
		for(String guid : guids) {
			if(isVisible) {
				CadObject cadObject = removedCadObjects.remove(guid);
				if(cadObject != null) {
					cadObject.setVisible(isVisible);
				}
			} else {
				if(ifcModel.containsIfcObject(guid)) {
					@SuppressWarnings("unchecked")
					Collection<CadObject> cadObjects = applicationModel.getCadObjectsByGuid(Arrays.asList(guid));
					if(cadObjects != null && cadObjects.size() > 0) {
						for(CadObject cadObject : cadObjects) {
							cadObject.setVisible(isVisible);
							removedCadObjects.put(guid,cadObject);
							
							if(!isVisible) hiddenEntities.add(guid);
							else hiddenEntities.remove(guid);
						}						
					}
				}
			}
		}
	}
	
	@Override
	public Collection<String> getHiddenEntities() {
		return hiddenEntities;
	}

	@Override
	public void unsetColorizedEntities(Collection<String> colorizedEntities) {
		Map<Color, Collection<String>> map = new HashMap<Color, Collection<String>>();
		// make a defense copy
		for(String guid : new ArrayList<String>(colorizedEntities)) {
			if(originalColorsForEntity.containsKey(guid)) {
				Color color = originalColorsForEntity.get(guid);
				List<String> guids = Arrays.asList(guid);
				if(map.containsKey(color)) map.get(color).addAll(guids);
				else {
					map.put(color, new ArrayList<String>(guids));
				}
				this.colorizedEntities.remove(guid);
			}
		}
		colorizeEntities(map);
	}

	@Override
	public void setTransparentEntities(Collection<String> transparentEntities, boolean transparent) {
		this.transparentEntities = transparentEntities;
		@SuppressWarnings("unchecked")
		Collection<CadObject> cadObjects = applicationModel.getCadObjectsByGuid(transparentEntities);
		if(cadObjects != null && cadObjects.size() > 0) {
			for(CadObject cadObject : cadObjects) {
				cadObject.setTransparent(transparent);
			}
		}			
	}

	@Override
	public Collection<String> getTransparentEntities() {
		return transparentEntities;
	}

	@Override
	public void setIfcURL(URL path) {
		try	{
			ifcModel.readStepFile(path);
			applicationModel.setIfcModel(ifcModel);
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	public Collection<String> getColorizedEntities() {
		return colorizedEntities;
	}
}
