package by.drdeck.usersub.controller;

import by.drdeck.usersub.dto.SubscriptionDto;
import by.drdeck.usersub.model.Subscription;
import by.drdeck.usersub.repository.SubscriptionRepository;
import by.drdeck.usersub.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @PostMapping("/user/{userId}")
    public ResponseEntity<Subscription> addSubscription(@PathVariable Long userId, @RequestBody SubscriptionDto subscription) {
        try {
            return ResponseEntity.ok(subscriptionService.addSubscription(userId, subscription));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }
}
