package finder.tracker.domain;

import finder.tracker.idclass.WednesdayId;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "wednesday")
@IdClass(WednesdayId.class)
@Getter
@Setter
public class Wednesday {
    @Id
    private String name;
    @Id
    private String time;

    @Column(name = "count")
    private Integer count;
}