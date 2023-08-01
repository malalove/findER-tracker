package finder.tracker.domain;

import finder.tracker.idclass.TuesdayId;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "tuesday")
@IdClass(TuesdayId.class)
@Getter
@Setter
public class Tuesday {
    @Id
    private String name;
    @Id
    private String time;

    @Column(name = "count")
    private Integer count;
}