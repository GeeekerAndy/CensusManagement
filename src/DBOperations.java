import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import javax.swing.*;
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
        this.disconnectDatabase();

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
    public void disconnectDatabase() {
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
            tables.close();
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
                    this.disconnectDatabase();
                    return true;
                }
                statement.close();
                resultSet.close();
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
            statement.close();
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
                    "censusID CHAR(10) NOT NULL," +
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

    //检查用户名密码
    public boolean checkAccount(String userName, String userPassword) {
        try {
            this.connectDatabase();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM userAccount WHERE userName = '" + userName +"' && userPassword = '" + userPassword + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.absolute(1)) {
                this.disconnectDatabase();
                return true;
            }
            statement.close();
            resultSet.close();
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
                this.disconnectDatabase();
                return true;
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
            this.disconnectDatabase();
            statement.close();
            System.out.println("Add a user account.");
            JOptionPane.showMessageDialog(null, "添加成功", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "添加失败", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //添加户籍
    public void addCensus(String[] arrCensus) {
        try {
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sql = "INSERT INTO census (censusID, censusType, censusHost, censusAddress) VALUES ('" +
                    arrCensus[0] + "','" +
                    arrCensus[1] + "','" +
                    arrCensus[2] + "','" +
                    arrCensus[3] + "');";
            statement.execute(sql);
            this.disconnectDatabase();
            statement.close();
            System.out.println("Census added.");
            JOptionPane.showMessageDialog(null, "成功添加户籍", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "添加户籍失败,请重新输入!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    //添加个人信息
    public void addPersonalInfo(String[] arrPersonalInfo) {
        try {
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sql = "INSERT INTO personalInfo (name, censusID, hostOrRelation, formerName, " +
                    "gender, birthplace, nationality, birthday, address, religion, IDNumber, Height, " +
                    "bloodType, educationLevel, marriageStatus, militaryStatus, occupation, ImmigrationEmigration) VALUES ('" +
                    arrPersonalInfo[0] + "','" +
                    arrPersonalInfo[1] + "','" +
                    arrPersonalInfo[2] + "','" +
                    arrPersonalInfo[3] + "','" +
                    arrPersonalInfo[4] + "','" +
                    arrPersonalInfo[5] + "','" +
                    arrPersonalInfo[6] + "','" +
                    arrPersonalInfo[7] + "','" +
                    arrPersonalInfo[8] + "','" +
                    arrPersonalInfo[9] + "','" +
                    arrPersonalInfo[10] + "','" +
                    arrPersonalInfo[11] + "','" +
                    arrPersonalInfo[12] + "','" +
                    arrPersonalInfo[13] + "','" +
                    arrPersonalInfo[14] + "','" +
                    arrPersonalInfo[15] + "','" +
                    arrPersonalInfo[16] + "','" +
                    arrPersonalInfo[17] + "');";
            statement.execute(sql);
            statement.close();
            JOptionPane.showMessageDialog(null, "成功添加用户", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.print("Personal information added.");
            this.disconnectDatabase();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "输入的数据格式错误,或者输入了他人的身份证号, 请重新输入！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //删除用户帐号
    public void deleteUserAccount(String userName) {
        try {
            this.connectDatabase();
            if(userName.equals("")) {
                throw new Exception("User number does not exist.");
            }
            Statement statement = this.connection.createStatement();
            String sqlDelete = "DELETE FROM userAccount WHERE userName = '" + userName + "';";
            String sqlSearch = "SELECT * FROM userAccount WHERE userName = '" + userName + "';";
            ResultSet resultSet = statement.executeQuery(sqlSearch);
            if(!resultSet.absolute(1)) {
                throw new Exception("User number does not exist.");
            }
            statement.execute(sqlDelete);
            statement.close();
            resultSet.close();
            this.disconnectDatabase();
            System.out.println("User account deleted.");
            JOptionPane.showMessageDialog(null, "删除用户成功", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Delete user account failed.");
            JOptionPane.showMessageDialog(null, "删除用户失败！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "用户不存在！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //删除户籍
    public void deleteCensus(String censusID) {
        try {
            if(censusID.equals("")) {
                throw new Exception("CensusID does not exist.");
            }
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sqlDelete = "DELETE FROM census WHERE censusID = '" + censusID + "';";
            String sqlSearch = "SELECT * FROM census WHERE censusID = '" + censusID + "';";
            ResultSet resultSet = statement.executeQuery(sqlSearch);
            if(!resultSet.absolute(1)) {
                throw new Exception("CensusID does not exist.");
            }
            statement.execute(sqlDelete);
            statement.close();
            resultSet.close();
            this.disconnectDatabase();
            System.out.println("Census deleted.");
            JOptionPane.showMessageDialog(null, "删除户籍成功！", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "删除户籍失败！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "删除失败，户籍号不存在！", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //删除个人信息
    public void deletePersonalInfo(String IDNumber) {
        try {
            if (IDNumber.equals("")) {
                throw new Exception("IDNumber does not exist.");
            }
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sqlDelete = "DELETE FROM personalInfo WHERE IDNumber = '" + IDNumber + "';";
            String sqlSearch = "SELECT * FROM personalInfo WHERE IDNumber = '" + IDNumber + "';";
            ResultSet resultSet = statement.executeQuery(sqlSearch);
            if (!resultSet.absolute(1)) {
                throw new Exception("IDNumber does not exist.");
            }
            statement.execute(sqlDelete);
            statement.close();
            resultSet.close();
            this.disconnectDatabase();
            System.out.println("Personal information deleted.");
            JOptionPane.showMessageDialog(null, "删除用户成功！", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "删除用户失败！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "删除失败，身份证号不存在！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //通过户号查询户籍信息
    public String[] searchCensusByID(String censusID) {
        try {
            if(censusID.equals("")) {
                throw new Exception("CensusID does not Exist.");
            }
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sql = "SELECT * FROM census WHERE censusID = '" + censusID + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            String[] resultCensus = new String[4];
            while(resultSet.next()) {
                resultCensus[0] = resultSet.getString("censusID");
                resultCensus[1] = resultSet.getString("censusType");
                resultCensus[2] = resultSet.getString("censusHost");
                resultCensus[3] = resultSet.getString("censusAddress");
            }
            if(resultCensus[0].equals("")) {
                throw new Exception("CensusID does not Exist.");
            }
            statement.close();
            resultSet.close();
            this.disconnectDatabase();
            System.out.println("Search census success.");
            JOptionPane.showMessageDialog(null, "查询成功！", "Success", JOptionPane.INFORMATION_MESSAGE);
            return resultCensus;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询户籍失败！", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "户籍号不存在！", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    //通过身份帐号查询户籍信息
    public String[] searchCensusByHostIDNumber(String hostIDNumber) {
        try {
            if(hostIDNumber.equals("")) {
                throw new Exception();
            }
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sqlSearchCensusIDFromPersonalInfo = "SELECT * FROM personalInfo WHERE IDNumber = '" +
                    hostIDNumber + "';";
            ResultSet resultSetcensusID = statement.executeQuery(sqlSearchCensusIDFromPersonalInfo);
            String censusID = null;
            if(resultSetcensusID.next()) {
                censusID = resultSetcensusID.getString("censusID");
            }
            System.out.println(censusID);
            String sql = "SELECT * FROM census WHERE censusID = '" + censusID + "';";
            ResultSet resultSetCensusInfo = statement.executeQuery(sql);
            String[] resultCensus = new String[4];
            while(resultSetCensusInfo.next()) {
                resultCensus[0] = resultSetCensusInfo.getString("censusID");
                resultCensus[1] = resultSetCensusInfo.getString("censusType");
                resultCensus[2] = resultSetCensusInfo.getString("censusHost");
                resultCensus[3] = resultSetCensusInfo.getString("censusAddress");
            }
            if(resultCensus[0].equals("")) {
                throw new Exception("CensusID does not Exist.");
            }
            statement.close();
            resultSetcensusID.close();
            resultSetCensusInfo.close();
            this.disconnectDatabase();
            System.out.println("Search census success.");
            JOptionPane.showMessageDialog(null, "查询成功！", "Success", JOptionPane.INFORMATION_MESSAGE);
            return resultCensus;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "查询失败！户籍号不存在！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "户籍号或身份证号为空，请更新户籍和个人信息！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }

    //通过身份证号查询用户信息
    public String[] searchPersonalInfo(String IDNumber) {
        try {
            if(IDNumber.equals("")) {
                throw new Exception("IDNumber does not exist.");
            }
            this.connectDatabase();
            String sql = "SELECT * FROM personalInfo WHERE IDNumber = '" + IDNumber + "';";
            String[] result = new String[18];
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                result[0] = resultSet.getString("name");
                result[1] = resultSet.getString("censusID");
                result[2] = resultSet.getString("hostOrRelation");
                result[3] = resultSet.getString("formerName");
                result[4] = resultSet.getString("gender");
                result[5] = resultSet.getString("birthplace");
                result[6] = resultSet.getString("nationality");
                result[7] = resultSet.getString("birthday");
                result[8] = resultSet.getString("address");
                result[9] = resultSet.getString("religion");
                result[10] = resultSet.getString("IDNumber");
                result[11] = resultSet.getString("height");
                result[12] = resultSet.getString("bloodType");
                result[13] = resultSet.getString("educationLevel");
                result[14] = resultSet.getString("marriageStatus");
                result[15] = resultSet.getString("militaryStatus");
                result[16] = resultSet.getString("occupation");
                result[17] = resultSet.getString("ImmigrationEmigration");

            }
            if(result[10].equals("")) {
                throw new Exception("IDNumber does not exist.");
            }
            statement.close();
            resultSet.close();
            this.disconnectDatabase();
            System.out.println("Personal information searched.");
            JOptionPane.showMessageDialog(null, "查询成功！", "Success", JOptionPane.INFORMATION_MESSAGE);
            return result;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "查询失败！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "查询失败！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return null;
    }

    //查询帐号信息
    public String[] searchAccount(String userName) {
        try {
            if(userName.equals("")) {
                throw new Exception("User name does not exist.");
            }
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sql = "SELECT * FROM userAccount WHERE userName = '" + userName + "';";
            String[] result = new String[4];
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                result[0] = resultSet.getString("userName");
                result[1] = resultSet.getString("userPassword");
                result[2] = resultSet.getString("userPower");
                result[3] = resultSet.getString("IDNumber");
            }
            if(result[0].equals("")) {
                throw new Exception("User name does not exist.");
            }
            statement.close();
            resultSet.close();
            this.disconnectDatabase();
            System.out.println("Account deleted.");
            JOptionPane.showMessageDialog(null, "查询帐号成功！", "Success", JOptionPane.INFORMATION_MESSAGE);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询帐号失败！", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询帐号失败，用户不存在！", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    //根据用户名查询户籍
    public String[] searchCensusByUserName(String userName) {
        try {
            if(userName.equals("")) {
                throw new Exception("User name does not exist.");
            }
            this.connectDatabase();
            String sqlSearchIDNumberByUserName = "SELECT IDNumber FROM userAccount WHERE userName = '" + userName + "';";
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSearchIDNumberByUserName);
            String IDNumber = null;
            while(resultSet.next()) {
                IDNumber = resultSet.getString("IDNumber");
            }
            resultSet = null;
            String sqlSearchCensusIDByIDNumber = "SELECT censusID FROM personalInfo WHERE IDNumber = '" + IDNumber + "';";
            resultSet = statement.executeQuery(sqlSearchCensusIDByIDNumber);
            String censusID = null;
            while(resultSet.next()) {
                censusID = resultSet.getString("censusID");
            }
            resultSet = null;
            String[] result = new String[5];
            String sqlSearchCensus = "SELECT * FROM census WHERE censusID = '" + censusID + "';";
            resultSet = statement.executeQuery(sqlSearchCensus);
            while(resultSet.next()) {
                result[0] = resultSet.getString("censusID");
                result[1] = resultSet.getString("censusType");
                result[2] = resultSet.getString("censusHost");
                //result[3] will be added below;
                result[4] = resultSet.getString("censusAddress");

            }
            resultSet = null;
            String sqlHostOrRelation = "SELECT hostOrRelation FROM personalInfo WHERE IDNumber = '" + IDNumber + "';";
            resultSet = statement.executeQuery(sqlHostOrRelation);
            while(resultSet.next()) {
                result[3] = resultSet.getString(1);
            }
            statement.close();
            resultSet.close();
            this.disconnectDatabase();
            System.out.println("Search census information by user name successful.");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败！", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "查询失败，用户名为空或用户户籍信息不完善！", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    //更新户籍信息
    public void updateCensus(String censusID, String censusColumn, String censusInfo) {
        try {
            if(censusID.equals("")) {
                throw new Exception("censusID does not exist.");
            }
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sqlSearch = "SELECT * FROM census WHERE censusID = '" + censusID + "';";
            ResultSet resultSet = statement.executeQuery(sqlSearch);
            if(!resultSet.absolute(1)) {
                throw new Exception("CensusID does not exist.");
            }
            String sql = "UPDATE census SET " + censusColumn + " = '" + censusInfo + "' WHERE censusID = '" + censusID + "';";
            statement.execute(sql);
            this.disconnectDatabase();
            statement.close();
            JOptionPane.showMessageDialog(null, "更新户籍成功！", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.print("Census updated.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "更新户籍失败！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "户籍号不存在！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //更新个人信息
    public void updatePersonalInfo(String IDNumber, String personalInfoColumn, String information) {
        try {
            if(IDNumber.equals("")) {
                throw new Exception("IDNumber does not exists.");
            }
            this.connectDatabase();
            Statement statement = this.connection.createStatement();
            String sqlSearch = "SELECT * FROM personalInfo WHERE IDNumber = '" + IDNumber + "';";
            ResultSet resultSet = statement.executeQuery(sqlSearch);
            if(!resultSet.absolute(1)) {
                throw new Exception("IDNumber does not exists.");
            }
            String sql = "UPDATE personalInfo SET " + personalInfoColumn + " = '" + information + "' WHERE IDNumber = '" + IDNumber + "';";
            statement.execute(sql);
            this.disconnectDatabase();
            statement.close();
            JOptionPane.showMessageDialog(null, "更新个人信息成功！", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Personal information updated.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "更新个人信息失败！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "更新失败，身份证号不存在！", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
