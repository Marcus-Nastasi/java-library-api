package com.library.app.Models.Rents;

import com.library.app.Models.Members.Member;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column
    private BigInteger book_id;
    @Column
    private LocalDate emit_date;
    @Column
    private LocalDate return_date;
    @Column
    private String librarian_id;
    @Column
    private BigInteger member_id;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Rent() {}

    public Rent(BigInteger book_id, String librarian_id, BigInteger member_id, Member member) {
        this.emit_date = LocalDate.now();
        this.return_date = emit_date.plusDays(7);
        this.book_id = book_id;
        this.librarian_id = librarian_id;
        this.member_id = member_id;
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getBook_id() {
        return book_id;
    }

    public void setBook_id(BigInteger book_id) {
        this.book_id = book_id;
    }

    public LocalDate getEmit_date() {
        return emit_date;
    }

    public void setEmit_date(LocalDate emit_date) {
        this.emit_date = emit_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public String getLibrarian_id() {
        return librarian_id;
    }

    public void setLibrarian_id(String librarian_id) {
        this.librarian_id = librarian_id;
    }

    public BigInteger getMember_id() {
        return member_id;
    }

    public void setMember_id(BigInteger member_id) {
        this.member_id = member_id;
    }
}




