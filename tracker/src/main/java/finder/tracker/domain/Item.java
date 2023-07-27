package finder.tracker.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Item {
    // 기관명
    @XmlElement(name = "dutyName")
    private String dutyName;
    // 병상 수
    @XmlElement(name = "hvec")
    private Long hvec;
}