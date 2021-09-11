package com.budymann.container.augmenterservice.sagaparticipants;

import com.budymann.container.augmenterserviceapi.TransactionRewardProcessChannels;
import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;

public class AugmenterServiceProxy {
    public final CommandEndpoint<StartTransactionRewardProcessCommand> startProcess = CommandEndpointBuilder
            .forCommand(StartTransactionRewardProcessCommand.class)
            .withChannel(TransactionRewardProcessChannels.COMMAND_CHANNEL)
            .withReply(Success.class)
            .build();
}
