package jpa_xslt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SiteDao {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("assignment6");
	EntityManager em = null;

	public Site findSite(int siteId) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Site site = em.find(Site.class, siteId);
		em.getTransaction().commit();
		em.close();
		return site;
	}

	@SuppressWarnings("unchecked")
	public List<Site> findAllSites() {
		List<Site> sites = new ArrayList<Site>();
		em = factory.createEntityManager();
		try {
			em.getTransaction().begin();
			sites = em.createNamedQuery("findAllSites").getResultList();
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return sites;
	}

	public void exportSiteDatabaseToXmlFile(SiteList siteList,
			String xmlFileName) {
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(siteList, xmlFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void convertXmlFileToOutputFile(String inputXmlFileName,
			String outputXmlFileName, String xsltFileName) {
		File inputXmlFile = new File(inputXmlFileName);
		File outputXmlFile = new File(outputXmlFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SiteDao dao = new SiteDao();
		Site site = dao.findSite(1);
		System.out.println(site.getName());

		List<Site> sites = dao.findAllSites();
		for (Site s : sites) {
			System.out.println(s.getName());
		}
		
		SiteList theSites = new SiteList();
		theSites.setDirectors(sites);
		
		dao.exportSiteDatabaseToXmlFile(theSites, "xml/sites.xml");
		
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
	}

}
