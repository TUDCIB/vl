//package de.tudresden.bau.cib.vl.gui.simulation.energy.view;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.TabFolder;
//
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.ClimateTabItem;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.ConstructionTypeTabItem;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.SpaceTypeTabItem;
//
//public class TemplatesTabFolder extends TabFolder{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 6348977089277054897L;
////	private TemplateViewController templateViewController;
//	
//
////	private final TabFolder tabFolder;
//	private ClimateTabItem tabItemClimateLocation;
//	private SpaceTypeTabItem tabItemSpaceType;
//	private ConstructionTypeTabItem tabItemConstructionType;
//	
//
//	public TemplatesTabFolder(Composite parent, int style) {
//		super(parent, style);	
//	
////		########## Create Tabs #########
////	    Climate Location Templates
//		tabItemClimateLocation = new ClimateTabItem(this, SWT.NONE);
//		
////	    Usage Templates
//		tabItemSpaceType = new SpaceTypeTabItem(this, SWT.NONE);
//		
////		Construction Templates
//		tabItemConstructionType = new ConstructionTypeTabItem(this, SWT.NONE);
//	    
//		
//		this.addSelectionListener(new SelectionListener() {
//			
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -7427229675491873870L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {			
//				showTemplates();
//			}
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});
//	    
////	    this.setSize(WIDTH, HEIGHT);	    
//	    
////	    default view
//	    this.setSelection(0);
//	    tabItemClimateLocation.createContent(this);
//	}
//
//	public TemplatesTabFolder(Composite parent) {
//		this(parent, SWT.NONE);
//	}
//	
//	private void showTemplates() {
//		int index = this.getSelectionIndex();
//		switch(index) {
//		case 0: 
//			tabItemClimateLocation.createContent(this);
//			break;
//		case 1: 
//			tabItemSpaceType.createContent(this);
//			break;		
//		case 2: 
//			tabItemConstructionType.createContent(this);
//			break;
//		
//		default: break;
//		
//		}
//		
//	}
//}
