package br.com.sdevlab.rpgcampaign.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sdevlab.rpgcampaign.daos.UserDAO;
import br.com.sdevlab.rpgcampaign.models.User;
import br.com.sdevlab.rpgcampaign.validation.UserValidation;

@Controller
@Transactional
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserDAO userDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UserValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(User user) {
		return new ModelAndView("users/form");
	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value = "users", allEntries = true)
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return form(user);
		}
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		user.setPassword(b.encode(user.getPassword()));
		userDao.createUser(user);
		redirectAttributes.addFlashAttribute("msg_success",
				"Usuário " + user.getName() + " - " + user.getEmail() + " cadastrado com sucesso! ");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(method = RequestMethod.GET)
	@Cacheable(value = "users")
	public ModelAndView users() {
		List<User> users = userDao.listUsers();
		ModelAndView modelAndView = new ModelAndView("users/users");
		modelAndView.addObject("users", users);

		return modelAndView;
	}

	@RequestMapping("/details/{email}")
	public ModelAndView details(@PathVariable("email") String email) {
		ModelAndView modelAndView = new ModelAndView("users/details");
		User user = userDao.findByEmail(email);
		modelAndView.addObject("user", user);
		return modelAndView;
	}

}
