package br.com.sdevlab.rpgcampaign.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.sdevlab.rpgcampaign.models.CharacterType;
import br.com.sdevlab.rpgcampaign.models.RPGCharacter;
import br.com.sdevlab.rpgcampaign.models.Role;
import br.com.sdevlab.rpgcampaign.models.User;

@Repository
public class UserDAO implements UserDetailsService {

	// antes o @Transactional era aqui, mas foi removido pq não funcionou com o
	// Spring Security
	// mudei o transactional para o usercontroller
	@PersistenceContext
	private EntityManager manager;

	public void createUser(User user) {

		RPGCharacter character = new RPGCharacter();
		character.setArmor(1);
		character.setName("teste");
		character.setPoints(1);
		character.setStrength(1);
		character.setDexterity(1);
		character.setEndurance(1);
		character.setArmor(1);
		character.setFirePower(1);
		character.setLevel(1);
		character.setHitPoints(1);
		character.setMagicPoints(1);
		character.setType(CharacterType.PLAYABLE);

		List<RPGCharacter> characters = new ArrayList<RPGCharacter>();
		characters.add(character);
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		roles.add(role);

		user.setCharacters(characters);
		user.setRoles(roles);

		manager.persist(user);
	}

	public List<User> listUsers() {
		return manager.createQuery("select u from User u", User.class).getResultList();
	}

	public User findByEmail(String email) {
		return manager.createQuery("select distinct(u) from User u join fetch u.characters c where u.email = :email ",
				User.class).setParameter("email", email).getSingleResult();
	}

	public User findById(String email) {
		return manager.find(User.class, email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return manager.createQuery("select distinct(u) from User u join fetch u.characters c where u.email = :email ",
				User.class).setParameter("email", email).getSingleResult();
	}

}
