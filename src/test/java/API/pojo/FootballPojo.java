package API.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class FootballPojo {

    private int count;
    private Object filters;
    private List<Map<String, Object>> competitions;
}
