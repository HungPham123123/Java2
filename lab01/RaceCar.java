package lab01;

class RaceCar extends Thread
{
    int finish;
    String name;

    RaceCar(int finish, String name){
        this.finish = finish;
        this.name = name;
    }
    //Override run() method for racing logic
    public void run(){
        for (int i=0; i<finish; i++){
            System.out.println(name + ": " + (i+1) + "Running...");

            try {
                //sleep for a random amount of time. To get a random value,
                //java.util.Math.random() method is used.
                Thread.sleep(Math.round(Math.random()*5000));
            } catch(Exception e){}
        }

        System.out.println(name + " finished");
    }
}

