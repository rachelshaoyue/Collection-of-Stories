package Model;

import java.util.ArrayList;
import java.util.HashSet;

public class Story {
    private int ID;
    private Category category;
    private int rating;
    private ArrayList<Creator> creatorList;
    private HashSet<String> sourceSet;
    private String review;
    private HashSet<String> genreSet;
    private String title;
    private Length length;

    public Story(Category category, int rating, ArrayList<Creator> creatorList, Length length, HashSet<String> sourceSet, String review,
                 HashSet<String> genreSet, String title){
        this.category = category;
        this.rating = rating;
        this.creatorList = creatorList;
        this.sourceSet = sourceSet;
        this.review = review;
        this.genreSet = genreSet;
        this.title = title;
        this.length = length;
    }

    public Category getCategory(){
        return category;
    }

    @Override
    public int hashCode(){
        int hashCode = category.hashCode() + title.hashCode() + length.hashCode();
        ID = hashCode;
        return hashCode;
    }

    public int getID(){
        return ID;
    }

    public int getRating(){
        return rating;
    }

    public ArrayList<Creator> getCreatorList(){
        return creatorList;
    }

    public String getStringCreatorList(){
        String string = "[";
        for(Creator creator: creatorList){
            ;
        }
        return null;
    }

    public Length getLength(){
        return length;
    }

    public HashSet<String> getSourceSet(){
        return sourceSet;
    }

    public String getReview(){
        return review;
    }

    public HashSet<String> getGenreSet(){
        return genreSet;
    }

    public String getTitle(){
        return title;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public void addCreator(Creator creator){
        creatorList.add(creator);
    }

    public void removeCreator(Creator creator){
        creatorList.remove(creator);
    }

    public void setLength(Length length){
        this.length = length;
    };

    public void setReview(String review){
        this.review = review;
    }

    public void addGenre(String genre){
        genreSet.add(genre);
    }

    public void removeGenre(String genre){
        genreSet.remove(genre);
    }
}
