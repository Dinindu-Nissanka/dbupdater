package com.wso2telco.atum.demo.dbupdater;

import java.sql.*;

public class DBUpdateHandler {

    private AppConfiguration configuration;

    public DBUpdateHandler(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    public void createMapping(String id) {

        String appID = null;
        Connection conAPIMgtDb = null;
        Connection conDEPDb = null;
        Statement stmt=null;
        ResultSet rs=null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conAPIMgtDb=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+this.configuration.getMysql().getApm()
                    ,this.configuration.getMysql().getUsername(),
                    this.configuration.getMysql().getPassword());

            if(conAPIMgtDb != null){
                stmt=conAPIMgtDb.createStatement();
                rs=stmt.executeQuery("select * from prodapimgtdb.am_application where NAME='"+id+"'");
                while(rs.next())
                    appID = rs.getString(1);
                if(appID != null){
                    conDEPDb=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/"+this.configuration.getMysql().getDep(),
                            this.configuration.getMysql().getUsername(),
                            this.configuration.getMysql().getPassword());
                    preparedStatement = conDEPDb
                            .prepareStatement("insert into proddepdb.sub_approval_operators values (?, ?, ?, ? , ?)");
                    preparedStatement.setString(1, this.configuration.getApiName());
                    preparedStatement.setString(2, this.configuration.getApiVersion());
                    preparedStatement.setString(3, this.configuration.getApiProvider());
                    preparedStatement.setString(4, appID);
                    preparedStatement.setString(5, this.configuration.getOperatorList());
                    preparedStatement.executeUpdate();
                }
            }
        }catch(Exception e){
            System.out.println(e);}
            finally {
            try {
                conAPIMgtDb.close();
                conDEPDb.close();
                rs.close();
                stmt.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
