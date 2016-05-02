package metropolia.minhtn.chatapplication.models;

import java.util.ArrayList;
import java.util.List;

import metropolia.minhtn.chatapplication.service.MessageObserver;

/**
 * Created by nmt19 on 5/1/2016.
 */
public class Tasks {
    private Tasks tasks;
    private List<Task> taskList;
    private List<MessageObserver> observers;

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
        return taskList.get(id);
    }

    public void addTask(Task t ){
        taskList.add(t);
    }

    public void registerObserver( MessageObserver o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for(MessageObserver o : observers){
            o.refresh();
        }
    }


}
