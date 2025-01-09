package com.uthmanIV.ise.user.influencer.social_media;

import com.uthmanIV.ise.user.influencer.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AudienceRepository extends JpaRepository<Audience,Long> {

}
