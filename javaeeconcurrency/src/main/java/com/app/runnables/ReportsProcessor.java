package com.app.runnables;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.Callable;

import com.app.beans.BankAccount;
import com.app.beans.BankAccountTransaction;
import com.app.dao.BankAccountDao;

public class ReportsProcessor implements Callable<Boolean> {

	private BankAccount account;
	private BankAccountDao dao;
	
	
	public ReportsProcessor(BankAccount account, BankAccountDao dao) {
		this.account = account;
		this.dao = dao;
	}


	@Override
	public Boolean call() throws Exception {
		boolean reportGenerated=false;
		List<BankAccountTransaction> transactions=dao.getTransactionsForAccount(account);
		File file=new File("D:/reports/"+account.getAccNumber()+"tx_report.txt");
		try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
		for(BankAccountTransaction transaction:transactions) {
				writer.write("Account Number:"+transaction.getAccNumber());
				writer.write("Transaction id:"+transaction.getTxId());
				writer.write("Transaction type:"+transaction.getTxType());
				writer.write("Transaction amount"+transaction.getAmount());
				writer.write("Transaction date:"+transaction.getTxDate());
				writer.newLine();
			}
		writer.flush();
		}
		reportGenerated=true;

		return reportGenerated;
	}

}
