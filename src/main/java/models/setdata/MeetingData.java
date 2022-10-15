package models.setdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class MeetingData {

    private String nameBusiness;
    private String typeMeeting;
    private String number;
    private String date;
    private String hourStart;
    private String hourEnd;
    private String location;
    private String host;
    private String reporter;
    
    public static List<MeetingData> setData(DataTable dataTable) {
        List<MeetingData> dates = new ArrayList<>();
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dates.add(new ObjectMapper().convertValue(map, MeetingData.class));
        }
        return dates;
    }
}