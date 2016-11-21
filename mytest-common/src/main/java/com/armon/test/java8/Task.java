package com.armon.test.java8;

public class Task {

    public enum STATUS {
        OPEN, CLOSED
    };

    private STATUS status;
    private int points;

    public Task(STATUS status, int points) {
        this.status = status;
        this.points = points;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Task [status=" + status + ", points=" + points + "]";
    }

}
