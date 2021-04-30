package com.spring.boot.phoneshop.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String brand;
    @NotNull
    private String model;

    private BigDecimal price;
    @Column(name = "DISPLAYSIZEINCHES")
    private BigDecimal displaySizeInches;
    @Column(name = "WEIGHTGR")
    private Integer weightGr;
    @Column(name = "LENGTHMM")
    private BigDecimal lengthMm;
    @Column(name = "WIDTHMM")
    private BigDecimal widthMm;
    @Column(name = "HEIGHTMM")
    private BigDecimal heightMm;
    private Date announced;
    @Column(name = "DEVICETYPE")
    private String deviceType;
    private String os;
    @Column(name = "DISPLAYRESOLUTION")
    private String displayResolution;
    @Column(name = "PIXELDENSITY")
    private Integer pixelDensity;
    @Column(name = "DISPLAYTECHNOLOGY")
    private String displayTechnology;
    @Column(name = "BACKCAMERAMEGAPIXELS")
    private BigDecimal backCameraMegapixels;
    @Column(name = "FRONTCAMERAMEGAPIXELS")
    private BigDecimal frontCameraMegapixels;
    @Column(name = "RAMGB")
    private BigDecimal ramGb;
    @Column(name = "INTERNALSTORAGEGB")
    private BigDecimal internalStorageGb;
    @Column(name = "BATTERYCAPACITYMAH")
    private Integer batteryCapacityMah;
    @Column(name = "TALKTIMEHOURS")
    private BigDecimal talkTimeHours;
    @Column(name = "STANDBYTIMEHOURS")
    private BigDecimal standByTimeHours;
    private String bluetooth;
    private String positioning;
    @Column(name = "IMAGEURL")
    private String imageUrl;
    @Column(length = 4096)
    private String description;
}
