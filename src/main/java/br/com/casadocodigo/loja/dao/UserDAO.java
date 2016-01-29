package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.SystemUser;
import br.com.casadocodigo.loja.models.User;



@Repository
public class UserDAO implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		String jpql = "select u from User u where u.login = :login";
		List<User> users = em.createQuery(jpql,User.class).setParameter("login", username).getResultList();
		if(users.isEmpty()){
			System.out.println("El usuario No existe");
			throw new UsernameNotFoundException("O usuario "+username+" nÃ£o existe");
			
		}
		System.out.println("El usuario es "+users.get(0));
		User user = users.get(0);
		System.out.println(user.getPassword());
		return users.get(0);
	}

}