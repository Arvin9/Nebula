package site.nebulas.beans;

/**
 * Created by Administrator on 2017/3/12.
 */
public class Digest {
    private Integer id;
    private String title;
    private String content;
    private String provenance;
    private Integer readQuantity;
    private String addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public Integer getReadQuantity() {
        return readQuantity;
    }

    public void setReadQuantity(Integer readQuantity) {
        this.readQuantity = readQuantity;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
