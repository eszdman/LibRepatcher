import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetPatchParameters {
public static void ReadPatchParams(Interface inter, File inputPatch){
    Properties props = new Properties();
    try {
        props.load(new FileInputStream(inputPatch));
    } catch (IOException e) {
        e.printStackTrace();
    }
    inter.findBStr = props.getProperty("FindPattern");
    inter.patchBStr = props.getProperty("ReplaceArray");
    try {
        inter.shift = Integer.parseInt(props.getProperty("ShiftIndex"));
        inter.start_index = Integer.parseInt(props.getProperty("StartIndex"));
        inter.every_pattern = Boolean.parseBoolean(props.getProperty("EveryPattern"));
    }catch (Exception e){
        inter.start_index = 0;
        inter.shift = 0;
        inter.every_pattern = false;
    }
}
}
