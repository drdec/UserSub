package by.drdeck.usersub.controller;

import by.drdeck.usersub.dto.SubscriptionDto;
import by.drdeck.usersub.model.Subscription;
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
    public ResponseEntity<Subscription> addSubscription(@PathVariable Long userId, @RequestBody SubscriptionDto subscriptionDto) {
        logger.info("Получен запрос на подписку от пользователя {}", userId);
        try {
            Subscription subscription = subscriptionService.addSubscription(userId, subscriptionDto);
            logger.info("Подписка добавлена");
            return ResponseEntity.ok(subscription);
        } catch (RuntimeException e) {
            logger.error("Ошибка при создании подписки для userId={}: {}", userId, e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable Long userId) {
        logger.info("Запрос на получение списка подписок от пользователя {}", userId);
        List<Subscription> subscription = subscriptionService.getSubscriptionsByUserId(userId);
        logger.info("Список подписок получен для пользователя {}", userId);
        return ResponseEntity.ok(subscription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        logger.info("Запрос на удаление подписки с id {}", id);
        subscriptionService.deleteSubscription(id);
        logger.info("Подписка с id {} удалена", id);
        return ResponseEntity.noContent().build();
    }
}
