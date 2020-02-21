package br.com.sdevlab.rpgcampaign.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sdevlab.rpgcampaign.daos.CampaignDao;
import br.com.sdevlab.rpgcampaign.daos.UserDAO;
import br.com.sdevlab.rpgcampaign.models.Campaign;
import br.com.sdevlab.rpgcampaign.models.User;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/campaigns")
public class CampaignController {

	@Autowired
	private CampaignDao campaignDao;

	@Autowired
	private UserDAO userDao;

	@RequestMapping("/form")
	public ModelAndView form(Campaign campaign) {

		List<User> users = userDao.listUsers();
		ModelAndView modelAndView = new ModelAndView("campaigns/form");
		modelAndView.addObject("users", users);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value = "campaigns", allEntries = true)
	public ModelAndView createCampaign(Campaign campaign, RedirectAttributes redirectAttributes) {
		campaignDao.createCampaign(campaign);
		redirectAttributes.addFlashAttribute("msg_success",
				"Campanha " + campaign.getName() + " - " + campaign.getMaster() + " cadastrada com sucesso! ");
		return new ModelAndView("redirect:campaigns");
	}

	@RequestMapping(method = RequestMethod.GET)
	@Cacheable(value = "campaigns")
	public ModelAndView campaigns() {
		List<Campaign> campaigns = campaignDao.listCampaigns();
		ModelAndView modelAndView = new ModelAndView("campaigns/campaigns");
		modelAndView.addObject("campaigns", campaigns);

		return modelAndView;
	}

	@RequestMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("campaigns/details");
		Campaign campaign = campaignDao.findById(id);
		List<User> users = userDao.listUsers();
		modelAndView.addObject("campaign", campaign);
		modelAndView.addObject("users", users);
		return modelAndView;
	}

	@RequestMapping("/add/{id}")
	public ModelAndView add(@PathVariable("id") Integer id, String user_email, RedirectAttributes redirectAttributes) {
		Campaign campaignTarget = campaignDao.findById(id);
		Campaign campaign = new Campaign();
		campaign.setId(id);
		campaign.setName(campaignTarget.getName());
		campaign.setMaster(campaignTarget.getMaster());
		User user = userDao.findById(user_email.split(",")[1]);
		
		if(campaignTarget.getListOfUsers() != null) {
			campaign.setListOfUsers(campaignTarget.getListOfUsers());
		}
		campaign.addPlayerToCampaign(user);
		
		campaignDao.updateCampaign(campaign);
		redirectAttributes.addFlashAttribute("msg_success",
				"Usuário " + user.getName() + " - " + user.getEmail() + " adicionado a campanha com sucesso! ");
		ModelAndView modelAndView = new ModelAndView("campaigns/campaigns");
		modelAndView.addObject("campaigns", campaignDao.listCampaigns());
		return modelAndView;
	}

}
