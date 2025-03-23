package com.joecis.quick_fix.rating;

import com.joecis.quick_fix.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    Long id;
    int value;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public Rating(Long id, int value, User user) {
        this.id = id;
        this.value = value;
        this.user = user;
    }

    public Rating() {
    }

    public Rating(Long id, int value) {
        this.id = id;
        this.value = value;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Rating [id=" + id + ", value=" + value +  "]";
    }

}
