package com.spring.boot.phoneshop.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    private BigDecimal price;
    private BigDecimal displaySizeInches;
    private Integer weightGr;
    private BigDecimal lengthMm;
    private BigDecimal widthMm;
    private BigDecimal heightMm;
    private Date announced;
    private String os;
    private String displayResolution;
    private Integer pixelDensity;
    private String displayTechnology;
    private BigDecimal backCameraMegapixels;
    private BigDecimal frontCameraMegapixels;
    private BigDecimal ramGb;
    private BigDecimal internalStorageGb;
    private Integer batteryCapacityMah;
    private BigDecimal talkTimeHours;
    private BigDecimal standByTimeHours;
    private String bluetooth;
    private String positioning;
    private String imageUrl;
    private String description;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    @ManyToMany
    @JoinTable(name = "phone2color",
            joinColumns = @JoinColumn(name = "phoneId"),
            inverseJoinColumns = @JoinColumn(name = "colorId"))
    private Set<Color> colors = new HashSet<>();
    @OneToOne(mappedBy = "product")
    private Stock stock;
}