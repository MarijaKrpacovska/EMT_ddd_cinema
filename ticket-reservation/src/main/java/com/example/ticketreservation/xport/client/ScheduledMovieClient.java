package com.example.ticketreservation.xport.client;

import com.example.ticketreservation.domain.valueobjects.Movie;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScheduledMovieClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public ScheduledMovieClient(@Value("${app.scheduledMovie.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }


    public List<ScheduledMovie> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/scheduledMovie")
                    .build().toUri(), HttpMethod.GET,null
                    , new ParameterizedTypeReference<List<ScheduledMovie>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Optional<ScheduledMovie> findScheduledMovieById(String id) {
        try {
            return Optional.of(Objects.requireNonNull(restTemplate.exchange(uri().path("/api/scheduledMovie/findById/" + id)
                            .build().toUri(), HttpMethod.GET, null
                    , new ParameterizedTypeReference<ScheduledMovie>() {
                    }).getBody()));
        } catch (Exception e) {
            return null;
        }
    }

}
