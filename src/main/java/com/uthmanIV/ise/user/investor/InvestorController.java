package com.uthmanIV.ise.user.investor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investor")
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService investorService;
}
