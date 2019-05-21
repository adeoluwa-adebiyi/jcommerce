package com.vamos.jcommercekt.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Privilege {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String privilege;

  @ManyToMany(mappedBy = "privilege")
  private Set<Role> roles;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
