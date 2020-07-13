package API.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PetPojo {

    private int id;
    private String name;
    private List<String> photoUrls;
    private List<String> tags;
    private String status;

    public PetPojo(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }


}
