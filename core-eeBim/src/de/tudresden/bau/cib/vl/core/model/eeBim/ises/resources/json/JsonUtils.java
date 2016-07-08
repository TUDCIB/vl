package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.json;
//package de.tudresden.bau.cib.vl.core.resources.json;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.reflect.Type;
//
//import com.google.gson.Gson;
//
//import de.tudresden.bau.cib.vl.core.resources.dm.ClassifiableAnnotable;
//
//public class JsonUtils {
//
//	private static Gson gson = RegisterGsonAdaptors.getJson();
//	
//	public static ClassifiableAnnotable parseJsonToDM(InputStream jsonIS, Type type) throws JsonException
//	{	
//		BufferedReader isreader = null;
//		
//		if(gson == null){
//			throw new JsonException("gson parser == null");
//		}
//		isreader = new BufferedReader(new InputStreamReader(RsUtils.getCatchedInputStream(jsonIS)));
//		
//		return gson.fromJson(isreader, type);	
//	}
//	
//	public static <T> String parseDMToJson(T res, Type type) throws JsonException
//	{	
//	
//		if(gson == null){
//			throw new JsonException("gson parser == null");
//		}
//		return gson.toJson(res, type);
//
//	}
//
//	public static Gson getGson() {
//		return gson;
//	}
//
//}
