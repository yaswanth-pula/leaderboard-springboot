package com.zmslabs.springboot.leaderboard.entity;


import com.zmslabs.springboot.leaderboard.validation.DbUniquePlayerName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "player")
@NoArgsConstructor
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int playerID;

    @Column(name = "player_name")
    @NotNull
    @Size(min = 1,message = "Player Name Should be atleast 1 char")
    @DbUniquePlayerName
    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }


}
