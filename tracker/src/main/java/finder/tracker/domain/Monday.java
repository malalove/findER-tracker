package finder.tracker.domain;

import finder.tracker.idclass.MondayId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "monday")
@IdClass(MondayId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Monday {
    @Id
    private String name;
    @Id
    private String time;

    @Column(name = "count")
    private Integer count;
}