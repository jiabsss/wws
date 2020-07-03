package bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rice
 * @create 2020/06/30 10:05
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponse {
    private Integer status ;
    private Object data ;

    public GlobalResponse(Object data){
        this.data = data;
    }
    public static String getResponse(Integer status,Object data)  {
        GlobalResponse globalResponse = new GlobalResponse();
        globalResponse.setData(data);
        globalResponse.setStatus(status);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(globalResponse);
        }catch ( Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getResponse(GlobalResponse globalResponse)  {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(globalResponse);
        }catch ( Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getResponse(Object data)  {
        GlobalResponse globalResponse = new GlobalResponse();
        globalResponse.setData(data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(globalResponse);
        }catch ( Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
