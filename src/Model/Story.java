package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

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

    public Story(String title, Category category, int rating, ArrayList<Creator> creatorList, Length length, HashSet<String> sourceSet, String review,
                 HashSet<String> genreSet){
        this.category = category;
        this.rating = rating;
        this.creatorList = creatorList;
        this.sourceSet = sourceSet;
        this.review = review;
        this.genreSet = genreSet;
        this.title = title;
        this.length = length;
    }

    @Override
    public int hashCode(){
        int hashCode = category.hashCode() + title.hashCode() + length.hashCode();
        ID = hashCode;
        return hashCode;
    }

    public Category getCategory(){
        return category;
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
        String string = "\n\tCreated by:";
        if(creatorList != null) {
            for (Creator creator : creatorList) {
                string += "\n\t\t" + creator.getName();
            }
        }
        return string;
    }

    public Length getLength(){
        return length;
    }

    public HashSet<String> getSourceSet(){
        return sourceSet;
    }

    public String getStringSourceSet(){
        String string = "\n\tSource(s):";
        if(sourceSet != null) {
            for (String source : sourceSet) {
                string += "\n\t\t" + source;
            }
        }
        return string;
    }

    public String getReview(){
        return review;
    }

    public HashSet<String> getGenreSet(){
        return genreSet;
    }

    public String getStringGenreSet(){
        String string = "\n\tGenre(s):";
        if(genreSet != null) {
            for (String genre : genreSet) {
                string += "\n\t\t" + genre;
            }
        }
        return string;
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

    public String getInfo(){
        String info = title + " (" + category.toString() + ") Information:";
        info += getStringCreatorList();
        info += getStringGenreSet();
        info += getStringSourceSet();
        info += length.getInfo();
        info += "\n\tRating:\n\t\t" + rating;
        info += "\n\tReview:\n\t\t" + review;
        return info;
    }

    public static void main(String[] args){
        ArrayList<Creator> creators = new ArrayList<>();
        Creator creator = new Creator("Bryan Fuller");
        creators.add(creator);
        Length length = new VideoLength(43, 39, 3);
        HashSet<String> sourceSet = new HashSet<>();
        sourceSet.add("Netflix");
        sourceSet.add("Amazon Prime");
        String review = "MY FAVVVVV";
        HashSet<String> genreset = new HashSet<>();
        genreset.add("Psychological horror");
        String title = "Hannibal";
        Story story = new Story(title, Category.TV_SHOW, 10, null, new BookLength(345, 1234),
                null, "", null);
    }
}
