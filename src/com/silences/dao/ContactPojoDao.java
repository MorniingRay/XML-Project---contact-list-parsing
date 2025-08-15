package com.silences.dao;

import com.silences.entity.ContactPojo;
import com.silences.readutils.DomReadXml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 数据访问层
public class ContactPojoDao {

        private Connection connect = null;
        private PreparedStatement preparedStatement = null;
        private ResultSet resultSet = null;

        public void insertContactPojo(ContactPojo contactPojo) {
            try {
                // 连接数据库
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                        "root", "1234");

                // 插入数据
                String sql = "INSERT INTO Contact (person, tags, title, firstName, middleName, lastName, address, latitude, " +
                        "longitude, king, phone, knows, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1, contactPojo.getPerson());
                preparedStatement.setString(2, contactPojo.getTags());
                preparedStatement.setString(3, contactPojo.getTitle());
                preparedStatement.setString(4, contactPojo.getFirstName());
                preparedStatement.setString(5, contactPojo.getMiddleName());
                preparedStatement.setString(6, contactPojo.getLastName());
                preparedStatement.setString(7, contactPojo.getAddress());
                preparedStatement.setString(8, contactPojo.getLatitude());
                preparedStatement.setString(9, contactPojo.getLongitude());

                preparedStatement.setString(10, contactPojo.getKing());
                preparedStatement.setString(11, contactPojo.getPhone());
                preparedStatement.setString(12, contactPojo.getKnows());
                preparedStatement.setString(13, contactPojo.getDescription());
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {
                    if (resultSet != null) resultSet.close();
                    if (preparedStatement != null) preparedStatement.close();
                    if (connect != null) connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public void updateContactPojo(ContactPojo contactPojo) {
            try {
                // 连接数据库
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                        "root", "1234");

                // 修改数据
                String sql = "update Contact  set person=?, tags=?, title=?, firstName=?, middleName=?, lastName=?, " +
                        "address=?, latitude=?, longitude=?, king=?, phone=?, knows=?, description=? where id=? ";
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1, contactPojo.getPerson());
                preparedStatement.setString(2, contactPojo.getTags());
                preparedStatement.setString(3, contactPojo.getTitle());
                preparedStatement.setString(4, contactPojo.getFirstName());
                preparedStatement.setString(5, contactPojo.getMiddleName());
                preparedStatement.setString(6, contactPojo.getLastName());
                preparedStatement.setString(7, contactPojo.getAddress());
                preparedStatement.setString(8, contactPojo.getLatitude());
                preparedStatement.setString(9, contactPojo.getLongitude());

                preparedStatement.setString(10, contactPojo.getKing());
                preparedStatement.setString(11, contactPojo.getPhone());
                preparedStatement.setString(12, contactPojo.getKnows());
                preparedStatement.setString(13, contactPojo.getDescription());
                preparedStatement.setInt(14, contactPojo.getId());
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {
                    if (resultSet != null) resultSet.close();
                    if (preparedStatement != null) preparedStatement.close();
                    if (connect != null) connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public void deleteContactPojo(int id) {
            try {
                // 连接数据库
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                        "root", "1234");

                // 删除数据
                String sql = "delete from  Contact  where id=? ";
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {
                    if (resultSet != null) resultSet.close();
                    if (preparedStatement != null) preparedStatement.close();
                    if (connect != null) connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public List<ContactPojo> retrieveContactPojo() {
            try {
                // 连接数据库
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                        "root", "1234");

                // 删除数据
                String sql = "select * from  Contact ";
                preparedStatement = connect.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<ContactPojo> list = new ArrayList<ContactPojo>();
                while(resultSet.next()) {
                    ContactPojo cp = new ContactPojo();
                    cp.setId(resultSet.getInt(1));
                    cp.setPerson(resultSet.getString(2));
                    cp.setTags(resultSet.getString(3));
                    cp.setTitle(resultSet.getString(4));
                    cp.setFirstName(resultSet.getString(5));
                    cp.setMiddleName(resultSet.getString(6));
                    cp.setLastName(resultSet.getString(7));
                    cp.setAddress(resultSet.getString(8));
                    cp.setLatitude(resultSet.getString(9));
                    cp.setLongitude(resultSet.getString(10));
                    cp.setKing(resultSet.getString(11));
                    cp.setPhone(resultSet.getString(12));
                    cp.setKnows(resultSet.getString(13));
                    cp.setDescription(resultSet.getString(14));
                    list.add(cp);
                }
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {
                    if (resultSet != null) resultSet.close();
                    if (preparedStatement != null) preparedStatement.close();
                    if (connect != null) connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    public ContactPojo retrieveContactPojoById(int id) {
        try {
            // 连接数据库
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                    "root", "1234");

            // 删除数据
            String sql = "select * from  Contact where id=? ";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ContactPojo cp = new ContactPojo();
            while(resultSet.next()) {
                cp.setId(resultSet.getInt(1));
                cp.setPerson(resultSet.getString(2));
                cp.setTags(resultSet.getString(3));
                cp.setTitle(resultSet.getString(4));
                cp.setFirstName(resultSet.getString(5));
                cp.setMiddleName(resultSet.getString(6));
                cp.setLastName(resultSet.getString(7));
                cp.setAddress(resultSet.getString(8));
                cp.setLatitude(resultSet.getString(9));
                cp.setLongitude(resultSet.getString(10));
                cp.setKing(resultSet.getString(11));
                cp.setPhone(resultSet.getString(12));
                cp.setKnows(resultSet.getString(13));
                cp.setDescription(resultSet.getString(14));
            }
            return cp;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

        public static void main(String[] args) {
            //ContactPojoDao example = new ContactPojoDao();
//            DomReadXml dr = new DomReadXml();
//            List<ContactPojo> list = dr.readXMLFile();
//            for(ContactPojo c : list) {
//                   example.insertContactPojo(c);
//            }
        }

}
