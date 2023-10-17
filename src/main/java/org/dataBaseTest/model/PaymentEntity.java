package org.dataBaseTest.model;

import java.util.Date;


import jakarta.persistence.*;


@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    @GeneratedValue
    private int idPayment;
    private float bonus;
    private Date dateOfPayment;
    private float pay;

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private UsersEntity user;

    public PaymentEntity(int idPayment, float bonus, Date dateOfPayment, float pay, UsersEntity user) {
        this.idPayment = idPayment;
        this.bonus = bonus;
        this.dateOfPayment = dateOfPayment;
        this.pay = pay;
        this.user = user;
    }

    public PaymentEntity() {
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idpayment) {
        this.idPayment = idpayment;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public float getPay() {
        return pay;
    }

    public void setPay(float pay) {
        this.pay = pay;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
