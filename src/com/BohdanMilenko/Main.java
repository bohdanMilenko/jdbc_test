package com.BohdanMilenko;

import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Drive D\\Java Root\\Java Directory\\TestDB\\" + DB_NAME;
    public static final String TABLE_CONTACTS ="contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";


    public static void main(String[] args) {
	// write your code here

//    try(Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Drive D\\Java Root\\Java Directory\\TestDB\\testjava5.db");
//        Statement statement = conn.createStatement();){
//       statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER)");

        try{
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
           // conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS "+
                    TABLE_CONTACTS+ "    (" + COLUMN_NAME + " TEXT,"
                                            + COLUMN_PHONE + " INTEGER)");

            createStatement(statement,"Albert", 6479874);
            createStatement(statement,"Bobbie", 8063468);
            createStatement(statement,"Sam", 58996324);
            createStatement(statement,"Funmi", 963852741);

//            statement.execute("INSERT INTO " + TABLE_CONTACTS
//                    + " (" + COLUMN_NAME + ", " + COLUMN_PHONE + ") " +
//                    "VALUES ('Albert', 6479874)");
//

            statement.execute("UPDATE "+ TABLE_CONTACTS +" SET " +
                    COLUMN_PHONE + " = 0123456 " +
                    "WHERE " + COLUMN_NAME + " = 'Funmi'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS +
                    " WHERE " + COLUMN_NAME + " = 'Sam'");

//            statement.execute("INSERT INTO contacts (name, phone) "+
//                    "VALUES ('Albert', 6478978974)");
//            statement.execute("INSERT INTO contacts (name, phone) "+
//                    "VALUES ('Funmy', 9376547893)");
//            statement.execute("INSERT INTO contacts (name, phone) "+
//                    "VALUES ('Bobbie', 0639631234)");
//              statement.execute("UPDATE contacts SET phone = 64734589 WHERE name = 'Albert'");
//              statement.execute("DELETE FROM contacts WHERE name = 'Funmy' ");

//            statement.execute("SELECT * FROM contacts");
//            ResultSet rs = statement.getResultSet();

            ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);

            while(rs.next()){
                System.out.println( rs.getString(COLUMN_NAME) + " " +
                rs.getInt(COLUMN_PHONE));
            }

            rs.close();


            statement.close();
            conn.close();

    }
    catch (SQLException e){
        System.out.println("Smth went wrong: " + e.getMessage());
        e.printStackTrace();
    }

    }

    public static void createStatement (Statement statement, String name, int number) throws  SQLException{
        statement.execute("INSERT INTO " +
                TABLE_CONTACTS +
                " (" + COLUMN_NAME + "," +
                COLUMN_PHONE + ")" +
                "VALUES ('" + name + "', " + number +")");

    }
}
