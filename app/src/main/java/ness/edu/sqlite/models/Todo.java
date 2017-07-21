package ness.edu.sqlite.models;

/**
 * A Todos Model class
 */

public class Todo {
    //Proprties:
    private int id;
    private String mission;
    private String importance;

    //Constructor for INSERT:
    public Todo(String mission, String importance) {
        this.mission = mission;
        this.importance = importance;
    }

    //Constructor for the SELECT:
    public Todo(String mission, String importance, int id) {
        this.id = id;
        this.mission = mission;
        this.importance = importance;
    }

    //getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMission() {
        return mission;
    }
    public void setMission(String mission) {
        this.mission = mission;
    }
    public String getImportance() {
        return importance;
    }
    public void setImportance(String importance) {
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", mission='" + mission + '\'' +
                ", importance='" + importance + '\'' +
                '}';
    }
}
