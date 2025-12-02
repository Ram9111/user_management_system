package com.management.userManagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * =========================================================================================================
 *  Description   : Use the store the values of users which is register
 *  Author        : Ram Choudhary
 *  Created Date  : 30-Nav-2025
 *  Version       : 1.0
 * =========================================================================================================
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private int userId;

    @Column(name = "User_Name")
    private String userName;

    @Column(name = "Password")
    private String password;

    @Column(name = "User_Email")
    private String userEmail;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoleId = new HashSet<>();

    @Column(name = "Create_Date")
    private LocalDateTime createDate;

    @Column(name = "Modify_Date")
    private LocalDateTime modifyDate;

    @Column(name = "Create_User_Id")
    private Integer createUserId;

    @Column(name = "Modify_User_Id")
    private Integer modifyUserId;

    @Column(name = "Active_Flag")
    private Integer activeFlag = 1;

    @Column(name = "Enable_Flag")
    private Integer enableFlag;

    // ---------------------------
    // Constructors


    public User() {
        // No-argument constructor (required by JPA)
    }

    public User(int userId, String userName, String password, Set<Role> userRoleId,  String userEmail,
                LocalDateTime createDate, LocalDateTime modifyDate, Integer createUserId,
                Integer modifyUserId, Integer activeFlag, Integer enableFlag) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userRoleId = userRoleId;
        this.userEmail = userEmail;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.createUserId = createUserId;
        this.modifyUserId = modifyUserId;
        this.activeFlag = activeFlag;
        this.enableFlag = enableFlag;
    }

    // ---------------------------
    // Getters and Setters
    // ---------------------------

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserRoleId(Set<Role> userRoleId) {
        this.userRoleId = userRoleId;
    }


    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Integer getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }
    public Set<Role> getUserRoleId() {
        return userRoleId;
    }

}
