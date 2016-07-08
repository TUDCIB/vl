package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.ClassifiableAnnotable;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.Eeentity;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.Eeprofile;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.Eetemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.ResourceDescriptor;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.StdDatabaseDescriptor;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.StdFileDescriptor;
import de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.dm.StdServiceDescriptor;




public class RegisterGsonAdaptors {
	
	public static Gson getJson() {

		RuntimeTypeAdapter<ClassifiableAnnotable> CAadaptor = new RuntimeTypeAdapter(ClassifiableAnnotable.class, "ClassifiableAnnotable");
			CAadaptor.registerSubtype(Eetemplate.class );
			CAadaptor.registerSubtype(Eeprofile.class);
			CAadaptor.registerSubtype(Eeentity.class);
	
		RuntimeTypeAdapter<ResourceDescriptor> RDadaptor = new RuntimeTypeAdapter(ResourceDescriptor.class, "ResourceDescriptor");
			RDadaptor.registerSubtype(StdDatabaseDescriptor.class);
			RDadaptor.registerSubtype(StdFileDescriptor.class);
			RDadaptor.registerSubtype(StdServiceDescriptor.class);
			
		
		Gson gson = new GsonBuilder() 
			.setPrettyPrinting() 
			.disableHtmlEscaping() 
		 	.registerTypeAdapter(ClassifiableAnnotable.class, CAadaptor)
			.registerTypeAdapter(ResourceDescriptor.class, RDadaptor)
			.create(); 
		return gson; 
	}
}
