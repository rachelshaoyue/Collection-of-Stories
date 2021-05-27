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
        info.append("Length:");
        if(volumes != 0){
            info.append("\t" + volumes + " volumes\n");
        }
        info.append("\t" + pages + " pages\n");
        return info.toString();
    }

    public int getVolumes(){
        return volumes;
    }

    public int getPages(){
        return pages;
    }

    public void setVolumes(int volumes){
        this.volumes = volumes;
    }

    public void setPages(int pages){
        this.pages = pages;
    }
}
