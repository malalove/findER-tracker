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

    public void increaseByOneMinute() {
        String[] timeParts = this.time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        minute += 1;

        if (minute == 60) {
            hour += 1;
            minute = 00;

        }

        if (hour == 24) {
            hour = 00;
        }

        this.time = String.format("%02d:%02d", hour, minute);
    }
}