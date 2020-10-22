import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetPatchParameters {
    private static int readint(String in){
        if(in.contains("0x")){
            return Integer.parseInt(in.replace("0x",""),16);
        } else return Integer.parseInt(in);
    }
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
        inter.shift = readint(temp);
        temp=props.getProperty("StartIndex");
        if(temp != null)
            inter.start_index = readint(temp);
        temp=props.getProperty("EveryPattern");
        if(temp != null)
            inter.every_pattern = Boolean.parseBoolean(temp);
        temp=props.getProperty("FindCoeff");
        if(temp != null)
        inter.find_percentage = Double.parseDouble(temp);
        temp=props.getProperty("SkipPattern");
        if(temp != null)
        inter.skip_pattern = readint(temp);

}
}
