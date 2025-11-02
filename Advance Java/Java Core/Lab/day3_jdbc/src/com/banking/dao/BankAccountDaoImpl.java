package com.banking.dao;

import java.sql.*;
import static com.banking.utils.DBUtils.*;

public class BankAccountDaoImpl implements BankAccountDao {
	private Connection connection;
	private CallableStatement cst1;
	//def ctor
	public BankAccountDaoImpl()throws SQLException {
		//1. open cn
		connection=openConnection();
		//2. cst1 - invoke stored proc
		cst1=connection.prepareCall("{call transfer_funds_proc(?,?,?,?,?)}");
		//3. register OUT parameter
		cst1.registerOutParameter(4, Types.DOUBLE);
		cst1.registerOutParameter(5, Types.DOUBLE);
		System.out.println("acct dao created !");
	}

	@Override
	public String transferFunds(int srcAcctNo, int destAcctNo, double amount) throws SQLException {
		// 1. set IN params
		cst1.setInt(1, srcAcctNo);
		cst1.setInt(2, destAcctNo);
		cst1.setDouble(3, amount);
		//2 execute the proc
		cst1.execute();
		//3 . read results form OUT params
		return "Updated src balance "+cst1.getDouble(4)+" dest balance "+cst1.getDouble(5);
	}

	@Override
	public void cleanUp() throws SQLException {
		if(cst1 != null)
		{
			cst1.close();
			cst1=null;
			closeConnection();
			System.out.println("acct dao  cleaned up ");
		}
		
	}
	
	

}
