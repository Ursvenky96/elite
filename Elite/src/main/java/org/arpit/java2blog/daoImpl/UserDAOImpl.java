package org.arpit.java2blog.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.arpit.java2blog.dao.UserDAO;
import org.arpit.java2blog.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<User> getAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = new ArrayList<>();
		userList = (List<User>) session.createQuery("from User").list();

		return userList;
	}

	public User getUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, new Integer(id));
		return user;
	}

	public User addUser(User user) {
		return user;

	}

	public User getUserByMobilenumberAndPassword(String mobileNumber, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("mobileNumber", mobileNumber));
		cr.add(Restrictions.eq("password", password));

		return (User) cr.uniqueResult();

	}
	public User getUserByMobilenumberAndPassword(String mobileNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("mobileNumber", mobileNumber));
		
		return (User) cr.uniqueResult();	
	}

	@Override
	public void forgetPassword(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);		
	}

}
