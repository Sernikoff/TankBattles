package launchMain;

import java.io.*;
import java.util.LinkedList;

public class Registrator {
    private LinkedList<String> list;

    Registrator(){
        list = new LinkedList<>();
    }

    public LinkedList<String> getList() {
        return list;
    }

    public void addList(String action){
        list.add(action);
    }

    public void pushToFile(){
        File file = new File("Registrator.txt");
        FileOutputStream fos = null;
        OutputStreamWriter in = null;
        BufferedWriter buff = null;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            in = new OutputStreamWriter(fos);
            buff = new BufferedWriter(in);

            String result = "";
            for (String str:list
                 ) {
                result+=str+" "+"\r\n";
                }
            buff.write(result);
        }catch(IOException e){
                e.printStackTrace();
            }finally{
                try{
                    buff.close();
            }catch(IOException e1){
                    e1.printStackTrace();
                }
        }
    }
}
