package Model;

import java.util.ArrayList;
import java.util.HashSet;

public class Story {
    private final int ID = counter;
    private static int counter = 0;

    private int rating;
    private ArrayList<Creator> creatorList;
    private HashSet<String> sourceSet;
    private String review;
    private HashSet<String> genreSet;
    private String title;
    private Length length;

    public Story(int rating, ArrayList<Creator> creatorList, Length length, HashSet<String> sourceSet, String review,
                 HashSet<String> genreSet, String title){

        this.rating = rating;
        this.creatorList = creatorList;
        this.sourceSet = sourceSet;
        this.review = review;
        this.genreSet = genreSet;
        this.title = title;
        this.length = length;

        counter++;
    }

    int getID(){
        return ID;
    }

    int getRating(){
        return rating;
    }

    ArrayList<Creator> getCreatorList(){
        return creatorList;
    }
    public Length getLength(){
        return length;
    }

    HashSet<String> getSourceSet(){
        return sourceSet;
    }

    String getReview(){
        return review;
    }

    HashSet<String> getGenreSet(){
        return genreSet;
    }

    String getTitle(){
        return title;
    }

    void setRating(int rating){
        this.rating = rating;
    }

    void addCreator(Creator creator){
        creatorList.add(creator);
    }

    void removeCreator(Creator creator){
        creatorList.remove(creator);
    }

    void setLength(Length length){
        this.length = length;
    };

    void setReview(String review){
        this.review = review;
    }

    void addGenre(String genre){
        genreSet.add(genre);
    }

    void removeGenre(String genre){
        genreSet.remove(genre);
    }
}
