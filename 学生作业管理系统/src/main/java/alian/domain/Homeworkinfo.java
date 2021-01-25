package alian.domain;

public class Homeworkinfo {
    private Integer id;

    private Integer homeworkId;

    private Integer studentId;

    private Integer type;

    private String filename;

    private String text;

    private String info;

    private String piyu;

    private Integer filescore;

    private Integer textscore;

    private String score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getPiyu() {
        return piyu;
    }

    public void setPiyu(String piyu) {
        this.piyu = piyu == null ? null : piyu.trim();
    }

    public Integer getFilescore() {
        return filescore;
    }

    public void setFilescore(Integer filescore) {
        this.filescore = filescore;
    }

    public Integer getTextscore() {
        return textscore;
    }

    public void setTextscore(Integer textscore) {
        this.textscore = textscore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }
}