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
        String temp;
        temp=props.getProperty("ShiftIndex");
        if(temp != null)
        inter.shift = Integer.parseInt(temp);
        temp=props.getProperty("StartIndex");
        if(temp != null)
            inter.start_index = Integer.parseInt(temp);
        temp=props.getProperty("EveryPattern");
        if(temp != null)
            inter.every_pattern = Boolean.parseBoolean(temp);
        temp=props.getProperty("FindCoeff");
        if(temp != null)
        inter.find_percentage = Double.parseDouble(temp);
        temp=props.getProperty("SkipPattern");
        if(temp != null)
        inter.skip_pattern = Integer.parseInt(temp);

}
}
