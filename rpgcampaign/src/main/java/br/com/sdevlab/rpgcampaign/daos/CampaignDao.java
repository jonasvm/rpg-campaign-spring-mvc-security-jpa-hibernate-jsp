package br.com.sdevlab.rpgcampaign.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sdevlab.rpgcampaign.models.Campaign;

@Repository
@Transactional
public class CampaignDao {

	@PersistenceContext
	private EntityManager manager;

	public void createCampaign(Campaign campaign) {
		manager.persist(campaign);
	}

	public List<Campaign> listCampaigns() {
		return manager.createQuery("select c from Campaign c", Campaign.class).getResultList();
	}

	public Campaign findById(Integer id) {
		return manager.find(Campaign.class, id);
	}

	public void updateCampaign(Campaign campaign) {
		manager.merge(campaign);
	}

	public Campaign findByIdLazy(Integer id) {
		return manager.createQuery("select distinct(c) from Campaign c join fetch c.listOfUsers u where c.id = :id ",
				Campaign.class).setParameter("id", id).getSingleResult();
	}

}
