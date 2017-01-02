/**
 * Created by msrabon on 12-Nov-16.
 * Project Name: JSON GSON.
 */
public class Key <T> {

    private String keyWord;
    private T t;


    public Key(T t, String keyWord) {
        this.t = t;
        this.keyWord = keyWord;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return String.format("Keyword: %s\n%s",keyWord,t.toString());
    }
}
