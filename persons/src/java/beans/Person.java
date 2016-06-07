package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import beans.interfaces.InterfacePerson;
import utils.Utils;

@Entity
@Table(name = "person")
public class Person implements InterfacePerson{
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name")
	@Size(min = 0, max = 200)
	private String name;

	@Column(name = "surname")
	@Size(min = 0, max = 200)
	private String surname;

	@ManyToOne(fetch = FetchType.EAGER, optional=true)
	private Position position;

	@ManyToOne(fetch = FetchType.EAGER, optional=true)
	private Department department;
	
	public Person() {
		super();
		name=Utils.EMPTY;
		surname=Utils.EMPTY;
		position=new Position();
		department=new Department();
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
