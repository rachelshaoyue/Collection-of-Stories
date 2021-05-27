package Model;

public class BookLength implements Length{
    private int volumes;
    private int pages;

    public BookLength(int volumes, int pages){
        this.volumes = volumes;
        this.pages = pages;
    }

    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n\tLength:");
        if(volumes != 0){
            info.append("\n\t\t").append(volumes).append(" volume(s)");
        }
        if(pages != 0) {
            info.append("\n\t\t").append(pages).append(" page(s)");
        }
        return info.toString();
    }

    public void setVolumes(int volumes){
        this.volumes = volumes;
    }

    public void setPages(int pages){
        this.pages = pages;
    }
}
