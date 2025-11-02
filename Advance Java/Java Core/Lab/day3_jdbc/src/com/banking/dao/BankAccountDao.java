package com.banking.dao;

import java.sql.SQLException;

public interface BankAccountDao {
//transfer funds
	String transferFunds(int srcAcctNo, int destAcctNo, double amount) throws SQLException;
	void cleanUp() throws SQLException;
}
