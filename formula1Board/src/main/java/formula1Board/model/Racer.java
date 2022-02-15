package formula1Board.model;

import java.util.Objects;

public class Racer {
    private String abbreviations;
    private String name;
    
    @Override
    public int hashCode() {
        return Objects.hash(abbreviations, name, teamName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Racer other = (Racer) obj;
        return Objects.equals(abbreviations, other.abbreviations) && Objects.equals(name, other.name)
                && Objects.equals(teamName, other.teamName);
    }

    private String teamName;
        
    public Racer (String abbreviations, String name, String teamName) {
        this.abbreviations = abbreviations;
        this.name = name;
        this.teamName = teamName;
    }

    public String getAbbreviations() {
        return abbreviations;
    }

    public void setAbbreviations(String abbreviations) {
        this.abbreviations = abbreviations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
