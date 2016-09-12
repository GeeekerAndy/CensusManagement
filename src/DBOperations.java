import java.sql.*;
import java.util.Properties;

/**
 * Created by andy on 9/9/16.
 */
public class DBOperations {

    private static final String dbClassName = "com.mysql.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://127.0.0.1/CensusManagementDB?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "5218";

    private Connection connection;

    public DBOperations () {

        this.connectDatabase();

        if(!this.checkTableExists("userAccount")) {
            this.createTableUserAccount();
        }
        if(!this.checkTableExists("census")) {
            this.createTableCensus();
        }
        if(!this.checkTableExists("personalInfo")) {
            this.createTablePersonalInfo();
        }
        this.disconnectionDatabase();

    }

    //连接数据库
    public void connectDatabase() {
        try {
            System.out.println(dbClassName);
            Class.forName(dbClassName);
            Properties properties = new Properties();
            properties.put("user", USER);
            properties.put("password", PASSWORD);
            connection = DriverManager.getConnection(CONNECTION, properties);
            System.out.println("Database connected.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //断开数据库连接
    public void disconnectionDatabase() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("connection closed.");
    }

//    //检查数据库是否存在
//    public boolean checkDBExists(String dbName) {
//        try {
//            ResultSet resultSet = connection.getMetaData().getCatalogs();
//            while(resultSet.next()) {
//                String databaseName = resultSet.getString(1);
//                if(databaseName.equals(dbName)) {
//                    return true;
//                }
//            }
//            resultSet.close();
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    //检查表是否存在
    public boolean checkTableExists(String tableName) {
        try {
            DatabaseMetaData dbm = this.connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if(tables.next()) {
                return true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //检查某一个健值在数据库中是否存在
    public boolean checkValueExists(String tableName, String value) {
        try {
            this.connectDatabase();
            if(tableName.equals("userAccount")) {
                String sql = "SELECT * FROM " + tableName + " WHERE userName = '" + value + "';";
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                if(resultSet.absolute(1)) {
                    this.disconnectionDatabase();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

//    //创建户籍管理数据库
//    public  void createDBCensusManagement() {
//        try {
//            Statement statement = this.connection.createStatement();
//            String sql = "CREATE DATABASE CensusManagementDB";
//            statement.execute(sql);
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }

    //创建用户帐号表
    public void createTableUserAccount() {
        try {
            Statement statement = this.connection.createStatement();
            String sql = "CREATE TABLE userAccount (" +
                    "userName CHAR(30) NOT NULL," +
                    "userPassword CHAR(20) NOT NULL," +
                    "userPower CHAR(1)," +
                    "IDNumber CHAR(20)," +
                    "PRIMARY KEY(userName)" +
                    ");";
            statement.execute(sql);
            System.out.println("Table userAccount created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //创建户籍表
    public void createTableCensus() {
        try {
            Statement statement = this.connection.createStatement();
            String sql = "CREATE TABLE census (" +
                    "censusID CHAR(10) NOT NULL," +
                    "censusType CHAR(20) NOT NULL," +
                    "censusHost CHAR(10) NOT NULL," +
                    "censusAddress VARCHAR(50) NOT NULL," +
                    "PRIMARY KEY(censusID)" +
                    ");";
            statement.execute(sql);
            System.out.println("Table census created.");
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //创建个人信息表
    public void createTablePersonalInfo() {
        try {
            Statement statement = this.connection.createStatement();
            String sql = "CREATE TABLE personalInfo (" +
                    "name CHAR(10) NOT NULL," +
                    "censusID INT(4) NOT NULL," +
                    "hostOrRelation CHAR(10) NOT NULL," +
                    "formerName CHAR(10) ," +
                    "gender CHAR(4) NOT NULL," +
                    "birthplace VARCHAR(50) NOT NULL," +
                    "nationality CHAR(10) NOT NULL," +
                    "birthday CHAR(15) NOT NULL," +
                    "address VARCHAR(50) NOT NULL," +
                    "religion CHAR(10)," +
                    "IDNumber CHAR(20) NOT NULL," +
                    "height CHAR(3) NOT NULL," +
                    "bloodType CHAR(10)," +
                    "educationLevel CHAR(10) NOT NULL," +
                    "marriageStatus CHAR(10) NOT NULL," +
                    "militaryStatus CHAR(10) NOT NULL," +
                    "occupation CHAR(10) NOT NULL," +
                    "ImmigrationEmigration CHAR(10) NOT NULL," +
                    "PRIMARY KEY (IDNumber)" +
                    ");";
            statement.execute(sql);
            statement.close();
            System.out.println("Table personalInfo created.");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //添加用户信息
    public void addUserAccount(String userName, String userPassword, String userPower, String IDNumber) {
        try {
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sql = "INSERT INTO userAccount (userName, userPassword, userPower, IDNumber) VALUES ('" +
                    userName + "','" + userPassword + "'," +
                    userPower + ",'" + IDNumber + "');";
            statement.execute(sql);
            System.out.println("Add a user account.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.disconnectionDatabase();
    }

    //检查用户名密码
    public boolean checkAccount(String userName, String userPassword) {
        try {
            this.connectDatabase();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM userAccount WHERE userName = '" + userName +"' && userPassword = '" + userPassword + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.absolute(1)) {
                this.disconnectionDatabase();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //判断权限
    public boolean checkPower(String userName, String userPassword) {
        try {
            this.connectDatabase();
            String sql = "SELECT * FROM userAccount WHERE userName = '" + userName + "' && userPassword = '" + userPassword + "' && userPower = '1';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.absolute(1)) {
                this.disconnectionDatabase();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //添加户籍
    public void addCensus() {

    }

    //添加个人信息
    public void addPersonalInfo() {

    }

    //删除用户帐号
    public void deleteUserAccount() {

    }

    //删除户籍
    public void deleteCensus() {

    }

    //删除个人信息
    public void deletePersonalInfo() {

    }

}
