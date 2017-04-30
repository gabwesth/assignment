package FootBallTableTournament;

/**
 * Created by Boti on 30-Apr-17.
 */
public class pointsAndTeams {

    public String team;
    public int points;

    public String toString()
    {
        return team + "\t"+ "\t" + points;
    }

    public pointsAndTeams(String team, int points){
        this.team=team;
        this.points=points;
    }

}
