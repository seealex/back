package br.com.alex.twitter.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {

    @Id
    private Long id;

    private String tag;

}
