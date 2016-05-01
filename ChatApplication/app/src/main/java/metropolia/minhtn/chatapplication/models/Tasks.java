package metropolia.minhtn.chatapplication.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmt19 on 5/1/2016.
 */
public class Tasks {
    private Tasks tasks;
    private List<Task> taskList;

    private Tasks() {
        taskList = new ArrayList<>();
    }

    public Tasks getInstance(){
        if(tasks == null ){
            tasks = new Tasks();
        }
        return tasks;
    }

    public int getSize(){
        return taskList.size();
    }

    public Task getTask(int id ){

    }
}
