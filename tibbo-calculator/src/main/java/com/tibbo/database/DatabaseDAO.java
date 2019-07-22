package com.tibbo.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO {
    private static Session session = null;
    private static final String url = "jdbc:postgresql://localhost:15555/calc_base";

    static {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static Session getSession() {
        return session;
    }

    public static void registerUser(String username, String password) {
        //Выполнить Insert в базу данных
        //session.createQuery("INSERT INTO users VALUES ('" + username +"', '" + password + "')");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "postgres", "7010666");
            Statement statement = conn.createStatement();
            statement.executeQuery("INSERT INTO users VALUES ('" + username +"', '" + password + "');");
        } catch (Exception ex) {
            System.out.printf(ex.toString());
        }finally{
            try{
                conn.close();
            }catch (SQLException ex){
                System.out.println(ex);
            }
        }
    }

    public static boolean checkUserExist(String username, String password) {
        //Выполнить SELECT в базу данных
        //если то, что вернулось не пусто, то true в другом случае false;
        boolean matchIsFounded = false;
        //final Connection connection = DriverManager.getConnection()
        //Statement statement =
        /*List<Object[]> resultList = session.createQuery("SELECT * FROM users").getResultList();
        try {
            for (Object[] cell : resultList) {
                if (cell.getString(username) == username && cell.getString(pass) == password) {
                    matchIsFounded = true;
                }
            }
        } catch (Exception ex) {
            System.out.printf(ex.toString());
        }*/
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "postgres", "7010666");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
            while (resultSet.next()) {
                if(resultSet.getString(1).equals(username) && resultSet.getString(2).equals(password)){
                    matchIsFounded = true;
                }
            }
        } catch (Exception ex) {
            System.out.printf(ex.toString());
        }finally{
            try{
                conn.close();
            }catch (SQLException ex){
                System.out.println(ex);
            }
        }
        return matchIsFounded;
    }
}
