package xytest;


/**
 * @author JianLong
 * @date 2016/3/9 10:44
 */
public class TgtChild extends Tgt{

    private static ThreadLocal<DataContext> dataContet;

    public ThreadLocal getDataContet() {
        return dataContet;
    }

    public void setDataContet(ThreadLocal dataContet) {
        TgtChild.dataContet = dataContet;
    }

    public void setData(DataContext o){
        dataContet.set(o);
    }

    public Object getData(){
        return dataContet.get();//.getValue();
    }

    public String getTest(){
        return "hdhdhdh";
    }


    public TgtChild(){
        dataContet = new ThreadLocal<>();
    }

    /*
    @ValidateAnnotation(dataclass = Data.class)
    public void process(){
        System.out.println("_____chin______");
        a();
        DataContext dc = dataContet.get();
        Data d = (Data) dc.getValue();
        System.out.println("Object this "+this);
        System.out.println("processing" + d.getId());
        System.out.println("_____chout______");
    }*/

    public void a(){
        System.out.println("childinfunctiona");
    }

}
