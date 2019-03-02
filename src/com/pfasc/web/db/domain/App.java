package com.pfasc.web.db.domain;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @version 1.1 $
 * @author cg48910 $
 *
 */

@XmlRootElement
public class App {

	private int id;
	private String name;

	/**
	 *
	 */
	public App() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
}

