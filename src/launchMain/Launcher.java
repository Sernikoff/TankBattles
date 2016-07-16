package launchMain;

public class Launcher {

    public static void main(String[] args) throws Exception {
        ActionField af = null;
      try{
          af = new ActionField();
      }catch(Exception e){
          e.printStackTrace();
      }finally {
          af.getRegistrator().pushToFile();
      }
    }
}
