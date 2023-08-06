package finder.tracker.domain;

import finder.tracker.idclass.BedId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "bed")
@IdClass(BedId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bed {
    @Id
    private String name;
    @Id
    private String time;

    @Column(name = "count")
    private Integer count;
}