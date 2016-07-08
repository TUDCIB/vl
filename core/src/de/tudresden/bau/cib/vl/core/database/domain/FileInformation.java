package de.tudresden.bau.cib.vl.core.database.domain;

import java.io.Serializable;
import java.net.URL;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public class FileInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8118600889381620916L;
	
	
	public enum TYPE
	{
		UNKNOWN,
		DIRECTORY,
		IFC,
		X3D,
		RDF,
		TAR,
		ZIP,
		GZ,
		JPG,
		GIF,
		PNG,
		SIMMATRIX,
		TXT,
		NANDRAD // Nandrad simulation model file
		;
		
		public static TYPE findByAbbreviation(String name) {
			TYPE[] values = values();
			for(TYPE value : values) {
				if(value.name().equalsIgnoreCase(name)) return value;
			}
			return TYPE.UNKNOWN;
		}
	}
	
	
	private Integer id;
	private String name;
	private String filePath;
	private URL url;
	private Integer ownerId;
	private TYPE fileType;
	
	
    public TYPE getFileType() {
		return fileType;
	}

	public void setFileType(TYPE fileType) {
		this.fileType = fileType;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FI_ID")
    public Integer getId() {
    	return id;
    }

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;	
		
	}
	
	@Override
	public int hashCode() {
		return 87+System.identityHashCode(this) + id;
	}
	
	@Override
	public boolean equals(Object obj) {
		FileInformation filePath = (FileInformation) obj;
		return this.hashCode() == filePath.hashCode() && filePath.filePath.equals(this.filePath);
	}
	
	@Override
	public String toString() {
		return id +":"+filePath;
	}

	/**
	 * @return The user id which is the owner of the file.
	 */
	public Integer getOwnerId() {
		return ownerId;
	}

	/**
	 * Sets the owner of the file.
	 * @param ownerId
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

}
