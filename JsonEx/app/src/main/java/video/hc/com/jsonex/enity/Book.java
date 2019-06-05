package video.hc.com.jsonex.enity;

/**
 * Created by ly on 2019/6/4.
 */

public class Book extends BaseUsers{
    String id;
    String language;
    String edition;
    String author;

    public Book(String id, String language, String edition, String author) {
        this.id = id;
        this.language = language;
        this.edition = edition;
        this.author = author;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", edition='" + edition + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
