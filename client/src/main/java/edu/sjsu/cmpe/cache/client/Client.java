package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.List;
import com.google.common.hash.Hashing;
public class Client {

    public static void main(String[] args) throws Exception {
        int j,j1;
        System.out.println("Starting Cache Client...");
        char[] value = {'0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        List<DistributedCacheService> dcs = new ArrayList<DistributedCacheService>();
        //starting servers
        dcs.add(new DistributedCacheService("http://localhost:3000"));

        dcs.add(new DistributedCacheService("http://localhost:3001"));

        dcs.add(new DistributedCacheService("http://localhost:3002"));

        for (int i = 1; i<= 10;  i++) {


            int k = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(i)),dcs.size());
           // System.out.println("The key is:"+k);
            if ( k == 0)
            {
                System.out.println("Sending data to Server 3000:-");

            }

            if ( k == 1 )
            {
                System.out.println("Sending data to Server 3001:-");

            }

            if ( k == 2 )
            {
                System.out.println("Sending data to Server 3002:-");

            }

            dcs.get(k).put(i, Character.toString(value[i]));

            System.out.println("The key value pair " + i + "-" + value[i] + " is assigned to server " + k);
            System.out.println("");
        }
        for (int i1 = 1; i1 <= 10; i1++) {
            int k1 = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(i1)),dcs.size());
          //  System.out.println("The key is:"+k1);
            System.out.println("");
            System.out.println("The key value pair " +  i1 + "-" + dcs.get(k1).get(i1) + " is received to server " + k1);


            if ( k1 == 0)
            {
                System.out.println(" Getting  data to Server 3000");

            }

            if ( k1 == 1 )
            {
                System.out.println("Getting data to Server 3001");

            }

            if ( k1 == 2 )
            {
                System.out.println(" Getting data to Server 3002");

            }
        }

    }
}
