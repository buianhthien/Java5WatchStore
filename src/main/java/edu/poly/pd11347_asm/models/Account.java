package edu.poly.pd11347_asm.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@Table(name = "accounts")

public class Account implements Serializable {
    @Id
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String photo;
    private boolean activated;
    private boolean isAdmin;
    @OneToMany(mappedBy = "account")
    private List<Order> orders;
}
