package jpa_xslt;

import java.io.Serializable;
import java.lang.String;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * Entity implementation class for Entity: Site
 *
 */
@Entity
@NamedQuery(name="findAllSites", query="SELECT s FROM Site s")
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Site implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private double latitude;
	@XmlAttribute
	private double longitude;
	
	//bi-directional many-to-one association to Tower
	@OneToMany(mappedBy = "site", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
	@XmlElement(name="tower")
	private List<Tower> towers;
	
	private static final long serialVersionUID = 1L;

	public Site() {
		super();
	}   
	
	public Site(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude =longitude;
	}
	
	public Site(String name, double latitude, double longitude,
			List<Tower> towers) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.towers = towers;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}   
	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public List<Tower> getTowers() {
		return this.towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

//	public Tower addTower(Tower tower) {
//		getTowers().add(tower);
//		tower.setSite(this);
//
//		return tower;
//	}
//
//	public Tower removeTower(Tower tower) {
//		getTowers().remove(tower);
//		tower.setSite(null);
//
//		return tower;
//	}
   
}
