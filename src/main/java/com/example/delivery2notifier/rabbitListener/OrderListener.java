package com.example.delivery2notifier.rabbitListener;


import com.example.delivery2notifier.dto.DeliveryResult;
import com.example.delivery2notifier.dto.OrderDiscountMessage;
import com.example.delivery2notifier.services.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class OrderListener {

    private NotificationService notificationService;
    private final Map<UUID, DeliveryResult> deliveryData = new HashMap<>();

    @Autowired
    public OrderListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "noticeQueue")
    public void listen(OrderDiscountMessage message) {
        UUID orderId = message.getOrderId();
        Double discount = message.getDiscount();
        Double oldPrice = message.getOldPrice();
        Double newPrice = message.getNewPrice();
        LocalDateTime time = message.getDate();
        System.out.println("К заказу с ID: "+ orderId + " была применена скидка "+ discount * 100 + "%. Старая цена: "+ oldPrice + ". Новая цена: "+ newPrice+ ". Время: " +  time);
        notificationService.sendNotification("К заказу с ID: "+ orderId + " была применена скидка "+ discount * 100 + "%. Старая цена: "+ oldPrice + ". Новая цена: "+ newPrice+ ". Время: " +  time);
    }

    @RabbitListener(queues = "deliveryQueue")
    public void handleDeliveryResult(DeliveryResult deliveryResult) {
        UUID orderId = deliveryResult.getOrderId();
        double distance = deliveryResult.getDistance();

        try {
            if (distance == 0.0) {
                deliveryData.remove(orderId);
                System.out.println("Заказ доставлен и удален: " + orderId);
                notificationService.sendNotification(createDeliveryMessage(orderId, "Заказ доставлен!"));
            } else if (deliveryData.containsKey(orderId)) {
                deliveryData.put(orderId, deliveryResult);
                System.out.println("Обновлены данные для заказа: " + orderId);
                notificationService.sendNotification(createDeliveryPayload(deliveryResult));
            } else {
                deliveryData.put(orderId, deliveryResult);
                System.out.println("Добавлен новый заказ: " + orderId);
                notificationService.sendNotification(createDeliveryPayload(deliveryResult));
            }
        } catch (Exception e) {
            System.err.println("Ошибка при обработке данных доставки: " + e.getMessage());
        }
    }


    private String createDeliveryMessage(UUID orderId, String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "delivery");
        payload.put("payload", Map.of("orderId", orderId.toString(), "message", message));
        return objectMapper.writeValueAsString(payload);
    }

    private String createDeliveryPayload(DeliveryResult deliveryResult) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "delivery");
        payload.put("payload", deliveryResult);
        return objectMapper.writeValueAsString(payload);
    }



}
