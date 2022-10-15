package models.setdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class AbrirLoombokData {
    private String url;
    
    public static List<AbrirLoombokData> setData(DataTable dataTable) {
        List<AbrirLoombokData> dates = new ArrayList<>();
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dates.add(new ObjectMapper().convertValue(map, AbrirLoombokData.class));
        }
        return dates;
    }
}