package com.datvutech.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.datvutech.data.entity.Bid;
import com.datvutech.data.entity.Item;
import com.datvutech.data.entity.User;
import com.datvutech.data.entity.view.BriefItem;
import com.datvutech.data.entity.view.ItemBidSummary;

public class HibernatePart2 {
    private static final SessionFactory SESSION_FACTORY;
    private static Properties appProps;

    private static void loadApplicationProperties() {
        try {
            String appPropsFilePath = "./src/main/resources/application.properties";
            FileInputStream appPropsFile = new FileInputStream(appPropsFilePath);
            appProps = new Properties();
            appProps.load(appPropsFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static {
        System.out.println(System.getProperty("user.dir"));
        loadApplicationProperties();
        Configuration conf = new Configuration();
        conf.setProperty(Environment.URL, appProps.getProperty(Environment.URL));
        conf.setProperty(Environment.USER, appProps.getProperty(Environment.USER));
        conf.setProperty(Environment.PASS, appProps.getProperty(Environment.PASS));
        conf.setProperty(Environment.DRIVER, appProps.getProperty(Environment.DRIVER));
        conf.setProperty(Environment.HBM2DDL_AUTO, appProps.getProperty(Environment.HBM2DDL_AUTO));
        conf.setProperty(Environment.DIALECT, appProps.getProperty(Environment.DIALECT));
        conf.setProperty(Environment.SHOW_SQL, appProps.getProperty(Environment.SHOW_SQL));
        conf.setProperty(Environment.FORMAT_SQL, appProps.getProperty(Environment.FORMAT_SQL));
        conf.setProperty(Environment.JDBC_TIME_ZONE, appProps.getProperty(Environment.JDBC_TIME_ZONE));
        conf.setProperty(Environment.PHYSICAL_NAMING_STRATEGY,
                appProps.getProperty(Environment.PHYSICAL_NAMING_STRATEGY));

        conf.addAnnotatedClass(Item.class);
        conf.addAnnotatedClass(Bid.class);
        conf.addAnnotatedClass(ItemBidSummary.class);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(BriefItem.class);

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties())
                .build();
        SESSION_FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getInstance() {
        return SESSION_FACTORY;
    }

    private HibernatePart2() {
    }
}
