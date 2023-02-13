package com.datvutech;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.datvutech.app.config.HibernatePart2;
import com.datvutech.data.embeddable.Dimension;
import com.datvutech.data.entity.Bid;
import com.datvutech.data.entity.Item;
import com.datvutech.data.entity.view.ItemBidSummary;

public class App {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernatePart2.getInstance();
		Session session = sessionFactory.openSession();
		createDummyItemBid(session);
		session.beginTransaction();
		/* Start code here */

		ItemBidSummary ibs = session.get(ItemBidSummary.class, 1L);

		/* End code here */
		session.getTransaction().commit();
		session.close();
		System.out.println("Hello World!");
	}

	protected static void createDummyItemBid(Session session) {
		session.beginTransaction();
		Item item1 = new Item("iPhone 13 Pro Max", Dimension.of(Dimension.CM, Dimension.CM_SYMBOL, "20x20x20"), 200.0D);
		Item item2 = new Item("Samsung Galaxy Note 9", Dimension.of(Dimension.CM, Dimension.CM_SYMBOL, "20x30x20"),
				0.201D);
		Item item3 = new Item("Google Pixel 7 Pro", Dimension.of(Dimension.CM, Dimension.CM_SYMBOL, "10x20x20"),
				0.212D);
		item1.addImage("/item1");
		item2.addImage("/item2");
		item3.addImage("/item3");
		session.save(item1);
		session.save(item2);
		session.save(item3);

		new Bid(new BigDecimal("1000"), item1);
		new Bid(new BigDecimal("1200"), item2);
		new Bid(new BigDecimal("3200"), item1);
		new Bid(new BigDecimal("350"), item3);
		new Bid(new BigDecimal("2000"), item2);
		new Bid(new BigDecimal("4000"), item1);

		session.getTransaction().commit();
		System.out.println("CREATION FINISHED");
	}
}
