package alian.domain;

import org.springframework.stereotype.Component;

import java.util.Date;
public class Homework {
    private Integer id;

    private Integer classId;

    private Date stime;

    private Date etime;

    private Integer type;

    private String filename;

    private Integer state;

    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", classId=" + classId +
                ", stime=" + stime +
                ", etime=" + etime +
                ", type=" + type +
                ", filename='" + filename + '\'' +
                ", state=" + state +
                ", text='" + text + '\'' +
                '}';
    }
}