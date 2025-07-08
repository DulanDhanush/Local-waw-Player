import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Player {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String filepath;

        System.out.print("----Started Player---- ");
        System.out.print("Enter full path to your .wav file: ");
        filepath = scanner.nextLine().replace("\\", "\\\\");

        File file = new File(filepath);
        if(!file.exists()){
            System.out.println("File Not Found Please Check Again!");
            
            return;
        }

        try(AudioInputStream audiostream =  AudioSystem.getAudioInputStream(file)) {

            Clip clip = AudioSystem.getClip();
            clip.open(audiostream);

            String response = "";

            while(!response.equals("Q")){

                System.out.println("P = Play");
                System.out.println("S = Stop");
                System.out.println("R = Reset");
                System.out.println("Q = Quit");
                System.out.print("Enter your choice: ");
                response = scanner.next().toUpperCase();

                switch(response){

                    case "P" ->{
                    if(!clip.isRunning())
                    { clip.loop(Clip.LOOP_CONTINUOUSLY);
                         clip.start();
                    }else{
                        System.out.println("Already Playing!");
                    }}
                    case "S" -> clip.stop();
                    case "R" -> clip.setMicrosecondPosition(0);
                    case "Q" -> clip.close();
                    default -> System.out.println("Invalid choice!");
                }

            }
            
        }
        catch(LineUnavailableException e){
            System.out.println("Unable to access audio resources!");

        }
        catch(UnsupportedAudioFileException e){
            System.out.println("Audio File is not Supported!");

        }
         catch (IOException e) {
            System.out.println("Something Went Wrong!");
        }
        finally{
            System.out.println("GoodBye!");
            scanner.close();
        }
    }
}
    
