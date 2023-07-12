package com.example.permission;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Role extends  BaseEntity{
    private String name;
}
