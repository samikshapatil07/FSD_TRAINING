package com.ecom.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ecom.dao.PurchaseDao;
import com.ecom.model.Purchase;
import com.ecom.utility.DBUtility;

public class PurchaseDaoImpl implements PurchaseDao {
	DBUtility db = DBUtility.getInstance();

	@Override
	public void addPurchase(Purchase purchase) {
		Connection conn = db.connect();
		String sql = "INSERT INTO purchase (date_of_purchase, customer_id, product_id, quantity, total_price, coupon) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, purchase.getDateOfPurchase().toString());
            ps.setInt(2, purchase.getCustomer().getId());
            ps.setInt(3, purchase.getProduct().getId());
            ps.setInt(4, purchase.getQuantity());
            ps.setDouble(5, purchase.getTotalPrice());
            ps.setString(6, String.valueOf(purchase.getCoupon()));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting purchase failed, no rows affected.");
            }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			db.close();
		}
	}

}
