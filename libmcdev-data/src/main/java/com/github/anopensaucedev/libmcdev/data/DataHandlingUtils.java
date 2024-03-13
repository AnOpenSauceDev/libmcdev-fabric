package com.github.anopensaucedev.libmcdev.data;

import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.TempNameGenerator;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class DataHandlingUtils { // dataHandlingLib but better (built from the ground up, platform checks no longer needed)

    // Key-Value... database? allows data to be stored for players in binary.

    String Namespace;

    /**
     * Starting multiple writes will distribute across multiple threads. Be careful with thread safety!
     */
    public ThreadedDataWriter Writer = new ThreadedDataWriter();

    public DataReader Reader = new DataReader();

    /**
     *
     * @param namespace The name of the folder dedicated to your data. You should use your ModID if you can.
     */
    public DataHandlingUtils(String namespace){
        this.Namespace = namespace;
    }

    public class ThreadedDataWriter{

        public void WriteData(String key,char[] data){ // This is *NOT* thread-safe! Please be VERY careful when writing important data!

            Debug ThreadDebugger = new Debug(TempNameGenerator.returnTempName());

            ThreadDebugger.LogWarning("attempting to write: " + key);

                new Thread(() ->{



                Path path2 = Paths.get("PersistentDataHandler",Namespace,key);
                Path path = FabricLoader.getInstance().getGameDir().resolve(path2);
                ThreadDebugger.Log("creating dir with path: " + path);
                try {
                    String finalpath = path.toFile().getAbsolutePath();
                    File directory = new File(finalpath);
                    directory.mkdirs();
                    File file = new File(finalpath, (key + ".bin"));

                    //file.createNewFile();
                    FileWriter Writer = new FileWriter(file);
                    Writer.write(data);
                    Writer.flush(); // remember to ALWAYS flush your files!
                    Writer.close();


                } catch (IOException e) {
                    ThreadDebugger.LogError(e.getLocalizedMessage());
                    throw new RuntimeException(e);

                }

                 }).start();

        }

    }

    public class DataReader{
        public byte[] ReadData(String key){ // not threaded yet because im lazy
            Debug ThreadDebugger = new Debug(TempNameGenerator.returnTempName());

            byte[] data = null;





            Path path2 = Paths.get("PersistentDataHandler",Namespace,key);
            Path path = FabricLoader.getInstance().getGameDir().resolve(path2);
            try {
                String finalpath = path.toFile().getAbsolutePath();
                File directory = new File(finalpath);
                directory.mkdirs();
                File file = new File(finalpath, (key + ".bin"));

                if(file.exists()) {
                    data = Files.readAllBytes(file.toPath());
                }else {
                    throw new IOException("WARNING! You are trying to read a file that doesn't exist! Did you cause a race condition?");
                }

            } catch (IOException e) {
                ThreadDebugger.LogError(e.getLocalizedMessage());
                throw new RuntimeException(e);

            }


            return data;
        }
    }

}
