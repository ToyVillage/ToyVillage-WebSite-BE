package com.command.toyvillage_server.domain.app.join_team.domain;

import com.command.toyvillage_server.domain.app.team.domain.Team;
import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
        name = "tbl_join_team",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_join_team_app_account",
                columnNames = "app_account_id"
        )
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "join_team_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_account_id", nullable = false)
    private AppAccount appAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    private JoinTeam(AppAccount appAccount, Team team) {
        this.appAccount = appAccount;
        this.team = team;
    }

    public static JoinTeam create(AppAccount appAccount, Team team) {
        return new JoinTeam(appAccount, team);
    }

    public void updateTeam(Team team) {
        this.team = team;
    }
}
