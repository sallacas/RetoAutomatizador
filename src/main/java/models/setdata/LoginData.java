package models.setdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class LoginData {
    private String user;
    private String password;
    
    public static List<LoginData> setData(DataTable dataTable) {
        List<LoginData> dates = new ArrayList<>();
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dates.add(new ObjectMapper().convertValue(map, LoginData.class));
        }
        return dates;
    }
}