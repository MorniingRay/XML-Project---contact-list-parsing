package com.silences.entity;

public class ContactPojo {

    private int id;// 序号
    private String person;// 姓名
    private String tags;// 标签

    private String title;// 名字标题
    private String firstName;// 名字的第一
    private String middleName;// 名字的第二
    private String lastName;// 名字的第三

    private String address;// 地址
    private String latitude;// 纬度
    private String longitude;// 经度

    private String king;// 电话备注
    private String phone;// 电话号码
    private String knows;// 联系人

    private String description;// 描述

    public ContactPojo() {
    }

    public ContactPojo(int id, String person, String tags, String title, String firstName, String middleName, String lastName, String address, String latitude, String longitude, String king, String phone, String knows, String description) {
        this.id = id;
        this.person = person;
        this.tags = tags;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.king = king;
        this.phone = phone;
        this.knows = knows;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ContactPojo{" +
                "person='" + person + '\'' +
                ", tags='" + tags + '\'' +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", king='" + king + '\'' +
                ", phone='" + phone + '\'' +
                ", knows='" + knows + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getKing() {
        return king;
    }

    public void setKing(String king) {
        this.king = king;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKnows() {
        return knows;
    }

    public void setKnows(String knows) {
        this.knows = knows;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
