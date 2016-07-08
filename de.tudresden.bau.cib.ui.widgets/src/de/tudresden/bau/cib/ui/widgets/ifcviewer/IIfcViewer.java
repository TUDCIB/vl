package de.tudresden.bau.cib.ui.widgets.ifcviewer;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.eclipse.swt.graphics.Color;

public interface IIfcViewer {

	void colorizeEntities(Map<Color, Collection<String>> colorizedEntities);

	void showOrHideEntities(Collection<String> guids, boolean visible);

	void unsetColorizedEntities(Collection<String> colorizedEntities);

	void setTransparentEntities(Collection<String> transparentEntities,
			boolean isTransparent);

	Collection<String> getTransparentEntities();

	void setSelectionColor(Color color);

	Color getSelectionColor();

	void setIfcURL(URL ifcURL);

	void deselectItems(Collection<String> guids);

	void selectItems(Collection<String> guids);

	void clearSelection();

	void deselectItem(String id);

	Collection<String> getCurrentSelection();

	void selectItem(String id);

	Collection<String> getHiddenEntities();

}
