package finder.tracker.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "monday")
@Getter
@Setter
public class Monday {
    @Id
    private String name;
    @Id
    private String time;

    @Column(name = "count")
    private Integer count;
}