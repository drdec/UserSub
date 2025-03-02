package by.drdeck.usersub.service;

import by.drdeck.usersub.dto.SubscriptionDto;
import by.drdeck.usersub.infrastructure.SubscriptionMapper;
import by.drdeck.usersub.model.Subscription;
import by.drdeck.usersub.model.User;
import by.drdeck.usersub.repository.SubscriptionRepository;
import by.drdeck.usersub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;

    public Subscription addSubscription(Long userId, SubscriptionDto subscriptionDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subscription subscription = subscriptionMapper.toEntity(subscriptionDto);
        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }
}
