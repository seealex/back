package br.com.alex.twitter.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private Long id;
    private String screenName;
    private Long followers;
    private String idiom;

}
