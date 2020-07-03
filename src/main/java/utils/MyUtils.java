package utils;

import bean.GlobalResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Rice
 * @create 2020/07/02 16:22
 */
public class MyUtils {


    public static void resp(HttpServletResponse response, GlobalResponse globalResponse){
        PrintWriter writer = null ;
        try {
            writer = response.getWriter();
            String res = GlobalResponse.getResponse(globalResponse);
            writer.println(res);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (writer!=null){
                writer.close();
            }
        }
    }

}
