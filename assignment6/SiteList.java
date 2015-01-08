package jpa_xslt;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SiteList {
	@XmlElement(name="site")
	private List<Site> sites;

	public List<Site> getDirectors() {
		return sites;
	}
	public void setDirectors(List<Site> directors) {
		this.sites = directors;
	}
	public SiteList(List<Site> sites) {
		super();
		this.sites = sites;
	}
	public SiteList() {
		super();
	}
}