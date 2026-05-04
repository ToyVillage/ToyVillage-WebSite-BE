package com.command.toyvillage_server.global.aws;

import com.command.toyvillage_server.global.aws.s3.AwsS3Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwsDeleteScheduler {
    private final AwsS3Provider awsS3Provider;

    @Scheduled(cron = "0 0 3 * * MON")
    public void cleanupOrphanedObjects() {
        awsS3Provider.cleanupOrphanedObjects();
    }
}
