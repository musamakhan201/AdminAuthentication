package com.seo.app.AdminAuthentication.domains;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "admins")
public class AdminRegistrationDomain implements Serializable {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int user_id;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String username;
    private String password;
    private boolean loggedIn;
    @Column(columnDefinition = "DATETIME")
    private String created_date;
    public AdminRegistrationDomain() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        created_date = utc.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }
}
