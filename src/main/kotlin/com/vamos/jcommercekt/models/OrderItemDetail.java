package com.vamos.jcommercekt.models;

import javax.persistence.*;

enum PaymentMethod{
    PAYMENT_ON_DELIVERY,
    PAYMENT_BEFORE_DELIVERY
}

enum ShippingDelivery{
    LOCAL,
    INTER_STATE,
    COURIER
}

@Entity
public class OrderItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private java.sql.Date date;
  private String invoiceAmount;
  private PaymentMethod paymentMethod;
  private ShippingDelivery shippingdetail;
  private long status;
  private long customerId;

  @ManyToOne
  @JoinColumn(name="order_id")
  private Order order;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }


  public String getInvoiceAmount() {
    return invoiceAmount;
  }

  public void setInvoiceAmount(String invoiceAmount) {
    this.invoiceAmount = invoiceAmount;
  }


    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShippingDelivery getShippingdetail() {
        return shippingdetail;
    }

    public void setShippingdetail(ShippingDelivery shippingdetail) {
        this.shippingdetail = shippingdetail;
    }

    public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

}
