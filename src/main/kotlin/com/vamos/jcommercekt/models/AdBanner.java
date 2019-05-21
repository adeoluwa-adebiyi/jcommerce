package com.vamos.jcommercekt.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;

@Entity
public class AdBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private long displayStatus;
  private Blob pngData;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getDisplayStatus() {
    return displayStatus;
  }

  public void setDisplayStatus(long displayStatus) {
    this.displayStatus = displayStatus;
  }

    public Blob getPngData() {
        return pngData;
    }

    public void setPngData(Blob pngData) {
        this.pngData = pngData;
    }
}
