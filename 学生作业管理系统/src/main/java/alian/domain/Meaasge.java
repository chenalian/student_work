package alian.domain;

public class Meaasge {
    private Integer id;

    private Integer sendid;

    private Integer receid;

    private Integer sendstate;

    private Integer recestate;

    private Integer state;

    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSendid() {
        return sendid;
    }

    public void setSendid(Integer sendid) {
        this.sendid = sendid;
    }

    public Integer getReceid() {
        return receid;
    }

    public void setReceid(Integer receid) {
        this.receid = receid;
    }

    public Integer getSendstate() {
        return sendstate;
    }

    public void setSendstate(Integer sendstate) {
        this.sendstate = sendstate;
    }

    public Integer getRecestate() {
        return recestate;
    }

    public void setRecestate(Integer recestate) {
        this.recestate = recestate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}