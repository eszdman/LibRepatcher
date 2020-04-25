import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Patcher {
    private static byte[] String2Byte(String in){
        in = in.replace("0x", "");
        char[] i = in.toCharArray();
        byte[] output = new byte[i.length/2];
        for(int k = 0; k<i.length; k+=2) {
            output[k / 2] += -128;
            output[k / 2] = (byte) (Integer.parseInt((Character.toString(i[k]) + Character.toString(i[k + 1])), 16));
        }
        return output;
    }
    public static void ParsePatchAndFindBytes(Interface inter){
        inter.patchB = String2Byte(inter.patchBStr);
        inter.findB = String2Byte(inter.findBStr);
    }
    public static void FindPtr(Interface inter){
        int counter = 0;
        int max_ind = 0;
        int max_counter = 0;
        int patchcount = 0;
        for(int i = inter.start_index; i<inter.fileB.length; i++){
            for(int j = 0; j<inter.findB.length;j++) {
               if(i+j < inter.fileB.length)
                   if (inter.fileB[i+j] == inter.findB[j]) counter++;

            }
            if(inter.every_pattern &&counter == inter.findB.length) for(int j = 0; j<inter.findB.length; j++) {
                if(inter.skip_pattern>0) {inter.skip_pattern--; continue;}
                inter.fileB[i+j] = inter.patchB[j];
                patchcount++;
            }
            if(max_counter<counter) {
                max_counter = counter;max_ind = i;
            }
            counter = 0;
        }
        if(inter.every_pattern) System.out.println("Patch count:"+patchcount);
        if(!inter.every_pattern)System.out.println("Patched, Success:" +100*((double)max_counter/inter.findB.length) +"%");
        else {
            if(((double)(max_counter)/inter.findB.length)>0.99)
                System.out.println("Patched, Success every pattern"); else
                    System.out.println("Fail every pattern");}
                    inter.index = max_ind;
    }
    public static void PathPtr(Interface inter){
        for(int i = 0; i<inter.patchB.length; i++){
            inter.fileB[inter.index+inter.shift+i] = inter.patchB[i];
        }
    }
    public static void SafeLib(Interface inter){
        File patched = new File(inter.inputFile.getAbsolutePath().replace(inter.inputFile.getName(),"")+"\\patched\\"+inter.inputFile.getName());
        try {
            File dir = new File(inter.inputFile.getAbsolutePath().replace(inter.inputFile.getName(),"")+"\\patched\\");
            if(!dir.exists()) dir.mkdir();
            if(!patched.exists()) patched.createNewFile();
            try {
                Files.write(patched.toPath(),inter.fileB);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
