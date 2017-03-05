package com.neu.edu.wtp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;

//import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;

import com.neu.edu.wtp.pojo.User;
//import com.neu.edu.wtp.pojo.UserType;
import com.neu.edu.wtp.pojo.UserType;
import com.neu.edu.wtp.utility.PasswordEncryption;

public class UserDAO extends DAO {

	public UserDAO() {

	}
	
	//Is username unique?
	public boolean isUsernameUnique(String userName){
		try{
			Criteria crit = getSession().createCriteria(User.class);
			 Criterion username = Restrictions.eq("userName", userName);
			 crit.add(username);
			 begin();
			 //User user=(User) crit.uniqueResult();
			return true;
		}catch(NonUniqueResultException ne){
			ne.printStackTrace();
			return false;
		}
	}

	// create customer user
	public User create(String userName, String password, String emailId) {

		try {
			begin();
		
			User user = new User(userName, password);
			user.setUserType(UserType.Customer);
			user.setEmailId(emailId);
			getSession().save(user);
			commit();
			close();
			return user;
		} catch (HibernateException he) {
			rollback();
		}
		return null;

	}
	//update user
	public boolean updateUser(User user,long id){
		try{
			User u=getUserByUserId(id);
			u.setDateOfBirth(user.getDateOfBirth());
			u.setEmailId(user.getEmailId());
			u.setFirstName(user.getEmailId());
			u.setLastName(user.getLastName());
			u.setGender(user.getGender());
			u.setPassword(user.getPassword());
			begin();
			getSession().update(u);
			commit();
			close();
			return true;
		}catch(HibernateException he){
			he.printStackTrace();
			rollback();
			return false;
		}
	}
	
public User getUserByUserId(long id){
		
		try{
			
			 Criteria crit = getSession().createCriteria(User.class);
			 Criterion username = Restrictions.eq("personID", id);
			 crit.add(username);
			 begin();
			 User user=(User) crit.uniqueResult();
			commit();
			close();
			return user;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			
		}
		return null;
	}
	//get user from only user name
	public User getUserByUserName(String userName){
		
		try{
			
			 Criteria crit = getSession().createCriteria(User.class);
			 Criterion username = Restrictions.eq("userName", userName);
			 crit.add(username);
			 begin();
			 User user=(User) crit.uniqueResult();
			commit();
			close();
			return user;
		}catch(HibernateException e){
			e.printStackTrace();
			rollback();
			
		}
		return null;
	}
	// create employee
	public User createEmployee(String firstName, String lastName, String userName, String password, String emailId,
			String gender, String dateOfBirth) {
		try {
			
//			System.out.println("In Create Employee DAO");

			User user = new User(userName, PasswordEncryption.encrypt(password));
			user.setUserType(UserType.Employee);
			user.setEmailId(emailId);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setGender(gender);
			user.setDateOfBirth(LocalDate.parse(dateOfBirth));
			getSession().save(user);
			commit();
			close();
//			System.out.println(user.getFirstName());
			return user;
		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	// get user for USERNAME and PASSWORD
	public User get(String username, String password) {
		try {
			begin();
			password = PasswordEncryption.encrypt(password);
			Query q = getSession().createQuery("from User where userName= :username and password= :password");
			q.setString("username", username);
			q.setString("password", password);
			
			User user = (User) q.uniqueResult();
			//getSession().load(User.class,"User");
			//user.setPassword(PasswordEncryption.decrypt(password));
			commit();
			close();
			return user;
		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;

	}

	// get list of users
	@SuppressWarnings("unchecked")
	public synchronized List<User> getListOfUsers(String username) {
		try {
			//begin();
			// Criteria crit = getSession().createCriteria(User.class);
			// Criterion userName = Restrictions.like("userName", username +
			// "%", MatchMode.ANYWHERE);
			// crit.add(userName);
			begin();
			
			ArrayList<User> listOfUsers;// = (ArrayList<User>) crit.list();

			Query q = getSession().createQuery("from User where userName like :username");
			q.setString("username", username+"%");
			listOfUsers = (ArrayList<User>) q.list();
			commit();
			close();
//			for (User u : listOfUsers) {
////				System.out.println("-------------------" + u.getUserName());
//			}
			return listOfUsers;
		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
		}
		return null;

	}

	// get list of users
	public ArrayList<User> getListOfEmployees(String username) {
		try {

			Criteria crit = getSession().createCriteria(User.class);
			Criterion userName = Restrictions.ilike("userName", username + "%", MatchMode.START);
			Criterion userType = Restrictions.eq("userType", UserType.Employee);
			Conjunction conjunction = Restrictions.conjunction();
			conjunction.add(userName);
			conjunction.add(userType);
			crit.add(conjunction);
			begin();
			@SuppressWarnings("unchecked")
			ArrayList<User> list = (ArrayList<User>) crit.list();

			// ArrayList<User> listOfUsers = new ArrayList<User>();
			// listOfUsers = (ArrayList<User>)
			// getSession().createCriteria(User.class)
			// .add(Restrictions.ilike("userName", username + "%",
			// MatchMode.ANYWHERE)).list();

//			for (User u : list) {
//				System.out.println("-------------------" + u.getUserName());
//			}

			commit();
			close();
			return list;
		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
		}
		return null;

	}

	public boolean resetPassword(User user, String password) {

		try {
			begin();
			user.setPassword(PasswordEncryption.encrypt(password));
			getSession().update(user);
			commit();
			close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollback();
			return false;
		}

	}
}
