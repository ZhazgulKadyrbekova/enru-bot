package model;

import java.sql.Date;
import java.util.Objects;

public class Lesson {

    private Integer id;

    private String name;

    private String teacher;

    private String hw;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getHw() {
        return hw;
    }

    public void setHw(String hw) {
        this.hw = hw;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id) && Objects.equals(name, lesson.name) && Objects.equals(teacher, lesson.teacher) && Objects.equals(hw, lesson.hw) && Objects.equals(date, lesson.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teacher, hw, date);
    }

    @Override
    public String toString() {
        if (hw == null) {
            return  "name:     " + name +
                    "\nteacher:  " + teacher+
                    "\nhw:       " + hw + "\n";
        }
        return  "name:     " + name +
                "\nteacher:  " + teacher+
                "\nhw:       " + hw +
                "\ndate:     " + date + "\n";
    }
}
