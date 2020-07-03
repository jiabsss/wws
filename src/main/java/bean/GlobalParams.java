package bean;

import lombok.*;

import java.util.Map;

/**
 * @author Rice
 * @create 2020/07/01 18:14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GlobalParams {
    private String action ;
    private Map params;
}
