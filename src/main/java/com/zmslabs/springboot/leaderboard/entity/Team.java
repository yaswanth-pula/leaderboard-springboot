package com.zmslabs.springboot.leaderboard.entity;

import com.zmslabs.springboot.leaderboard.validation.DbUniqueTeamName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")

    private int teamId;

    @Column(name = "team_name")
    private String teamName;


    @Column(name = "matches_played")

    private int matchesPlayed;


    @Column(name = "matches_won")

    private int matchesWon;


    @Column(name = "matches_lost")

    private int matchesLost;


    @Column(name = "league_points")

    private int leaguePoints;

    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL)

    private Fantasy fantasy;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")

    private List<Player> players;


    // helpers
    @Transient
    private int supportScore;

    public int getSupportScore() {
        if (this.fantasy == null)
            return 0;
        return fantasy.getFanSupportCount();
    }

    public void addPlayers(Player player) {
        if (this.players == null)
                this.players = new ArrayList<>();
        this.players.add(player);
    }

}
