package xd.medicine.entity.dto;

import java.io.Serializable;

/**
 * created by xdCao on 2017/12/25
 */

public class InMessage implements Serializable{

    private String name;
    private String id;

    public InMessage() {
    }

    public InMessage(String name,String id) {
        this.id = id;
        this.name = name;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id:  "+id+"---------, name:   "+name;
    }
}
