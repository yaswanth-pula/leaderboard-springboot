package com.zmslabs.springboot.leaderboard.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fantasy")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Fantasy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fantasy_id")
    private int fantasyID;

    @Column(name = "fan_support_count")
    private int fanSupportCount;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
