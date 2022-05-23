package com.fgsantana.datacaching.domain;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Game implements Serializable {

    Integer id;
    String name;
    Double rating;
}
