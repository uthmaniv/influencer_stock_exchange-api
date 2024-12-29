package com.uthmanIV.ise.user.influencer;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "social_media_account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SocialMediaAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "influencer_id", nullable = false)
    @NotNull(message = "Influencer association is required")
    private Influencer influencer;

    @Column(nullable = false)
    @NotBlank(message = "Platform name is required")
    private String platform;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Social media handle or link is required")
    private String accountHandle;

    @Column(nullable = false)
    private boolean isConnected;

    @Column(nullable = false)
    @PositiveOrZero(message = "Engagement rate must be 0 or greater")
    private double engagementRate;

    @Column(nullable = false)
    @PositiveOrZero(message = "Follower count must be 0 or greater")
    private long followersCount;

    @Column(nullable = false)
    @PositiveOrZero(message = "Post count must be 0 or greater")
    private long postsCount;

    @Column(nullable = false)
    @PositiveOrZero(message = "Average likes per post must be 0 or greater")
    private double averageLikes;

    @Column(nullable = false)
    @PositiveOrZero(message = "Average comments per post must be 0 or greater")
    private double averageComments;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SocialMediaAccount that = (SocialMediaAccount) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

