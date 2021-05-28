package Model;

public class Length {
    private int duration;
    private String episodes;
    private String seasons;
    private String volumes;
    private String pages;

    public Length(String seasons, String episodes, int duration, String volumes, String pages){
        this.duration = duration;
        this.episodes = episodes;
        this.seasons = seasons;
        this.volumes = volumes;
        this.pages = pages;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public void setEpisodes(String episodes){
        this.episodes = episodes;
    }

    public void setSeasons(String seasons){
        this.seasons = seasons;
    }

    public void setVolumes(String volumes){
        this.volumes = volumes;
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n\tLength:");
        if(!seasons.equals("")){
            info.append("\n\t\t").append(seasons).append(" season(s)");
        }
        if(!episodes.equals("")){
            info.append("\n\t\t").append(episodes).append(" episode(s)");
        }
        if(duration != 0) {
            int hours = duration / 60;
            int minutes = duration % 60;
            info.append("\n\t\t").append(hours).append("h ").append(minutes).append("m");
        }
        if(!volumes.equals("")){
            info.append("\n\t\t").append(volumes).append(" volume(s)");
        }
        if(!pages.equals("")){
            info.append("\n\t\t").append(pages).append(" page(s)");
        }
        return info.toString();
    }
}
