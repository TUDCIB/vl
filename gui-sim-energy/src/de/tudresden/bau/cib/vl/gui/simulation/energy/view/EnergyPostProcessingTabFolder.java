//package de.tudresden.bau.cib.vl.gui.simulation.energy.view;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.TabFolder;
//
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.CoolingTabItem;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.FinalEnergyCostsTabItem;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.GreenhouseGasEmissionTabItem;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.tabs.HeatingTabItem;
//
//public class EnergyPostProcessingTabFolder extends TabFolder{
//	
//
//	/**
//	 * 
//	 */
//	private static final long 				serialVersionUID 		= -1904928089168699267L;
//	private HeatingTabItem 					heatingTabItem;
//	private CoolingTabItem 					coolingTabItem;
//	private GreenhouseGasEmissionTabItem	greenhouseGasEmissionTabItem;
////	private SocioCultureValueTabItem 		socioCultureValueTabItem;
//	private FinalEnergyCostsTabItem				finalEnergyTabItem;
//	
//
//	public EnergyPostProcessingTabFolder(Composite parent, int style) {
//		super(parent, style);	
//	
////		########## Create Tabs #########
////		eKPI 1.1a
//		heatingTabItem 					= 	new HeatingTabItem(this, SWT.NONE);
////		eKPI eKPI 1.2
//		coolingTabItem 					= 	new CoolingTabItem(this, SWT.NONE);
////		eKPI 2.1
//		greenhouseGasEmissionTabItem	=	new GreenhouseGasEmissionTabItem(this, SWT.NONE);
////		eKPI 3.1
////		socioCultureValueTabItem 		= 	new SocioCultureValueTabItem(this, SWT.NONE);
////		eKPI 6.1 & 6.2 & 6.3
//		finalEnergyTabItem 				= 	new FinalEnergyCostsTabItem(this, SWT.NONE);
//		
//		this.addSelectionListener(new SelectionListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 647476142897639598L;
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {			
//				showTabs();
//			}
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				widgetSelected(e);
//			}
//		});    
//	    
////	    default view
////	    this.setSelection(0);
//	    heatingTabItem.createContent(this);
//	}
//
//	public EnergyPostProcessingTabFolder(Composite parent) {
//		this(parent, SWT.NONE);
//	}
//	
//	private void showTabs() {
//		int index = this.getSelectionIndex();
//		switch(index) {
//		case 0: 
//			heatingTabItem.createContent(this);
//			break;
//		case 1: 
//			coolingTabItem.createContent(this);
//			break;
//		case 2: 
//			greenhouseGasEmissionTabItem.createContent(this);
//			break;		
////		case 3: 
////			socioCultureValueTabItem.createContent(this);
////			break;	
//		case 3: 
//			finalEnergyTabItem.createContent(this);
//			break;	
//		default: break;		
//		}
//	}
//}
