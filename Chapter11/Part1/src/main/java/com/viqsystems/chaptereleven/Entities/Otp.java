package com.viqsystems.chaptereleven.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Otp {
    @Id
    private String username;
    private String code;
}
