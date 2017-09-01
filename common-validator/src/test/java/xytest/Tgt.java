package xytest;


/**
 * @author JianLong
 * @date 2016/3/9 10:44
 */
public abstract class Tgt {

    private static ThreadLocal<DataContext> dataContet;

    public ThreadLocal getDataContet() {
        return dataContet;
    }

    public void setDataContet(ThreadLocal dataContet) {
        Tgt.dataContet = dataContet;
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


    public Tgt(){
        dataContet = new ThreadLocal<>();
    }

    @ValidateAnnotation(dataclass = Data.class)
    public void process(){
        System.out.println("_____fpin______"+this);
        a();
//        DataContext dc = dataContet.get();
//        Data d = (Data) dc.getValue();
//        System.out.println("Object this "+this);
//        System.out.println("processing" + d.getId());
        System.out.println("_____fpout______");
    }

    abstract void a();
    /*{
        System.out.println("fatherA");
    }
    */

}
