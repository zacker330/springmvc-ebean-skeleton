package hello.demo.domain;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jack on 1/28/16.
 */
@Entity
@Table(name = "people")
public class Person extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name = "modification_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationTime;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Version
    private long version;

    @Column(name = "avatar_path", nullable = true)
    private String avatarPath;


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

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
