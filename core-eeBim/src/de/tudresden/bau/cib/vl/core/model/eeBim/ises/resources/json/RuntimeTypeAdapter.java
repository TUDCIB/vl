package de.tudresden.bau.cib.vl.core.model.eeBim.ises.resources.json;

/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;



public  class RuntimeTypeAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
  private final Class<?> baseType;
  private final String typeFieldName;
  private final Map<String, Class<?>> labelToSubtype = new LinkedHashMap<String, Class<?>>();
  private final Map<Class<?>, String> subtypeToLabel = new LinkedHashMap<Class<?>, String>();

  public RuntimeTypeAdapter(Class<?> baseType, String typeFieldName) {
    //if (typeFieldName == null || baseType == null) {
	if ( baseType == null) {
	       throw new NullPointerException();
    }
    this.baseType = baseType;
    this.typeFieldName = typeFieldName;
  }

  /**
   * Creates a new runtime type adapter for {@code c} using {@code "type"} as
   * the type field name.
   */
  public  <T> RuntimeTypeAdapter<T> create(Class<T> c) {
    return create(c, "type");
  }

  /**
   * Creates a new runtime type adapter using for {@code c} using {@code
   * typeFieldName} as the type field name. Type field names are case sensitive.
   */
  public  <T> RuntimeTypeAdapter<T> create(Class<T> c, String typeFieldName) {
    return new RuntimeTypeAdapter<T>(c, typeFieldName);
  }

  /**
   * Registers {@code type} identified by {@code label}. Labels are case
   * sensitive.
   *
   * @throws IllegalArgumentException if either {@code type} or {@code label}
   *     have already been registered on this type adapter.
   */
  public RuntimeTypeAdapter<T> registerSubtype(Class<? extends T> type, String label) {
    if (type == null || label == null) {
      throw new NullPointerException();
    }
    if (subtypeToLabel.containsKey(type) || labelToSubtype.containsKey(label)) {
      throw new IllegalArgumentException("types and labels must be unique");
    }
    labelToSubtype.put(label, type);
    subtypeToLabel.put(type, label);
    return this;
  }

  /**
   * Registers {@code type} identified by its {@link Class#getSimpleName simple
   * name}. Labels are case sensitive.
   *
   * @throws IllegalArgumentException if either {@code type} or its simple name
   *     have already been registered on this type adapter.
   */
  public RuntimeTypeAdapter<T> registerSubtype(Class<? extends T> type) {
    return registerSubtype(type, type.getSimpleName());
  }

  public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
    Class<?> srcType = src.getClass();
 
    String label = subtypeToLabel.get(srcType);
    if (label == null) {
      throw new JsonParseException("cannot serialize " + srcType.getName()
          + "; did you forget to register a subtype?");
    }
    JsonElement serialized = context.serialize(src, srcType);
    final JsonObject jsonObject = serialized.getAsJsonObject();
    if (jsonObject.has(typeFieldName)) {
      throw new JsonParseException("cannot serialize " + srcType.getName()
          + " because it already defines a field named " + typeFieldName);
    }
    JsonObject clone = new JsonObject();
    clone.add(typeFieldName, new JsonPrimitive(label));
    for (Map.Entry<String, JsonElement> e : jsonObject.entrySet()) {
      clone.add(e.getKey(), e.getValue());
    }
    return clone;
  }

  public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)throws JsonParseException 
      {
	  JsonElement labelJsonElement = null;
	  
	  
	  labelJsonElement = (JsonPrimitive)json.getAsJsonObject().remove(typeFieldName);
	  if (labelJsonElement == null) {
		  return null;
		  //throw new JsonParseException("cannot deserialize " + typeOfT
		//		  + " because it does not define a field named " + typeFieldName);
	  }
	  
	  
	  String label = labelJsonElement.getAsString();
	  Class<?> subtype = labelToSubtype.get(label);
	  if (subtype == null) {
		  throw new JsonParseException("cannot deserialize " + baseType + " subtype named "
				  + label + "; did you forget to register a subtype?");
	  }
	  @SuppressWarnings("unchecked") // registration requires that subtype extends T
	  T result = (T) context.deserialize(json, subtype);
	  return result;
  }
  
  private void printlabelToSubtype()
  {
	  for(Map.Entry<String, Class<?>> entry:labelToSubtype.entrySet())
	  {
	  System.out.println(entry.getKey()+":"+entry.getValue());
	  }
  }
  private void printtostring(JsonObject jo )
  {
	  for(Map.Entry<String, JsonElement> entry:jo.entrySet())
	  {
	  System.out.println(entry.getKey());
	  }
	  
 }
}
