import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;
import java.io.IOException;
import java.lang.Math;
import java.math.BigInteger;

//Adhavan T CSE 6A 19cs004 Kcg college of Technology
//Rename this file to Memoranda

//Public class Memoranda {
public class CSE_Adhavan_T {

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome to Memoranda!");
        System.out.println("\nPlease enter your name: ");
        String name = sc.nextLine();    

        System.out.println("\nYou want to know about rules? (y/n)");
        String rules = sc.nextLine();

        if ((rules.toUpperCase()).equals("Y")) {
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            System.out.println("Rules of the game: \n");
            System.out.println("The number of digits dictates the number of digits that will be shown to you to memorize. The digits will be shown for values provided in “Display in seconds”. Once the number disappears, you should be promoted for input. Once you enters the value, the result is whether the number is correct or not. Then prompt for Continue/Exit. If you prefers to continue, the whole process repeats. When you decides not to continue, then the application should print the name, number of  attempts, score (number of correct attempts).");    
        }else {
            System.out.println("Let's start!\n");
        }
 
        int score = 0;
        int attempt = 0;
        int successrate = 0;
        int failrate = 0;
        int sec_Autopilot = 0;
        
        //Memoranda calcs = new Memoranda();
        CSE_Adhavan_T calcs = new CSE_Adhavan_T();
        int autopilot = autopilot(args);
        int digits = 0;
        if(autopilot == 1){
            System.out.println("\nEnter length of the number you want to guess  of \"3\" - \"10\" then it is easy to remember: ");
            digits = sc.nextInt();
            if(digits >=3){
                System.out.println("Enter the number of seconds you want to display the number: ");
                sec_Autopilot = sc.nextInt();
                while(sec_Autopilot <= 0){
                    System.out.println("\nYou have entered invalid timing so it default to 3 seconds\n");
                    sec_Autopilot =3;
                } 
                calcs.calc(args,name,score,attempt,successrate,failrate,digits,autopilot, sec_Autopilot);
            }else if(digits <3){
                while(digits < 3){
                    System.out.println("Autopilot mode requires length is greater than 3");
                    digits = sc.nextInt();
                }
                System.out.println("Enter the number of seconds you want to display the number: ");
                sec_Autopilot = sc.nextInt();
                while(sec_Autopilot <= 0){
                    System.out.println("\nYou have entered invalid timing so it default to 3 seconds\n");
                    sec_Autopilot =3;
                }             
                calcs.calc(args,name,score,attempt,successrate,failrate,digits,autopilot,sec_Autopilot);
            }   
            
            
        }else if(autopilot ==0){
            sec_Autopilot =0;
            calcs.calc(args,name,score,attempt,successrate,failrate,digits,autopilot, sec_Autopilot);
        }else{
      }         
    }
    
    //Calculation function
    private void calc(String[] args,String name, int score, int attempt,int successrate, int failrate, int digits_a,int autopilot, int sec_Autopilot) {
        Scanner sc = new Scanner(System.in);
        if(autopilot == 1){
            int ran_num = (int) (Math.random() * (Math.pow(10, digits_a) - 1));
            
            System.out.println("I will be shown "+ran_num+" for "+sec_Autopilot+" seconds, then I will be prompted for the enter the number");
            
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {                    
                    System.out.print("\033[H\033[2J");
                    display(ran_num,timer,args,name,score,attempt,successrate, failrate,digits_a,autopilot, sec_Autopilot);
                }
            };

            timer.schedule(task, sec_Autopilot * 1000);
        }else if(autopilot == 0){
            System.out.println("\nEnter length of the number you want to guess with the range of \"1\" - \"10\" then it is easy to remember: ");
            int digits_NA = sc.nextInt();
            System.out.println("Enter the number of seconds you want to display the number: ");
            int sec = sc.nextInt();
            if(sec <= 0){
                System.out.println("\nYou have entered invalid timing so it default to 3 seconds\n");
                sec =3;
            }
            int ran_num = (int) (Math.random() * (Math.pow(10, digits_NA) - 1));
            
            System.out.println("I will be shown "+ran_num+" for "+sec+" seconds, then I will be prompted for the enter the number");
            
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {                    
                    System.out.print("\033[H\033[2J");
                    display(ran_num,timer,args,name,score,attempt,successrate, failrate,digits_NA,autopilot, sec_Autopilot);                    
                }
            };   
            timer.schedule(task, sec * 1000);
        }    
    }
    
    //Display function
    public int display(int ran_num, Timer timer, String[] args, String name, int score, int attempt,int successrate, int failrate, int digits,int autopilot, int sec_Autopilot){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the number: ");
        int num = sc.nextInt();
        if (num == ran_num) {
            if(autopilot == 1){
                successrate ++;
                failrate = 0;
            }
            System.out.println("\nYou are correct!");
            score ++;
                    attempt = attempt + 1;
                    timer.cancel();
                } else {
                    if(autopilot == 1){
                        successrate = 0;
                        failrate ++;
                    }
                    System.out.println("\nYou are wrong!, the correct number is "+ran_num);
                    attempt = attempt + 1;
                    timer.cancel();
                }
                System.out.println("\nYou want to continue PRESS 1 or EXIT PRESS 0");
                int ans = sc.nextInt();
                if(ans == 1){
                    if(autopilot == 1){
                        if(successrate == 3){
                            successrate = 0;
                            digits = digits + 1;
                            System.out.println("The number of digits has increased by 1");
                        }
                        if(failrate == 3){
                            failrate = 0;
                            if(autopilot == 1 && digits >=3){
                                digits = digits - 1;
                                System.out.println("The number of digits has decreased by 1");
                            }
                            if(digits == 2){
                                System.out.println("\nYou Lost consecutively 3 times till length 3 so game ends");
                                System.out.println("\n"+ name + " your score is: "+score + " out of " + attempt + " attempts \nFailed to guess: " +(attempt-score));
                                System.exit(0);
                            }

                        }
                    }
                    System.out.println(" ");
                    calc(args, name, score, attempt,successrate,failrate,digits,autopilot, sec_Autopilot);
                }
                else if(ans == 0){
                    System.out.println("\n"+ name + " your score is: "+score + " out of " + attempt + " attempts \nFailed to guess: " +(attempt-score));
                }
                else{
                    System.out.println("\nYou have entered invalid option so it default to 0");
                    System.out.println("\n"+ name + " your score is: "+score + " out of " + attempt + " attempts \nFailed to guess: " +(attempt-score));
                }
                return num;   
            }
            
            //Clear screen function
            public static void clearScreen() {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

            //Autopilot function
            private static int autopilot(String[] args){
                Scanner sc = new Scanner(System.in);
                System.out.println("Do you want Autopilot mode ON\n");
                //clearScreen();
                System.out.print("type \"Y\" or \"N\" to choose autopilot mode and  \"H\"  for Know more about autopilot: ");
                String autopilot_mode = sc.nextLine();
                if ((autopilot_mode.toUpperCase()).equals("Y")|| autopilot_mode.equals("Y")) {
                    clearScreen();
                    System.out.println("Autopilot mode ON");
                    return 1;
                }else if ((autopilot_mode.toUpperCase()).equals("N") || autopilot_mode.equals("N")) {
                    System.out.println("Autopilot mode OFF");
                    return 0;
                }else if ((autopilot_mode.toUpperCase()).equals("H") || autopilot_mode.equals("H")) {
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();  
                    System.out.println("\nAbout Autopilot mode: \n");
                    System.out.println("If turned on, after 3 successful attempts, the number of digits will increase by one automatically. After 3 wrong attempts, the number of digits will decrease by 1 automatically until the number of digits is 3.");
                    return autopilot(args);
                }else{
                    System.out.println("Invalid input");
                    return autopilot(args);
                } 
            }
        }