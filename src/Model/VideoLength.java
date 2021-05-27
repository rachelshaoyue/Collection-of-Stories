package Model;

public class VideoLength implements Length{
    private int duration;
    private int episodes;
    private int seasons;

    public VideoLength(int duration, int episodes, int seasons){
        this.duration = duration;
        this.episodes = episodes;
        this.seasons = seasons;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public void setEpisodes(int episodes){
        this.episodes = episodes;
    }

    public void setSeasons(int seasons){
        this.seasons = seasons;
    }

    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n\tLength:");
        if(seasons != 0){
            info.append("\n\t\t").append(seasons).append(" season(s)");
        }
        if(episodes != 0){
            info.append("\n\t\t").append(episodes).append(" episode(s)");
        }
        if(duration != 0) {
            int hours = duration / 60;
            int minutes = duration % 60;
            info.append("\n\t\t").append(hours).append("h ").append(minutes).append("m");
        }
        return info.toString();
    }
}
