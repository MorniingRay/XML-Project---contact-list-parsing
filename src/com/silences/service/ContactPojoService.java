package com.silences.service;

import com.silences.dao.ContactPojoDao;
import com.silences.entity.ContactPojo;
import java.util.List;

// 业务层
public class ContactPojoService {

    ContactPojoDao contactPojoDao = new ContactPojoDao();

    // 查询所有
    public List<ContactPojo> getContactPojos() {
        return contactPojoDao.retrieveContactPojo();
    }
    // 单个查询
    public ContactPojo getContactPojo(int id) {
        return contactPojoDao.retrieveContactPojoById(id);
    }
    // 添加
    public void insertContactPojo(ContactPojo contactPojo){
        contactPojoDao.insertContactPojo(contactPojo);
    }
    // 修改
    public void updateContactPojo(ContactPojo contactPojo){
        contactPojoDao.updateContactPojo(contactPojo);
    }
    // 删除
    public void deleteContactPojo(int id){
        contactPojoDao.deleteContactPojo(id);
    }
}
