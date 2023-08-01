package finder.tracker.domain;

import finder.tracker.idclass.ThursdayId;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "thursday")
@IdClass(ThursdayId.class)
@Getter
@Setter
public class Thursday {
    @Id
    private String name;
    @Id
    private String time;

    @Column(name = "count")
    private Integer count;
}