package org.llamawow.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.*;
import java.time.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class AccountEntity implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "salt")
    private byte[] salt;
    @Column(name = "verifier")
    private byte[] verifier;
    @Column(name = "session_key_auth")
    private byte[] keyauth;
    @Column(name = "session_key_bnet")
    private String keybnet;
    @Column(name = "totp_secret")
    private String otpSecret;
    @Column(name = "email")
    private String email;
    @Column(name = "reg_mail")
    private String regemail;
    @Column(name = "joindate")
    private LocalDate joinDate;
    @Column(name = "last_ip")
    private String lastIp;
    @Column(name = "last_attempt_ip")
    private String lastattempIp;
    @Column(name = "failed_logins")
    private String failedLogins;
    private boolean locked;
    @Column(name = "lock_country")
    private String lockcountry;
    @Column(name = "last_login")
    private LocalDate lastLogin;
    private boolean online;
    private String expansion;
    @Column(name = "mutetime")
    private Long muteTime;
    @Column(name = "mutereason")
    private String muteReason;
    @Column(name = "muteby")
    private String muteBy;
    private int locale;
    private String os;
    @Column(name = "timezone_offset")
    private int timezone;
    @Column(name = "recruiter")
    private int recruiter;


    @PrePersist
    public void prePersist() {
        this.joinDate = LocalDate.now();
        this.failedLogins = "0";
        this.lastIp = "127.0.0.1";
        this.lastattempIp="127.0.0.1";
        this.os = "";
        this.username=username.toUpperCase();
        this.lockcountry = "00";
        this.regemail="";
        this.muteBy = "";
        this.muteReason = "";
        this.expansion = "2";
        this.muteTime = 0L;
    }

    public AccountEntity() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
