package br.com.pucminas.hospital.config;

import br.com.pucminas.hospital.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CronConfig {

    @Autowired
    SmsService smsService;

    @Scheduled(cron = "0 1/1 * 1/1 * ?")
    public void sendMessageCron() {
        smsService.dailyCallAssessment();
    }

}