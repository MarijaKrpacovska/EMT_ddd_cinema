package com.example.movie.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Video implements ValueObject {

    private final String videoUrl;

    public Video() {
        videoUrl="";
    }

    public Video(String url) {
        this.videoUrl = url;
    }

    public static Video valueOf(String url) {
        return new Video(url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return videoUrl.equals(video.videoUrl);
    }

}
