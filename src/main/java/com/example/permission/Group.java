package com.example.permission;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "user_groups")
@Data
public class Group extends BaseEntity{

    private String name;

    @ManyToMany
    @JoinTable(
            name = "groups_roles",
            joinColumns = @JoinColumn(
                    name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();
}
