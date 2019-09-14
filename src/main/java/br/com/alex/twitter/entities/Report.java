package br.com.alex.twitter.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "tweet")
    private Set<UserEntity> users;
}
