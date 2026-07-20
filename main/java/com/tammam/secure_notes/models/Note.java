package com.tammam.secure_notes.models;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
@Table(name = "Note")
@Entity
public class Note {

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
    @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	private Long Id;
    @Lob
    private String content;
    
    private String ownerUserName;
    
   	public Note(Long id, String content, String ownerUserName) {
		super();
		Id = id;
		this.content = content;
		this.ownerUserName = ownerUserName;
	}




	public Note(Builder builder) {
		this.Id = builder.Id;
		this.content=builder.content;
		this.ownerUserName=builder.ownerUserName;
	}


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
    
	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		return Objects.equals(Id, other.Id);
	}

	public static final class Builder
	{
		//private static final Long Builder = null;
		private Long Id;
	    private String content;
	    private String ownerUserName;
	    
		public static Builder builder() {
			return new Builder();
		}

		private Builder() {
		}
		public Builder id (Long Id)
		{
		  this.Id =Id; 
		  return this;
		}
		public Builder content (String content)
		{
		  this.content=content;
		  return this;
		}
		public Builder ownerUserName (String ownerUserName)
		{
		  this.ownerUserName=ownerUserName;
		  return this;
		}
		
        public Note build()
        {
        	return new Note (this);
        }

}
}