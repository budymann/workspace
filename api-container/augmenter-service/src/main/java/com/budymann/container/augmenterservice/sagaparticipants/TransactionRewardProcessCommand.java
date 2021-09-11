package com.budymann.container.augmenterservice.sagaparticipants;

import io.eventuate.tram.commands.common.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRewardProcessCommand implements Command {
    private long transactionId;
}
