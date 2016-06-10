package com.ruc.bhushalsuman.ruc;

/**
 * Created by Bhushal Suman on 4/13/2016.
 */
public class FetchRecords {
    String Id;
    String module;
    String lecturer;
    String week_Day;
    String time;
    String block;
    String class_Room;

    public FetchRecords(String id, String module, String lecturer, String week_Day, String time, String block, String class_Room) {
        Id = id;
        this.module = module;
        this.lecturer = lecturer;
        this.week_Day = week_Day;
        this.time = time;
        this.block = block;
        this.class_Room = class_Room;
    }

    public FetchRecords() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getWeek_Day() {
        return week_Day;
    }

    public void setWeek_Day(String week_Day) {
        this.week_Day = week_Day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getClass_Room() {
        return class_Room;
    }

    public void setClass_Room(String class_Room) {
        this.class_Room = class_Room;
    }
}
