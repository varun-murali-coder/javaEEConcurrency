package com.app.ejbbeans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 * Session Bean implementation class TransactionBean
 */
@Stateless(name="tx-bean")
@LocalBean
public class TransactionBean {

    @Resource(lookup="jdbc/source1")
	private DataSource source1;
    @Resource(lookup="jdbc/source1")
	private DataSource source2;
	
    public void saveBankAccountTransaction() {
    	try {
			Connection conn=source1.getConnection();
			Statement stmt=conn.createStatement();
			String query="insert into bank_account_transaction(tx_id,amount,"
					+ "tx_type,tx_date,acc_number)"
					+ "values(11,555,'debit','"+new Date(System.currentTimeMillis())+"',101)";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void saveBankAccountTransactionLog() throws SQLException {
			Connection conn=source1.getConnection();
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("insert into bank_account_transaction_log(id,tx_id,"
					+ "tx_done_by,curr_date)"
					+ "values(3,11,'system','"+new Date(System.currentTimeMillis())+"')");
		
    }

}
