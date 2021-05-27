public class VideoLength implements Length{
    private int duration;
    private int episodes;
    private int seasons;

    public VideoLength(int duration, int episodes, int seasons){
        this.duration = duration;
        this.episodes = episodes;
        this.seasons = seasons;
    }

    public int getDuration(){
        return duration;
    }

    public int getEpisodes(){
        return episodes;
    }

    public int getSeasons(){
        return seasons;
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
        info.append("Length:");
        if(seasons != 0){
            info.append("\t" + seasons + " seasons\n");
        }
        if(episodes != 0){
            info.append("\t" + episodes + " episodes\n");
        }
        if(duration != 0) {
            int hours = duration / 60;
            int minutes = duration % 60;
            info.append("\t" + hours + "h " + minutes + "m\n");
        }
        return info.toString();
    }
}
