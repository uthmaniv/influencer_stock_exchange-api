package com.uthmanIV.ise.influencer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/influencer")
@RequiredArgsConstructor
public class InfluencerController {

    private InfluencerService influencerService;
}
