import java.io.*;
import java.lang.*;
import java.util.*;

public class Sock
{
    public String color;
    public int captured;
    public Sock(String input_color) 
    {
        this.color = input_color;
        this.captured = 0;
    }

    public String getColor(){
        return color;
    }

    public void getSock(){
        captured = 1;
    }
}