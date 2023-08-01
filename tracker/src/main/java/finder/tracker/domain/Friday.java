package finder.tracker.domain;

import finder.tracker.idclass.FridayId;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "friday")
@IdClass(FridayId.class)
@Getter
@Setter
public class Friday {
    @Id
    private String name;
    @Id
    private String time;

    @Column(name = "count")
    private Integer count;
}