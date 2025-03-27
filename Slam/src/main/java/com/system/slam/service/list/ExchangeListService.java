package com.system.slam.service.list;

import com.system.slam.entity.Status;
import com.system.slam.entity.User;
import com.system.slam.entity.list.ExchangeList;
import com.system.slam.entity.list.OfferList;
import com.system.slam.entity.list.UserExchangeList;
import com.system.slam.entity.list.WishList;
import com.system.slam.repository.StatusRepository;
import com.system.slam.repository.list.ExchangeListRepository;
import com.system.slam.repository.list.OfferListRepository;
import com.system.slam.repository.list.UserExchangeListRepository;
import com.system.slam.repository.list.WishListRepository;
import com.system.slam.service.EmailService;
import com.system.slam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExchangeListService {

    private final ExchangeListRepository exchangeListRepository;
    private final OfferListRepository offerListRepository;
    private final WishListRepository wishListRepository;
    private final UserExchangeListRepository userExchangeListRepository;
    private final StatusRepository statusRepository;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public ExchangeListService(ExchangeListRepository exchangeListRepository,
                               OfferListRepository offerListRepository,
                               WishListRepository wishListRepository,
                               UserExchangeListRepository userExchangeListRepository,
                               StatusRepository statusRepository,
                               UserService userService,
                               EmailService emailService) {
        this.exchangeListRepository = exchangeListRepository;
        this.offerListRepository = offerListRepository;
        this.wishListRepository = wishListRepository;
        this.userExchangeListRepository = userExchangeListRepository;
        this.statusRepository = statusRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    public ExchangeList proposeExchange(Long offerId, Long wishId) {
        OfferList offer1 = offerListRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found: " + offerId));

        WishList wish1 = wishListRepository.findById(wishId)
                .orElseThrow(() -> new RuntimeException("Wish not found: " + wishId));

        ExchangeList exchange = new ExchangeList();
        exchange.setOfferList1(offer1);
        exchange.setWishList1(wish1);
        exchange.setOfferList2(null);
        exchange.setWishList2(null);
        exchange.setCreateAt(LocalDateTime.now());
        exchange.setBoth(false);

        exchangeListRepository.save(exchange);

        Status reservedStatus = statusRepository.findByName("reserved")
                .orElseThrow(() -> new RuntimeException("Status 'reserved' not found"));

        offer1.setStatus(reservedStatus);
        offer1.setUpdateAt(LocalDateTime.now());
        offerListRepository.save(offer1);

        wish1.setStatus(reservedStatus);
        wish1.setUpdateAt(LocalDateTime.now());
        wishListRepository.save(wish1);

        return exchange;
    }

    public ExchangeList confirmExchange(Long exchangeId,
                                        Long secondOfferId,
                                        Long secondWishId) {
        ExchangeList exchange = exchangeListRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Exchange not found: " + exchangeId));

        OfferList offer2 = offerListRepository.findById(secondOfferId)
                .orElseThrow(() -> new RuntimeException("Offer2 not found: " + secondOfferId));
        WishList wish2 = wishListRepository.findById(secondWishId)
                .orElseThrow(() -> new RuntimeException("Wish2 not found: " + secondWishId));

        exchange.setOfferList2(offer2);
        exchange.setWishList2(wish2);
        exchange.setBoth(true);
        exchange.setCreateAt(LocalDateTime.now());
        exchangeListRepository.save(exchange);

        Status confirmedStatus = statusRepository.findByName("confirmed")
                .orElseThrow(() -> new RuntimeException("Status 'confirmed' not found"));

        offer2.setStatus(confirmedStatus);
        offer2.setUpdateAt(LocalDateTime.now());
        offerListRepository.save(offer2);

        wish2.setStatus(confirmedStatus);
        wish2.setUpdateAt(LocalDateTime.now());
        wishListRepository.save(wish2);

        createUserExchangeListRecord(exchange, exchange.getOfferList1());
        createUserExchangeListRecord(exchange, exchange.getOfferList2());

        return exchange;
    }

    private void createUserExchangeListRecord(ExchangeList exchange, OfferList offer) {
        UserExchangeList uex = new UserExchangeList();
        uex.setExchangeList(exchange);
        uex.setOfferList(offer);
        uex.setTrackNumber(null);
        uex.setReceiving(false);
        userExchangeListRepository.save(uex);
    }

    public UserExchangeList setTrackNumber(Long exchangeId,
                                           Long userId,
                                           String trackNumber) {
        ExchangeList exchange = exchangeListRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Exchange not found: " + exchangeId));

        OfferList userOffer; // -
        if (exchange.getOfferList1() != null &&
                exchange.getOfferList1().getUser().getIdUser().equals(userId)) {
            userOffer = exchange.getOfferList1();
        }
        else if (exchange.getOfferList2() != null &&
                exchange.getOfferList2().getUser().getIdUser().equals(userId)) {
            userOffer = exchange.getOfferList2();
        } else {
            userOffer = null;
        }
        if (userOffer == null) {
            throw new RuntimeException("User is not a participant in this exchange.");
        }

        UserExchangeList uex = userExchangeListRepository
                .findAll().stream()
                .filter(ue -> ue.getExchangeList().getIdExchangeList().equals(exchangeId))
                .filter(ue -> ue.getOfferList().getIdOfferList().equals(userOffer.getIdOfferList()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UserExchangeList record not found"));

        uex.setTrackNumber(trackNumber);
        userExchangeListRepository.save(uex);

        Status shippedStatus = statusRepository.findByName("shipped")
                .orElseThrow(() -> new RuntimeException("Status 'shipped' not found"));
        userOffer.setStatus(shippedStatus);
        userOffer.setUpdateAt(LocalDateTime.now());
        offerListRepository.save(userOffer);

        notifyOtherParticipantWithTrack(exchange, userOffer, trackNumber);

        return uex;
    }

    public String getTrackNumber(Long exchangeId, Long userId) {
        if (exchangeId != null && userId != null) {
            UserExchangeList uex = getUserExchangeList(exchangeId, userId);
            return (uex != null
                    && uex.getTrackNumber() != null
                    && !uex.getTrackNumber().isEmpty())
                    ? uex.getTrackNumber() : "";
        }
        return "";
    }

    public UserExchangeList getUserExchangeList(Long exchangeId, Long userId) {
        ExchangeList exchange = exchangeListRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Exchange not found: " + exchangeId));
        OfferList userOffer;
        if (exchange.getOfferList1().getUser().getIdUser().equals(userId)) {
            userOffer = exchange.getOfferList1();
        } else if (exchange.getOfferList2() != null
                && exchange.getOfferList2().getUser().getIdUser().equals(userId)) {
            userOffer = exchange.getOfferList2();
        } else {
            userOffer = null;
        }
        if (userOffer == null) {
            throw new RuntimeException("User is not a participant in this exchange.");
        }
        return userExchangeListRepository.findAll().stream()
                .filter(ue -> ue.getExchangeList().getIdExchangeList().equals(exchangeId))
                .filter(ue -> ue.getOfferList().getIdOfferList().equals(userOffer.getIdOfferList()))
                .findFirst()
                .orElse(null);
    }

    public List<UserExchangeList> findOverdueExchangesWithoutTrack(int daysLimit) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(daysLimit);

        return userExchangeListRepository.findAll().stream()
                .filter(ue -> ue.getTrackNumber() == null)
                .filter(ue -> {
                    ExchangeList exch = ue.getExchangeList();
                    return exch.isBoth()
                            && exch.getCreateAt().isBefore(cutoff);
                })
                .toList();
    }

    public void sendReminderToUser(UserExchangeList uex) {
        User user = uex.getOfferList().getUser();
        if (user == null) {
            System.out.println("No user in offerList");
            return;
        }
        String email = user.getEmail();
        if (email == null || email.isEmpty()) {
            System.out.println("User has no email, userId=" + user.getIdUser());
            return;
        }

        String subject = "Напоминание о вводе трек-номера";
        String text = "Уважаемый(ая) " + user.getFirstName()
                + ",\n\n"
                + "У вас есть обмен № " + uex.getExchangeList().getIdExchangeList()
                + ", для которого вы не ввели трек-номер. "
                + "Пожалуйста, в личном кабинете укажите его.\n\n"
                + "С уважением, Администрация Book-changer";

        emailService.sendEmail(email, subject, text);

        System.out.println("SEND REMINDER: userId=" + user.getIdUser()
                + ", email=" + email
                + ", exchangeId=" + uex.getExchangeList().getIdExchangeList());
    }

    private void notifyOtherUserReceive(ExchangeList exchange, OfferList userOffer) {
        OfferList otherOffer = (userOffer == exchange.getOfferList1())
                ? exchange.getOfferList2()
                : exchange.getOfferList1();
        if (otherOffer == null || otherOffer.getUser() == null) {
            return;
        }
        User otherUser = otherOffer.getUser();
        if (otherUser.getEmail() != null && !otherUser.getEmail().isEmpty()) {
            String subject = "Участник обмена подтвердил получение книги";
            String text = "Обмен № " + exchange.getIdExchangeList()
                    + ": участник обмена (userId=" + userOffer.getUser().getIdUser()
                    + ") подтвердил, что книга получена.\n\n"
                    + "Можете убедиться в личном кабинете!";
            emailService.sendEmail(otherUser.getEmail(), subject, text);
        }
        System.out.println("NOTIFY -> userId=" + otherUser.getIdUser()
                + ": участник обмена подтвердил получение книги в обмене "
                + exchange.getIdExchangeList());
    }

    public void blockParticipant(UserExchangeList uex) {
        Long userId = uex.getOfferList().getUser().getIdUser();
        userService.blockUser(userId);
        System.out.println("Пользователь " + userId
                + " заблокирован из-за отсутствия номера трека во времени.");
    }

    private void successShipmentNotify(Long userId, Long exchangeId) {
        System.out.println("Участник обмена " + userId
                + " ввёл трек-номер для обмена." + exchangeId);
    }

    private void notifyOtherParticipantWithTrack(ExchangeList exchange,
                                                 OfferList userOffer, String trackNumber) {
        OfferList otherOffer = (userOffer == exchange.getOfferList1())
                ? exchange.getOfferList2()
                : exchange.getOfferList1();
        if (otherOffer == null || otherOffer.getUser() == null) {
            return;
        }
        User otherUser = otherOffer.getUser();
        if (otherUser.getEmail() == null || otherUser.getEmail().isEmpty()) {
            return;
        }
        String email = otherUser.getEmail();
        String subject = "Участник обмена указал трек-номер";
        String text = "Вам отправили трек: " + trackNumber
                + ", обмен №" + exchange.getIdExchangeList();
        emailService.sendEmail(email, subject, text);
    }

    private void sendInAppNotificationForOtherParticipant(ExchangeList exchange,
                                                          OfferList userOffer,
                                                          String trackNumber) {
        OfferList otherOffer = (userOffer == exchange.getOfferList1())
                ? exchange.getOfferList2()
                : exchange.getOfferList1();
        if (otherOffer != null && otherOffer.getUser() != null) {
            System.out.println("In-app notify -> userId=" + otherOffer.getUser().getIdUser()
                    + ": участник обмена указал трек-номер=" + trackNumber);
        }
    }

    public void reportToAdmin(Long exchangeId, Long userId, String message) {
        System.out.println("REPORT TO ADMIN: user=" + userId + ", exchange="
                + exchangeId + ", text=" + message);
    }

    private boolean bothUsersReceived(Long exchangeId) {
        List<UserExchangeList> all = userExchangeListRepository.findAll().stream()
                .filter(ue -> ue.getExchangeList().getIdExchangeList().equals(exchangeId))
                .toList();
        return all.size() == 2 && all.stream().allMatch(UserExchangeList::isReceiving);
    }

    private void completeExchange(ExchangeList exchange) {
        Status completed = statusRepository.findByName("completed")
                .orElseThrow(() -> new RuntimeException("Status 'completed' not found"));

        if (exchange.getOfferList1() != null) {
            exchange.getOfferList1().setStatus(completed);
            offerListRepository.save(exchange.getOfferList1());
        }
        if (exchange.getOfferList2() != null) {
            exchange.getOfferList2().setStatus(completed);
            offerListRepository.save(exchange.getOfferList2());
        }
        exchangeListRepository.save(exchange);
    }

    private OfferList findOfferBelongsToUser(ExchangeList exchange, Long userId) {
        if (exchange.getOfferList1() != null
                && exchange.getOfferList1().getUser().getIdUser().equals(userId)) {
            return exchange.getOfferList1();
        } else if (exchange.getOfferList2() != null
                && exchange.getOfferList2().getUser().getIdUser().equals(userId)) {
            return exchange.getOfferList2();
        }
        return null;
    }

    public ExchangeList receiveBook(Long exchangeId, Long userId) {
        ExchangeList exchange = exchangeListRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("Exchange not found: " + exchangeId));

        OfferList userOffer = findOfferBelongsToUser(exchange, userId);
        if (userOffer == null) {
            throw new RuntimeException("User is not a participant in this exchange.");
        }

        UserExchangeList uex = userExchangeListRepository.findAll().stream()
                .filter(ue -> ue.getExchangeList().getIdExchangeList().equals(exchangeId))
                .filter(ue -> ue.getOfferList().getIdOfferList().equals(userOffer.getIdOfferList()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UserExchangeList not found"));

        uex.setReceiving(true);
        userExchangeListRepository.save(uex);
        notifyOtherUserReceive(exchange, userOffer);

        if (bothUsersReceived(exchangeId)) {
            completeExchangeNoRating(exchange);
        }
        return exchange;
    }

    private void completeExchangeNoRating(ExchangeList exchange) {
        Status completed = statusRepository.findByName("completed")
                .orElseThrow(() -> new RuntimeException("Status 'completed' not found"));
        if (exchange.getOfferList1() != null) {
            exchange.getOfferList1().setStatus(completed);
            offerListRepository.save(exchange.getOfferList1());
        }
        if (exchange.getOfferList2() != null) {
            exchange.getOfferList2().setStatus(completed);
            offerListRepository.save(exchange.getOfferList2());
        }
        exchangeListRepository.save(exchange);
    }


}

