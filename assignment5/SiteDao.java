package jpa_jws;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/site")
public class SiteDao {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("assignment5");
	EntityManager em = null;

	// /api/site/ID
	@GET
	@Produces("application/json")
	@Path("/{ID}")
	public Site findSite(@PathParam("ID") int siteId) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Site site = em.find(Site.class, siteId);
		em.getTransaction().commit();
		em.close();
		return site;
	}

	// /api/site
	@GET
	@Produces("application/json")
	@Path("/")
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

	// /api/site/ID
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/{site}/{ID}")
	public List<Site> updateSite(@PathParam("ID") int siteId, Site site) {
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();

		try {
			site.setId(siteId);
			em.merge(site);

			sites = em.createNamedQuery("findAllSites").getResultList();

			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}

		return sites;

	}

	// /api/site
	@DELETE
	@Produces("application/json")
	@Path("/{ID}")
	public List<Site> removeSite(@PathParam("ID") int siteId) {
		List<Site> sites = new ArrayList<Site>();

		em = factory.createEntityManager();
		em.getTransaction().begin();
		try {
			Site site = em.find(Site.class, siteId);

			em.remove(site);
			sites = em.createNamedQuery("findAllSites").getResultList();

			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}

		return sites;
	}

	// /api/site
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/")
	public List<Site> createSite(Site site) {
		List<Site> sites = new ArrayList<Site>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(site);
			sites = em.createNamedQuery("findAllSites").getResultList();

			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}

		return sites;
	}

	public static void main(String[] args) {
		SiteDao dao = new SiteDao();

		Site s1 = new Site("ccc", 0.5, 0.5);
		dao.createSite(s1);
		Site s2 = new Site("ddd", 9.5, 6.5);
		Site s3 = new Site("eee", 7.5, 6.5);
		Site s4 = new Site("fff", 9.5, 6.5);
		dao.createSite(s2);
		dao.createSite(s4);

		dao.removeSite(1);
		dao.updateSite(2, s3);
		dao.findAllSites();

		List<Site> sites = dao.findAllSites();
		for (Site s : sites) {
			System.out.println(s.getName());
		}
	}

}
