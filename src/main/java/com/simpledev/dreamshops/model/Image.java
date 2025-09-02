package com.simpledev.dreamshops.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;


@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;

    @Lob
    @Column(name = "image_data")
    @JsonIgnore
    private Blob blob;
    private String downloadUrl;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    public Image() {
    }
}
