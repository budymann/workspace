package com.budymann.container.augmenterservice.messagehandlers;

import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SagaParticipantConfiguration.class, TramEventsPublisherConfiguration.class, SagaParticipantConfiguration.class})
public class TransactionRewardProcessCommandHandlersConfiguration {
    @Bean
    public TransactionRewardProcessCommandHandlers transactionRewardProcessCommandHandlers() {
        return new TransactionRewardProcessCommandHandlers();
    }

    @Bean
    public SagaCommandDispatcher orderCommandHandlersDispatcher(TransactionRewardProcessCommandHandlers transactionRewardProcessCommandHandlers, SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
        return sagaCommandDispatcherFactory.make("augmenterService", transactionRewardProcessCommandHandlers.commandHandlers());
    }
}
