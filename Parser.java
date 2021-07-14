import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public static List<Pixel> parse(File file){
        List<Pixel> images = new ArrayList<>();
        char imageClass;

        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                scanner.nextLine();
                imageClass = scanner.nextLine().charAt(1);
                scanner.nextLine();

                String First = scanner.nextLine();
                String Second = scanner.nextLine();
                String[][] image = ImageAnalyser(First+Second);
                Pixel pixel = new Pixel(image,imageClass);
                images.add(pixel);
            }
        }
        catch(Exception e){}

        return images;
    }

    public static String[][] ImageAnalyser(String line){

        int row = 10;
        int col = 10;
        String[][] values = new String[row][col];
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
               char character = line.charAt((i*10) + j);
               if(character=='0') values[j][i] = "false";
               else values[j][i] = "true";
            }
        }
        return values;
    }
}
