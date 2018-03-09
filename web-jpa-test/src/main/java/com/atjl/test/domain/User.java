package com.atjl.test.domain;


import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findByName", query = "select name,address from User u where u.name=?1")
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue
    private Long id;
    @Column(name = "name")
    String name;
    @Column(name = "address")
    String address;

}

