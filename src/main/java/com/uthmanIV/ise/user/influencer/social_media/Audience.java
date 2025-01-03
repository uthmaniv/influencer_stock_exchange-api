package com.uthmanIV.ise.user.influencer.social_media;

import com.uthmanIV.ise.user.influencer.Influencer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "audiences")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Audience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "influencer_id", nullable = false)
    @NotNull(message = "Influencer association is required")
    private Influencer influencer;

    @ElementCollection
    @CollectionTable(name = "audience_industry", joinColumns = @JoinColumn(name = "audience_id"))
    @Column(name = "industry")
    @NotEmpty(message = "At least one industry is required")
    private List<String> industries;

    @ElementCollection
    @CollectionTable(name = "audience_content_type", joinColumns = @JoinColumn(name = "audience_id"))
    @Column(name = "content_type")
    @NotEmpty(message = "At least one content type is required")
    private List<String> contentTypes;

    @Column(name = "age_range", nullable = false)
    @NotBlank(message = "Age range is required")
    private String ageRange;

    @Column(name = "region", nullable = false)
    @NotBlank(message = "Region is required")
    private String region;

    @Column(name = "gender", nullable = false)
    @NotBlank(message = "Gender preference is required")
    private String gender;

    @Column(name = "engagement_frequency", nullable = false)
    @NotBlank(message = "Engagement frequency is required")
    private String engagementFrequency;
}
