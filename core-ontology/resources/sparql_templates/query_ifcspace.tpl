## STATIC PREFIXES
PREFIX owl: <http://www.w3.org/2002/07/owl> 
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns> 
PREFIX ifcOWL: <http://openeebim.bau.tu-dresden.de/dev/ontology/BIMOnto>
## DYNAMIC PREFIXES
#if ($namespaces != null)
	#foreach ($ns in $namespaces)
	PREFIX m$foreach.count: $ns
	#end
#end
## SELECT
SELECT ?guid ?name ?longName ?ownerhistory" +
WHERE {
		?x		a				ifcOWL:IfcSpace ;
								ifcOWL:Name 		?lblName ;
								ifcOWL:LongName		?lblLongName ;
								ifcOWL:Description	?txtDescClass ;
								ifcOWL:GlobalId 	?guidClass .
			   ?lblName			ifcOWL:StringValue	?name .
			   ?lblLongName 	ifcOWL:StringValue 	?longName .
			   ?guidClass		ifcOWL:StringValue	?guid .	
			   ?txtDescClass	ifcOWL:StringValue	\""+nameCriteria+"\" .
	  }";