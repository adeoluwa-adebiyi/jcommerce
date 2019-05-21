package com.vamos.jcommercekt.models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String role;

  @ManyToMany
  @JoinTable(joinColumns = @JoinColumn(name="role_id"),
          inverseJoinColumns = @JoinColumn(name="privilege_id"))
  private Set<Privilege> privilege;


  public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
