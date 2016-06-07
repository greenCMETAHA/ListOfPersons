package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import beans.interfaces.InterfacePosition;
import utils.Utils;

@Entity
@Table(name = "position")
public class Position implements InterfacePosition{
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name")
	@Size(min=0, max=200)
	private String name;
	
	public Position() {
		name=Utils.EMPTY;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
