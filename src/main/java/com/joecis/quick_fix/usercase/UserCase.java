package com.joecis.quick_fix.usercase;

import java.time.LocalDateTime;

import com.joecis.quick_fix.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

public class UserCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Size(max = 40)    
    @Column(nullable = false)
    private String title;
    
    @Size(max = 255)
    @Column(nullable = false)
    private String description; 
    
    private LocalDateTime create_date;
    private LocalDateTime modified_date;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;


}
