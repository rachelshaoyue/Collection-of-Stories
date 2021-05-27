import java.util.ArrayList;

public class Creator {
    private final int ownerID = counter;
    private static int counter = 0;

    private String name;
    private ArrayList<Story> storyList;

    public Creator(String name){
        this.name = name;
        counter++;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addWork(Story story){
        storyList.add(story);
    }

    public void removeWork(Story story){
        storyList.remove(story);
    }

    public ArrayList<Story> getStoryList(){
        return storyList;
    }

    public int getRating(){
        int totalRating = 0;
        for(Story story: storyList){
            int rating = story.getRating();
            totalRating += rating;
        }
        return totalRating / storyList.size();
    }
}
