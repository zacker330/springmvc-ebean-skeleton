package hello.demo.domain;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo extends Model implements Serializable {

    private static final long serialVersionUID = -4886703854563377897L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "modification_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationTime;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Version
    private long version;

    public Todo() {
    }

    public Todo(String title, String description) {
        this.description = description;
        this.title = title;
        Date now = new Date();
        this.creationTime = now;
        this.modificationTime = now;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}