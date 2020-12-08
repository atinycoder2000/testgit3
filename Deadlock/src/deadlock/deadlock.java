package deadlock;
public class deadlock implements Runnable{
    public int flag=1;
    public static Object o1=new Object(),o2=new Object();
    @Override
    public void run() {
        if(flag==1){
          synchronized (o1) {
              try {
                  Thread.sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              synchronized (o2){
                  System.out.println("1");
              }
          }
        }
        else{
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("2");
                }
            }
        }

    }
    public static void main(String[] args){
        deadlock d1=new deadlock();
        deadlock d2=new deadlock();
        d1.flag=1;
        d2.flag=0;
        new Thread(d1).start();
        new Thread(d2).start();
    }
}
