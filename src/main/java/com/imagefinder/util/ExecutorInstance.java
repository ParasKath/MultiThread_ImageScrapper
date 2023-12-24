package com.imagefinder.util;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorInstance {

    private final int poolsize = 2000;
    private static final int parts = 10;
    public static ExecutorService[] executors = null;

    private ExecutorInstance() {
        executors = new ExecutorService[parts];
        for(int i=0;i<parts;i++)
        {
            executors[i] = Executors.newFixedThreadPool(poolsize);
        }
    }

    private static int generateHashCode()
    {
        Random random = new Random();
        int randomValue = random.nextInt(parts+1);
        return randomValue;
    }
    public static ExecutorService getinstance(){
        if(executors==null)
        {
            new ExecutorInstance();
        }
        try{
            return executors[generateHashCode()];
        }
        catch (Exception e)
        {
            // Handle the case if generateHashCode generates value greater than 10 or less than 0
            return executors[parts/2];
        }

    }

}
