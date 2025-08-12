import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void StickOrSwitch (String[] chosenDoor, String[] noCarDoor, String[] switchDoor) {
        Scanner sc = new Scanner(System.in);
        String lose = "\nUnfortunately, you did not win a car.";
        String win = "\nCongratulations, you won a car!";
        String stickOrSwitch = "";

        //inform user which unchosen door has no car
        //ask user if they want to stick to their door or switch doors
        System.out.println("I have decided to let you know that Door " + noCarDoor[0] + " does not have a car. Would you like to stick with Door " + chosenDoor[0] + " or switch to Door " + switchDoor[0] + "?");

        //user input
        while(!stickOrSwitch.equals(chosenDoor[0]) && !stickOrSwitch.equals(switchDoor[0])) {
            stickOrSwitch = sc.nextLine();
        }

        //user chooses to stick with their door and there is a car
        if(stickOrSwitch.equals(chosenDoor[0]) && chosenDoor[1].equals("car")) {
            System.out.println(win);
        }
        //user chooses to stick with their door and there is no car
        else if(stickOrSwitch.equals(chosenDoor[0]) && chosenDoor[1].equals("goat")) {
            System.out.println(lose);
        }
        //user chooses to switch doors and there is a car
        else if(stickOrSwitch.equals(switchDoor[0]) && switchDoor[1].equals("car")) {
            System.out.println(win);
        }
        //user chooses to switch doors and there is no car
        else if(stickOrSwitch.equals(switchDoor[0]) && switchDoor[1].equals("goat")) {
            System.out.println(lose);
        }
    }
    public static void DoorOutcome (String[] chosenDoor, String[] unchosenDoor1, String[] unChosenDoor2) {
        Random rand = new Random();

        //if the user chooses the door with the car
        if(chosenDoor[1].equals("car")) {

            //store unchosen doors in an array list
            ArrayList<String[]> unchosenDoors = new ArrayList<>();
            unchosenDoors.add(unchosenDoor1);
            unchosenDoors.add(unChosenDoor2);

            //randomly choose any one of the two unchosen doors
            int removeDoor = rand.nextInt(0,2);
            int otherUnchosenDoor = (0 == removeDoor) ? 1 : 0;

            StickOrSwitch(chosenDoor, unchosenDoors.get(removeDoor), unchosenDoors.get(otherUnchosenDoor));
        }
        //if the user chooses the door without the car, scenario 1
        else if(unchosenDoor1[1].equals("car")) {
            StickOrSwitch(chosenDoor, unChosenDoor2, unchosenDoor1);
        }
        //if the user chooses the door without the car, scenario 2
        else if(unChosenDoor2[1].equals("car")) {
            StickOrSwitch(chosenDoor, unchosenDoor1, unChosenDoor2);
        }

    }

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        String[] objects = {"goat", "goat", "car"};

        String[] doorOne = {"1", null};
        String[] doorTwo = {"2", null};
        String[] doorThree = {"3", null};
        int one;
        int two;
        int three;
        String choice = "";


        //assign door one
        one = rand.nextInt(0, objects.length);
        doorOne[1] = objects[one];

        //assign door two
        do {
            two = rand.nextInt(0, objects.length);
            doorTwo[1] = objects[two];
        }
        while (one == two);

        //assign door three
        do {
            three = rand.nextInt(0, objects.length);
            doorThree[1] = objects[three];
        }
        while (three == two || three == one);

        //ask user for which door they would like to choose
        System.out.println("There are three doors: Door 1, Door 2, and Door 3.");
        System.out.println("Behind one of the doors is a brand new car of your choice. Behind the other two doors are goats.");
        System.out.println("Which door would you like to choose (1, 2, or 3)?");

        //user input
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            choice = sc.nextLine();

        }

        //switch cases for the door that the user chooses
        switch (choice) {
            //user chooses door 1
            case "1":
                DoorOutcome(doorOne, doorTwo, doorThree);
            break;

            //user chooses door 2
            case "2":
                DoorOutcome(doorTwo, doorOne, doorThree);
            break;

            //user chooses door 3
            case "3":
                DoorOutcome(doorThree, doorOne, doorTwo);
            break;
        }

        System.out.println("Door 1: " + doorOne[1] + ", Door 2: " + doorTwo[1] + ", Door 3: " + doorThree[1]);
    }
}