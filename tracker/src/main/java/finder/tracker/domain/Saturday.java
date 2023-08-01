package finder.tracker.domain;

import finder.tracker.idclass.SaturdayId;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "saturday")
@IdClass(SaturdayId.class)
@Getter
@Setter
public class Saturday {
    @Id
    private String name;
    @Id
    private String time;

    @Column(name = "count")
    private Integer count;
}