package com.neu.edu.wtp.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.wtp.pojo.ChequeDepositRequest;

public class ChequeDepositRequestDAO extends DAO {
	
	public ArrayList<ChequeDepositRequest> getAll(){
		try{
			begin();
			Query q=getSession().createQuery("from ChequeDepositRequest");
			@SuppressWarnings("unchecked")
			ArrayList<ChequeDepositRequest> list=(ArrayList<ChequeDepositRequest>) q.list();
			commit();
			close();
			return list;
		}catch(HibernateException he){
			he.printStackTrace();
			rollback();
			return null;
		}
	}
	public boolean addRequest(ChequeDepositRequest cdr){
		try{
			begin();
			getSession().save(cdr);
			commit();
			close();
			return true;
		}catch(HibernateException he){
			he.printStackTrace();
			rollback();
			return false;
		}
		
		
	}

}
