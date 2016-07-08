package de.tudresden.bau.cib.vl.gui.simulation.energy;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.tudresden.bau.cib.vl.gui.core.view.UserProjectView;

public class SimulationPerspective implements IPerspectiveFactory {
	
	enum FolderIdentifiers {
//		TOP ("de.tudresden.bau.cib.vl.gui.simulation.energy.view.top"),
		MAIN ("de.tudresden.bau.cib.vl.gui.simulation.energy.view.main"),
		BOTTOM ("de.tudresden.bau.cib.vl.gui.simulation.energy.view.bottom"),
		BOTTOM_RIGHT ("de.tudresden.bau.cib.vl.gui.simulation.energy.view.bottom_right"),
		BOTTOM_LEFT ("de.tudresden.bau.cib.vl.gui.simulation.energy.view.bottom_left"),
		TOP_LEFT ("de.tudresden.bau.cib.vl.gui.simulation.energy.view.top_left")
		;
		
		private String id;
		
		private FolderIdentifiers(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}
	}
	
	enum PlaceHolders {
//		TOP ("**.simulation.energy.view.top.*"),
		MAIN ("**.simulation.energy.view.main.*"),
		BOTTOM ("**.simulation.energy.view.bottom.*"),
		BOTTOM_RIGHT ("**.simulation.energy.view.bottom_right.*"),
		BOTTOM_LEFT ("**.simulation.energy.view.bottom_left.*"),
		TOP_LEFT ("**.simulation.energy.view.top_left.*")
		;
		
		private String id;
		
		private PlaceHolders(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}
	}

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(false);

		
		// create folders (create the main folder first as a reference)
//		IFolderLayout topFolder = layout.createFolder(FolderIdentifiers.TOP.getId(), IPageLayout.LEFT, 0.95f, layout.getEditorArea());
//		IFolderLayout mainFolder = layout.createFolder(FolderIdentifiers.MAIN.getId(), IPageLayout.BOTTOM, (float) 0.10f, FolderIdentifiers.TOP.getId());

		IFolderLayout mainFolder = layout.createFolder(FolderIdentifiers.MAIN.getId(), IPageLayout.LEFT, 0.95f, layout.getEditorArea());
		
		IFolderLayout projectFolder = layout.createFolder(FolderIdentifiers.TOP_LEFT.getId(), IPageLayout.LEFT, (float) 0.2, FolderIdentifiers.MAIN.getId());
		//IFolderLayout bottomFolder = layout.createFolder(FolderIdentifiers.BOTTOM.getId(), IPageLayout.BOTTOM, 0.7f, FolderIdentifiers.MAIN.getId());
		IFolderLayout leftBottomFolder = layout.createFolder(FolderIdentifiers.BOTTOM_LEFT.getId(), IPageLayout.BOTTOM, 0.7f, FolderIdentifiers.TOP_LEFT.getId());
		//IFolderLayout rightBottomFolder = layout.createFolder(FolderIdentifiers.BOTTOM_RIGHT.getId(), IPageLayout.RIGHT, (float) 0.7, FolderIdentifiers.BOTTOM.getId());
		
		// top
//		topFolder.addPlaceholder(PlaceHolders.TOP.getId());
		
//		main
		//mainFolder.addView(EnergyResultView.ID);
		mainFolder.addPlaceholder(PlaceHolders.MAIN.getId());
//		left
		projectFolder.addView(UserProjectView.ID);
		projectFolder.addPlaceholder(PlaceHolders.TOP_LEFT.getId());
//		bottom
		//bottomFolder.addPlaceholder(PlaceHolders.BOTTOM.getId());
//		bottomFolder.addView(SimulationManagementView.ID);
		
		leftBottomFolder.addPlaceholder(PlaceHolders.BOTTOM_LEFT.getId());
		
		//rightBottomFolder.addView(LogView.ID);
		//rightBottomFolder.addPlaceholder(PlaceHolders.BOTTOM_RIGHT.getId());
	}

}
