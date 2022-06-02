package hr.tvz.smolcic.hardwareapp.model;

import hr.tvz.smolcic.hardwareapp.enums.Rating;
import hr.tvz.smolcic.hardwareapp.util.converters.RatingAttributeConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "REVIEW")
public class Review implements Serializable {

    @Id
    @Column(name = "REVIEW_ID")
    @GeneratedValue
    int id;

    @Column(name = "TITLE")
    String title;

    @Column(name = "TEXT")
    String text;

    @Column(name = "RATING_ID")
    @Convert(converter = RatingAttributeConverter.class)
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "HARDWARE_ID")
    private Hardware hardware;

    public Review() {

    }

    public Review(int id, String title, String text, Rating rating) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getHardware() {
        return hardware.id;
    }
}
