package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.app.beans.BankAccount;
import com.app.beans.BankAccountTransaction;

public class BankAccountDao {
	
	private DataSource dataSource;

	public BankAccountDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<BankAccount> getAllBankAccounts(){
		Connection conn;
		List<BankAccount> accounts=new ArrayList<BankAccount>();
		try {
			conn = dataSource.getConnection();
			BankAccount account=null;
			Statement stmt=conn.createStatement();
			ResultSet set=stmt.executeQuery("select * from bank_account");
			while(set.next()) {
				account=new BankAccount();
				account.setAccNumber(set.getInt("acc_number"));
				account.setName(set.getString("acc_holder_name"));
				account.setEmail(set.getString("acc_email"));
				account.setAccType(set.getString("acc_type"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return accounts;
	}
	
	public List<BankAccountTransaction> getTransactionsForAccount(BankAccount bankAccount)
	{
		Connection conn;
		List<BankAccountTransaction>transactions=new ArrayList<BankAccountTransaction>();

		try {
			conn = dataSource.getConnection();
			BankAccountTransaction transaction=null;
			PreparedStatement stmt=conn.prepareStatement("select * from bank_account_transaction where"
					+ " acc_number=?");
			stmt.setInt(1, bankAccount.getAccNumber());
			ResultSet set=stmt.executeQuery();
			while(set.next()) {
				transaction=new BankAccountTransaction();
				transaction.setAccNumber(set.getInt("acc_number"));
				transaction.setAmount(set.getDouble("amount"));
				transaction.setTxDate(new Date(set.getDate("tx_date").getTime()));//convert sql to util date
				transaction.setTxId(set.getInt("tx_id"));
				transaction.setTxType(set.getString("tx_type"));
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;

		
	}

}
