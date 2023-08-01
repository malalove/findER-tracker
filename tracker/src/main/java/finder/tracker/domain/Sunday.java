package finder.tracker.domain;

import finder.tracker.idclass.SundayId;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "sunday")
@IdClass(SundayId.class)
@Getter
@Setter
public class Sunday {
    @Id
    private String name;
    @Id
    private String time;

    @Column(name = "count")
    private Integer count;
}