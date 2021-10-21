package com.example.movie.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Image implements ValueObject {

    private final String imageUrl;

    private final ImageType imageType;

    public Image() {
        imageType=ImageType.poster;
        imageUrl="";
    }

    public Image(String url, ImageType imageType) {
        this.imageUrl = url;
        this.imageType = imageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return imageUrl.equals(image.imageUrl);
    }

}
