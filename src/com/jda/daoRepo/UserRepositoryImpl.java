package com.jda.daoRepo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import com.jda.model.User;

public class UserRepositoryImpl {
	 private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public int check(String username,String password) throws Exception{	
		 try {
			 String DBURL = "jdbc:mysql://" + System.getenv("DBHOST") + "/db1000124?user=" + System.getenv("DBUSER")
	      + "&password=" + System.getenv("DBPASSWORD");
		 Class.forName("com.mysql.cj.jdbc.Driver"); 
		 connect = (Connection) DriverManager.getConnection(DBURL);
          statement = connect.createStatement();
/*          resultSet = ((java.sql.Statement) statement)
                  .executeQuery("select * from db1000124.LoginPages");*/
   //       writeResultSet(resultSet);
          preparedStatement = ((java.sql.Connection) connect)
                  .prepareStatement("select username , password from  db1000124.LoginPages where username = ? and password = ?;");
          preparedStatement.setString(1, username);
          preparedStatement.setString(2, password);
          resultSet = preparedStatement.executeQuery();
          if(resultSet.next()){
         	 return 1;
          }
          else {
         	 return 0;
          }
          
		 }
		 catch (Exception e) {
          throw e;
      } finally {
          close();
      }

	}
	public void save(User user) throws Exception{	
		 try {
			 String DBURL = "jdbc:mysql://" + System.getenv("DBHOST") + "/db1000124?user=" + System.getenv("DBUSER")
	      + "&password=" + System.getenv("DBPASSWORD");
		 Class.forName("com.mysql.cj.jdbc.Driver"); 
		 connect = (Connection) DriverManager.getConnection(DBURL);
          statement = connect.createStatement();
          resultSet = ((java.sql.Statement) statement)
                  .executeQuery("select * from db1000124.LoginPages");
   //       writeResultSet(resultSet);
          preparedStatement = ((java.sql.Connection) connect)
                  .prepareStatement("insert into  db1000124.LoginPages values (default, ?, ?, ?, ? )");
          String username = user.getUsername();
          String email = user.getEmail();
          String mobile = user.getMobile();
          String password = user.getPassword();
          
          preparedStatement.setString(1, username);
          preparedStatement.setString(2, email);
          preparedStatement.setString(3, mobile);
         
          preparedStatement.setString(4, password);
        
          preparedStatement.executeUpdate();
          
         /* preparedStatement =  connect
                  .prepareStatement("select * from db1000124.LoginPages");
          resultSet = preparedStatement.executeQuery();
         // writeResultSet(resultSet);

          resultSet = statement
          .executeQuery("select * from db1000124.LoginPages");*/
    //      writeMetaData(resultSet);

      } catch (Exception e) {
          throw e;
      } finally {
          close();
      }

	}

	  private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                ((java.sql.Statement) statement).close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
