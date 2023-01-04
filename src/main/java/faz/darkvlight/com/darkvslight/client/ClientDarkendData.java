package faz.darkvlight.com.darkvslight.client;

public class ClientDarkendData {
    private static int darkendPlayer;

    public static void set(int darkend)
    {
        ClientDarkendData.darkendPlayer = darkend;
    }

    public static int getPlayerDarkend(){
        return darkendPlayer;
    }
}
