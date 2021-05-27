package Model;

public enum Category {
    TV_SHOW("TV Show"),
    MOVIE("Movie"),
    ANIMATION("Animation Film"),
    ANIME("Anime"),
    COMIC("Comic"),
    BOOK("Book"),
    SHORT_STORY("Short Story");

    private String name;

    Category(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
