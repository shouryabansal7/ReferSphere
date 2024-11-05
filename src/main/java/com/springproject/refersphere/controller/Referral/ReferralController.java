package com.springproject.refersphere.controller.Referral;

import com.springproject.refersphere.Utils.AppliedReferralsToPost;
import com.springproject.refersphere.Utils.ReferralBody;
import com.springproject.refersphere.Utils.ReferralPost;
import com.springproject.refersphere.Utils.ReferralResponse;
import com.springproject.refersphere.model.Referral;
import com.springproject.refersphere.service.ReferralService;
import lombok.RequiredArgsConstructor;
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
        return ResponseEntity.ok(referralService.apply(id));
    }

    @GetMapping("/applied")
    public ResponseEntity<List<ReferralResponse>> appliedReferrals() {
        return ResponseEntity.ok(referralService.findApplied());
    }

    @GetMapping("/post")
    public ResponseEntity<ReferralPost> referralById(@RequestParam(required = true)Long id) {
        return ResponseEntity.ok(referralService.findReferral(id));
    }

    @PostMapping("/change")
    public ResponseEntity<String> referralById(@RequestParam(required = true)Long id, @RequestParam(required = true)String status,@RequestParam Long candidateId) {
        return ResponseEntity.ok(referralService.changeStatus(id,status,candidateId));
    }

    @GetMapping("/posts/user")
    public ResponseEntity<List<AppliedReferralsToPost>> applicationsOnReferralPostByUser() {
        return ResponseEntity.ok(referralService.applicationsOnReferralPostByUser());
    }
}
