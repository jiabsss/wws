package bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rice
 * @create 2020/06/29 10:23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Msg {
    private String user ;
    private String tsid ;
    private String msg ;
    //1 私聊  2 群聊
    private Integer tid ;
    public static Msg getInstance(String msg){
        Msg mo =null ;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            mo = objectMapper.readValue(msg, Msg.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mo ;
    }

    public static String forwordMSg(Msg msg)   {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(msg);
        }catch (JsonProcessingException e){
            return null ;
        }
    }



}
