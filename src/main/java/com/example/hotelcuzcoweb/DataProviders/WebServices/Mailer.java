package com.example.hotelcuzcoweb.DataProviders.WebServices;

import com.example.hotelcuzcoweb.Business.Entities.Room;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Mailer {

    public void sendCreateRoomMail(Room room) {
        Mail mail = new Mail(1, 1, "new room created", room.description);

        this.send(mail);
    }

    private void send(Mail mail) {
        RestTemplate restTemplate = new RestTemplate();

        String url
                = "https://jsonplaceholder.typicode.com/posts";

        HttpEntity<Mail> request = new HttpEntity<>(mail);

        Mail returnedMail = restTemplate.postForObject(url, request, Mail.class);

        String title = returnedMail.title;
    }
}
