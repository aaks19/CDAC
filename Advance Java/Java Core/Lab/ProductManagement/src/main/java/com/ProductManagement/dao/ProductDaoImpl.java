package com.ProductManagement.dao;

import static com.ProductManagement.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ProductManagement.entities.Category;
import com.ProductManagement.entities.Product;

public class ProductDaoImpl implements ProductDao {

	@Override
	public String addNewProduct(Product newProduct) {
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.persist(newProduct);
			tx.commit();
		}catch (RuntimeException e) {
			if(tx!=null) {
				tx.rollback();
			}
			throw e;
		}
		return "Product added with Id - "+newProduct.getProductId();
	}

	@Override
	public List<Product> displayProducts() {
		List<Product> prod = null;
		String jpql = "select p from Product p";
		
		Session session = getFactory().getCurrentSession();
		
		Transaction tx = session.beginTransaction();
		
		try {
			prod = session.createQuery(jpql, Product.class).getResultList();
			tx.commit();
		}catch(RuntimeException e) {
			if(tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return prod;
	}

	@Override
	public List<Product> displaySpecified(LocalDate mfgDate, Category cat) {
		List<Product> prod = null;
		
		String jpql = "select p.productId, p.productName, p.price from Product p where category = :cty and date > :dt";
		
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		try {
			prod = session.createQuery(jpql, Product.class).setParameter("cty", cat).setParameter("dt", mfgDate).getResultList();
			
			tx.commit();
		}catch(RuntimeException e) {
			if(tx!=null) {
				tx.rollback();
			}
			throw e;
		}
		
		return prod;
	}

	@Override
	public String changeProductPrice(String prodname, double price) {
		StringBuilder msg = new StringBuilder("Product price change - ");
		
		String jpql = "select p from Product p where p.productName = :pname";
		
		Product product = null;
		
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		try {
			product = session.createQuery(jpql, Product.class).setParameter("pname", prodname).getSingleResult();
			product.setPrice(price);
			tx.commit();
			
		}catch(RuntimeException e) {
			if(tx!=null) {
				tx.rollback();
			}
			throw e;
		}
		return msg.append("Successful").toString();
	}

}
