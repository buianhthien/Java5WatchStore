package edu.poly.pd11347_asm.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long orderId;
private String address;
@Temporal(TemporalType.DATE)
@Column(name = "CreateDate")
Date createDate = new Date();
@ManyToOne
@JoinColumn(name = "Username")
Account account;
@OneToMany(mappedBy = "order")
List<OrderDetail> orderDetails;
}
