package metropolia.minhtn.chatapplication.models;

/**
 * Created by nmt19 on 5/1/2016.
 */
public class Task {
    private String content;
    private boolean isCompleted;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Task(String content, boolean isCompleted) {

        this.content = content;
        this.isCompleted = isCompleted;
    }
}
