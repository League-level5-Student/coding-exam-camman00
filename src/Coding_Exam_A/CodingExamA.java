package Coding_Exam_A;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	private Scanner sc;
	private int numRobots;
	private Color color;
	private int numSides;
	public static void main(String[] args) {
		/*
		 * Write a program that asks the user for three pieces of information.
		 * 1. How many robots
		 * 2. The color of the shapes
		 * 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the requested number of robots
		 * each draw the requested shape in the requested color. The robots should execute at the same time so 
		 * Threads will need to be used. Arrange the robots so that the shapes do not overlap.
		 * For full credit, define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product should look like.
		 */
		new CodingExamA();
	}
	public CodingExamA() {
		sc = new Scanner(System.in);
		System.out.println("How many robots do you want (enter a num)");
		numRobots = sc.nextInt();
		System.out.println("What color should they be?");
		color = Color.getColor(sc.next().toUpperCase());
		System.out.println("How many sides (enter a num)");
		numSides = sc.nextInt();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i < numRobots; i++) {
			threads.add(new Thread(()-> {
				Robot r = new Robot();
				r.setPenColor(color);
				r.setX(new Random().nextInt(400));
				r.setAngle(360/numSides);
				r.setSpeed(10);
				r.penDown();
				int a = numSides;
				while(a > 0) {
					r.move(100);
					r.turn(360/numSides);
					a--;
				}
			}));
		}
		for(Thread thread : threads) {
			thread.start();
		}
	}
}
