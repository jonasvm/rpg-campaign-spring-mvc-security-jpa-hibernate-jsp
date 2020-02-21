package br.com.sdevlab.rpgcampaign.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Campaign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String master;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "campaign_user")
	private List<User> listOfUsers = new ArrayList<User>();

//	@ElementCollection
//	private List<User> listOfUsers = new ArrayList<User>();

	@ElementCollection
	private List<RPGCharacter> listofNpcs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public List<User> getListOfUsers() {
		return listOfUsers;
	}

	public void setListOfUsers(List<User> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	public List<RPGCharacter> getListofNpcs() {
		return listofNpcs;
	}

	public void setListofNpcs(List<RPGCharacter> listofNpcs) {
		this.listofNpcs = listofNpcs;
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", name=" + name + ", master=" + master + ", listOfUsers=" + listOfUsers
				+ ", listofNpcs=" + listofNpcs + "]";
	}

	public void addPlayerToCampaign(User user) {
		this.listOfUsers.add(user);
	}

}
