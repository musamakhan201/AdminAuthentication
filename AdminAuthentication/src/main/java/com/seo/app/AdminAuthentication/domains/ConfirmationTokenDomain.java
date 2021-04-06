package com.seo.app.AdminAuthentication.domains;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admins_confirmation_tokens")
public class ConfirmationTokenDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = AdminRegistrationDomain.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private AdminRegistrationDomain user;

    public ConfirmationTokenDomain(AdminRegistrationDomain user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

}
