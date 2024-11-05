package com.springproject.refersphere.controller.Referral;

import com.springproject.refersphere.Utils.ReferralBody;
import com.springproject.refersphere.Utils.ReferralResponse;
import com.springproject.refersphere.model.Referral;
import com.springproject.refersphere.service.ReferralService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/referral")
@RequiredArgsConstructor
public class ReferralController {

    private final ReferralService referralService;

    @PostMapping("/create")
    public ResponseEntity<String> createReferralPost(@RequestBody ReferralBody referralBody) {
        referralService.create(referralBody);
        return ResponseEntity.ok("Hello from secured endpoint, Referral Post Created");
    }

    @GetMapping
    public ResponseEntity<List<Referral>> findAllBooks() {
        return ResponseEntity.ok(referralService.findAll());
    }

    @PostMapping("/apply")
    public ResponseEntity<String> appleReferral(@RequestParam(required = true)Long id) {
        referralService.apply(id);
        return ResponseEntity.ok("Hello from secured endpoint, Referral applied for post id: "+id);
    }

    @GetMapping("/applied")
    public ResponseEntity<List<ReferralResponse>> appliedReferrals() {
        return ResponseEntity.ok(referralService.findApplied());
    }
}
