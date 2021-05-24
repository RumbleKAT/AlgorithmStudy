import java.util.*;

public class Main {

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}
 class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
        Song next = this;
        HashMap<String, Integer> map = new HashMap<>();

        while(next != null){
            if(map.get(next.name) != null) return true;
            map.put(next.name, 1);
            next = next.nextSong;
        }
        return false;
    }
}