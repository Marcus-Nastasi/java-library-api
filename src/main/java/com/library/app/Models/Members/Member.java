package com.library.app.Models.Members;

import com.library.app.Enums.Members.MemberType;
import com.library.app.Models.Rents.Rent;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column
    private String name;
    @Column
    private String cpf;
    @Column
    @Enumerated(value = EnumType.ORDINAL)
    private MemberType type;
    @Column(name = "date_of_membership")
    private LocalDate dateOfMembership;
    @Column(name = "books_issued")
    private int booksIssued;
    @Column(name = "books_limit")
    private int booksLimit;
    @Column
    private String phone;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rents;

    public Member() {}

    public Member(String name, String cpf, MemberType type, LocalDate dateOfMembership, int booksIssued, int booksLimit, String phone) {
        this.name = name;
        this.cpf = cpf;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.booksIssued = booksIssued;
        this.booksLimit = booksLimit;
        this.phone = phone;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public int getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(int booksIssued) {
        this.booksIssued = booksIssued;
    }

    public int getBooksLimit() {
        return booksLimit;
    }

    public void setBooksLimit(int booksLimit) {
        this.booksLimit = booksLimit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


