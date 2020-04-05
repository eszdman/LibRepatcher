import java.io.File;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class Main {
    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    public static void main(String[] args) {
        int patchnum = 0;
        if(args.length >= 2) patchnum = Integer.valueOf(args[1]);
        File inputPatch=null;
        Interface inter = new Interface();
        inter.inputFile = null;
        String fulldir = null;
        String name;
        String folder;
        try {
            inter.inputFile = new File(args[0]);
            name = inter.inputFile.getName();
            folder = inter.inputFile.getAbsolutePath().replace(name,"");
            inter.fileB = Files.readAllBytes(inter.inputFile.toPath());
            fulldir = folder + "\\"+name.replace("."+getFileExtension(name),"");
            if(args.length >= 3) {
                fulldir = folder+"\\"+args[2];
            }
            inputPatch = new File(fulldir+".patch"+String.valueOf(0));
            if(!inputPatch.isFile()) {inputPatch.createNewFile();}
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Wrong File or .patch0 not found!");
        }
        GetPatchParameters.ReadPatchParams(inter,inputPatch);
        Patcher.ParsePatchAndFindBytes(inter);
        Patcher.FindPtr(inter);
        if(!inter.every_pattern) Patcher.PathPtr(inter);
        for(int i =1; i<patchnum; i++){
            try {
                inputPatch = new File(fulldir+".patch"+String.valueOf(i));
            }
            catch (Exception e){System.out.println("Wrong File or .patch not found!");}
            GetPatchParameters.ReadPatchParams(inter,inputPatch);
            Patcher.ParsePatchAndFindBytes(inter);
            Patcher.FindPtr(inter);
            if(!inter.every_pattern) Patcher.PathPtr(inter);
        }
        Patcher.SafeLib(inter);
    }
}
