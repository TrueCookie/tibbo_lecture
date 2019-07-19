package com.tibbo.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO {
    private static Session session = null;
    //private static final String url = "jdbc:postgresql://localhost:5432/server_calculator";

    static {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        session = sessionFactory.openSession();
    }

    public static Session getSession() {
        return session;
    }

    public static void registerUser(String username, String password) {
        //Выполнить Insert в базу данных
        session.createQuery("INSERT INTO server_calculator VALUES (" + username + "," + password + ")");
        /*try {
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO server_calculator VALUES (" + username + "," + password + ")");
        } catch (Exception ex) {
            System.out.printf(ex.toString());
        }*/
    }

    public static boolean checkUserExist(String username, String password) {
        //Выполнить SELECT в базу данных
        //если то, что вернулось не пусто, то true в другом случае false;
        boolean matchIsFounded = false;
        List<ResultSet> resultList = session.createQuery("SELECT * FROM server_calculator").getResultList();
        try {
            for (ResultSet cell : resultList) {
                if (cell.getString(username) == username && cell.getString(password) == password) {
                    matchIsFounded = true;
                }
            }
        } catch (Exception ex) {
            System.out.printf(ex.toString());
        }
        /*try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM server_calculator");
            while (resultSet.next()) {
                if(resultSet.getString(username) == username && resultSet.getString(password) == password){
                    matchIsFounded = true;
                }
            }
        } catch (Exception ex) {
            System.out.printf(ex.toString());
        }*/
        return matchIsFounded;
    }
}
