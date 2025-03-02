package by.drdeck.usersub.infrastructure;

import by.drdeck.usersub.dto.SubscriptionDto;
import by.drdeck.usersub.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    Subscription toEntity(SubscriptionDto subscriptionDto);
    SubscriptionDto toDto(Subscription subscription);
}
