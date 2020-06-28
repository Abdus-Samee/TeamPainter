package sample;

import java.io.DataInputStream;

public class network {

    public network(DataInputStream dis, Controller controller){
        conn obj = new conn(dis, controller);
        obj.start();
    }

    private class conn extends Thread{
        DataInputStream dis;
        Controller controller;

        public conn(DataInputStream dis, Controller controller){
            this.dis = dis;
            this.controller = controller;
        }

        @Override
        public void run(){
            while(true){
                try{
                    String temp = dis.readUTF();
                    String[] arr = temp.split(" ");

                    if(temp.charAt(0) == 'p'){
                        controller.drawFirst(arr[1], arr[2]);
                    }
                    else{
                        controller.drawNext(arr[0], arr[1]);
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }
}
