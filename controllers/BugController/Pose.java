// File: Pose.java
// Date: 30th Dec 2020
// Description: Pose Class support for COMP329 Programming Assignment (2020)
// Author: Terry Payne
// Modifications:
//     Addition of getDeltaTheta() and update to the toString() method - 26th Nov 2021
/**
 * The Pose class for a robot 
 * Based on Worksheet 3 for COMP329, Nov 2020
 * 
 * @author Dr Terry R. Payne (trp@liv.ac.uk)
 *
 */
 
public class Pose {
  private double x;    // position on x axis - assume units are meters
  private double y;    // position on y axis - assume units are meters
  private double theta;  // This determines the angle (radians) anticlockwise from the x-axis line

  // ==================================================================================
  // Constructors
  // ==================================================================================
  public Pose() {
    this(0.0,0.0,0.0);
  }

  public Pose(double xpos, double ypos, double theta) {
    this.x = xpos;
    this.y = ypos;
    this.setTheta(theta);
  }

/*
 * Removed for simplicity for the python class
  public Pose(Pose p) {
    this.x = p.getX();
    this.y = p.getY();
    this.setTheta(p.getTheta());
  }
*/
  // ==================================================================================
  // Getters / Setters  
  // ==================================================================================
  public void setTheta(double theta) {
    // Ensure that theta is in the range -\pi..\pi
    if (theta>Math.PI) {
      this.theta=theta-(2 * Math.PI);
//      this.theta=(2 * Math.PI)-theta;    // THIS IS INCORRECT!  THANKS TO J.WOOD FOR SPOTTING THIS
    } else if (theta < -Math.PI) {
      this.theta = (2 * Math.PI)+theta;
    } else {
      this.theta = theta;
    }
  }

  public void setPosition(double xpos, double ypos, double theta) {
    this.x = xpos;
    this.y = ypos;
    this.setTheta(theta);
  }

  public void setPosition(Pose p) {
    this.setPosition(p.getX(), p.getY(), p.getTheta());
  }

  public String toString() {
    return String.format("<%.03f", this.x) + ", " +
        String.format("%.03f", this.y) + ", " +
        String.format("%.03f", this.theta) +">";
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getTheta() {
    return theta;
  }
  
  // Find the difference in radians between some heading and the current pose
  public double getDeltaTheta(double theta) {
    double d = theta - this.theta;
    if (d > Math.PI)
      d = -(2*Math.PI) + d;
    else if (d < -Math.PI)
      d = (2*Math.PI) + d;
    return d;
  }

  public double distanceTo(Pose p){
    return Math.sqrt(Math.pow(this.x - p.getX(), 2) + Math.pow(this.y - p.getY(), 2));
  }

  public double distanceTo(Vector2D v){
    return Math.sqrt(Math.pow(this.x - v.getX(), 2) + Math.pow(this.y - v.getY(), 2));
  }

  public double distanceTo(Point p){
    Vector2D v = p.getPosition();
    return Math.sqrt(Math.pow(this.x - v.getX(), 2) + Math.pow(this.y - v.getY(), 2));
  }

  public Vector2D toVector() {
    return new Vector2D(this.x, this.y);
  }
}