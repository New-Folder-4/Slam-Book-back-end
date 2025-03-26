package com.system.slam.scheduler;

import com.system.slam.entity.list.UserExchangeList;
import com.system.slam.repository.list.UserExchangeListRepository;
import com.system.slam.service.list.ExchangeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class TrackNumberScheduler {

    private final ExchangeListService exchangeListService;
    private final UserExchangeListRepository userExchangeListRepository;

    @Autowired
    public TrackNumberScheduler(ExchangeListService exchangeListService,
                                UserExchangeListRepository userExchangeListRepository) {
        this.exchangeListService = exchangeListService;
        this.userExchangeListRepository = userExchangeListRepository;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void checkTrackNumber() {
        List<UserExchangeList> overdue6 = exchangeListService.findOverdueExchangesWithoutTrack(6);
        overdue6.forEach(exchangeListService::sendReminderToUser);

        List<UserExchangeList> overdue7 = exchangeListService.findOverdueExchangesWithoutTrack(7);
        overdue7.forEach(exchangeListService::blockParticipant);
    }

    private List<UserExchangeList> findUserExchangeWithoutTrackMoreThanDays(int days) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
        return userExchangeListRepository.findAll().stream()
                .filter(ue -> ue.getTrackNumber() == null || ue.getTrackNumber().trim().isEmpty())
                .filter(ue -> ue.getExchangeList() != null && ue.getExchangeList().isBoth())
                .filter(ue -> ue.getExchangeList().getCreateAt().isBefore(cutoff))
                .collect(Collectors.toList());
    }

}
