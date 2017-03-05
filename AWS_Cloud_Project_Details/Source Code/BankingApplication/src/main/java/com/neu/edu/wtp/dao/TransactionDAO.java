package com.neu.edu.wtp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.neu.edu.wtp.pojo.Transaction;

public class TransactionDAO extends DAO {

	@Autowired
	@Qualifier("bankAccountDAO")
	BankAccountDAO bankAccountDAO;

	public TransactionDAO() {

	}

	public Transaction createTransaction(Map<String, String> param) {
//		System.out.println("Inside Transaction DAO");
		Long fromAccount = Long.parseLong(param.get("fromAccount"));
		Long toAccount = Long.parseLong(param.get("toAccount"));
		Float amount = Float.parseFloat(param.get("amount"));
		String description = param.get("description");
//		System.out.println(
//				"From/To/Amount/Description" + fromAccount + "/" + toAccount + "/" + amount + "/" + description);
		try {
			begin();
			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setFromAccount(fromAccount);
			transaction.setToAccount(toAccount);
			transaction.setShortDescription(description);
			transaction.setTransactionDate(new DateTime());
			getSession().save(transaction);
			commit();
			close();
			return transaction;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
			// throw new AdException("Could not delete user " + user.getName(),
			// e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> paginateTransaction(int pageNumber, long fromAccount) {
		List<Transaction> list = new ArrayList<Transaction>();

		try {
			begin();
			Criteria criteria = getSession().createCriteria(Transaction.class);
			criteria.setFirstResult((pageNumber-1) * 2);
			criteria.setMaxResults(5);
			Criterion fromAcc = Restrictions.eq("fromAccount", fromAccount);
			Criterion toAcc = Restrictions.eq("toAccount", fromAccount);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(fromAcc);
			disjunction.add(toAcc);
			criteria.add(disjunction);
			list = (List<Transaction>)criteria.list();
			close();

			return list;
		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Transaction> statement(long fromAccount) {
//		System.out.print("In DAO");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		try {
			begin();

			Criteria crit = getSession().createCriteria(Transaction.class);
			Criterion fromAcc = Restrictions.eq("fromAccount", fromAccount);
			Criterion toAcc = Restrictions.eq("toAccount", fromAccount);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(fromAcc);
			disjunction.add(toAcc);
			crit.add(disjunction);
			list = (ArrayList<Transaction>) crit.list();

			// Query q = getSession().createQuery("from Transaction where
			// accountNumber = :accountNumber");
			// q.setLong("accountNumber", accountNumber);

			commit();
			close();
			return list;
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
			// throw new AdException("Could not delete user " + user.getName(),
			// e);
		}

		return list;

	}
}
