package com.uthmanIV.ise.user.influencer;

import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.influencer.social_media.Audience;
import com.uthmanIV.ise.user.influencer.social_media.SocialMediaAccount;
import com.uthmanIV.ise.user.wallet.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "influencer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Influencer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @NotNull(message = "User association is required")
    private User user;

//    @Column(name = "fullName")
//    private String fullName;

    @Column(name = "stock_symbol", nullable = false)
    @NotBlank(message = "Stock symbol is required")
    @Size(max = 10, message = "Stock symbol cannot exceed 10 characters")
    private String stockSymbol;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Column(name = "country", nullable = false)
    @NotBlank(message = "Country is required")
    private String country;

    @Column(name = "state", nullable = false)
    @NotBlank(message = "State is required")
    private String state;

    @Column(name = "city", nullable = false)
    @NotBlank(message = "City is required")
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    @NotNull(message = "Gender is required")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "influencer_type", nullable = false)
    @NotNull(message = "Influencer type is required")
    private InfluencerType influencerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "influencer_tier", nullable = false)
    @NotNull(message = "Influencer Tier is required")
    private InfluencerTier influencerTier;

    @Column(name = "ethnicity", nullable = false)
    @NotBlank(message = "Ethnicity is required")
    private String ethnicity;

    @Column(name = "language", nullable = false)
    @NotBlank(message = "Language is required")
    private String language;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SocialMediaAccount> socialMediaAccounts;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Audience> audienceDetails;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Influencer that = (Influencer) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer()
                .getPersistentClass().hashCode() : getClass().hashCode();
    }
}
