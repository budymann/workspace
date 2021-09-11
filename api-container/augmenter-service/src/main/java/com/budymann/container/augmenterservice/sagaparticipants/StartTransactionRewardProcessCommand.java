package com.budymann.container.augmenterservice.sagaparticipants;

public class StartTransactionRewardProcessCommand extends TransactionRewardProcessCommand {
    public StartTransactionRewardProcessCommand(long transactionId){
        super(transactionId);
    }
}
