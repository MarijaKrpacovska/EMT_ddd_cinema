package com.example.movie.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.format.DateTimeFormatter;

@Embeddable
@Getter
public class Video implements ValueObject {

    private final String videoUrl;
    public Video() {
        videoUrl = "";
    }
    public Video(String url) {
        this.videoUrl = url;
    }
    public static Video valueOf(String url) {
        return new Video(url);
    }

    public static Video buildVideoWithEmbeddableUrl(String url) {
        if (url.contains("https://www.youtube.com/embed/")) {
            return new Video(url);
        } else {
            String finalVideoUrl = "";
            if (url.contains("https://youtu.be/")) {
                finalVideoUrl = url.replace("https://youtu.be/", "");
            } else if (url.contains("https://www.youtube.com/watch?v=")) {
                finalVideoUrl = url.replace("https://www.youtube.com/watch?v=", "");
            } else {
                finalVideoUrl = "";
            }
            return new Video("https://www.youtube.com/embed/" + finalVideoUrl);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return videoUrl.equals(video.videoUrl);
    }

}
