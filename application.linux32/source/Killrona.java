import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Killrona extends PApplet {

ArrayList<Corona> viruses = new ArrayList<Corona>();
ArrayList<Worlds> events = new ArrayList<Worlds>();
ArrayList<Vaccine> bullets = new ArrayList<Vaccine>();
CDC player;
int ground;
PImage coronaPic;
PImage worldsPic;

public void setup() {
  
  //size(1200, 800);
  background(255);
  noStroke();
  player = new CDC();
  ground = height - 100;
  coronaPic = loadImage("corona.png");
  worldsPic = loadImage("worlds.png");
}

public void draw() {
  background(255);
  player.update();
  player.show();
  if (random(1) > 0.997f) {
    viruses.add(new Corona());
  }
  colorMode(HSB);
  for (Vaccine shot : bullets) {
    shot.update();
    shot.show();
  }
  colorMode(RGB);
  for (Corona virus : viruses) {
    virus.update();
    virus.show();
  }
  if (random(1) > 0.997f) {
    events.add(new Worlds());
  }
  for (Worlds comp : events) {
    comp.update();
    comp.show();
  }

  for (int i = bullets.size() - 1; i >= 0; i--) {
    Vaccine b = bullets.get(i);
    if (b.pos.x > width) {
      bullets.remove(b);
      continue;
    }
    for (int j = viruses.size() - 1; j >= 0; j--) {
      Corona v = viruses.get(j);
      if (v.pos.x < -120) {
        loose();
        continue;
      }
      if (b.intersects(PVector.add(v.pos, new PVector(70, 35)), 35)) {
        bullets.remove(b);
        viruses.remove(v);
      }
    }
    for (int j = events.size() - 1; j >= 0; j--) {
      Worlds e = events.get(j);
      if (e.pos.x < -50) {
        events.remove(e);
        continue;
      }
      if (b.intersects(PVector.add(e.pos, new PVector(50, 35)), 40)) {
        loose();
      }
    }
  }
}

public void keyPressed() {
  if (key==' ') {
    player.jump();
  } else if (key == 'q') {
    bullets.add(new Vaccine(player.pos - player.h/2));
  }
}

public void loose() {
  background(0);
  textAlign(CENTER, CENTER);
  textSize(width / 10);
  fill(255, 0, 0);
  text("YOU LOST", width / 2, height / 2);
  noLoop();
}
class CDC {
  float acc, vel, pos;
  int h = 75;
  int w = 25;

  CDC() {
    acc = 0.8f;
    vel = 0;
    pos = ground;
  }

  public void update() {
    if (pos >= ground && vel >= 0) {
      pos = ground;
      vel = 0;
    } else if (pos - h <= 0) {
      vel = 0;
      pos = h + 1;
    } else {
      pos += vel;
      vel += acc;
    }
  }

  public void show() {
    fill(0, 255, 0);
    rect(100, pos - h, w, h);
  }

  public void jump() {
    vel = -25;
  }
}
class Corona {
  PVector vel, pos;
  
  
  Corona() {
    vel = new PVector(-random(1, 5), 0);
    pos = new PVector(width, round(random(50, ground)));
  }
  
  public void update() {
    pos.add(vel);
  }
  
  public void show() {
    image(coronaPic, pos.x, pos.y, 140, 75);
  }
  
  
}
class Vaccine {
  float vel;
  int col;
  PVector pos;
  int h = 20;
  int w = 40;

  Vaccine(float y) {
    vel = 8;
    pos = new PVector(125, y);
    col = 0;
  }

  public void update() {
    pos.x += vel;
    col += 10;
    col = col % 256;
  }

  public void show() {
    fill(col, 255, 255);
    rect(pos.x, pos.y, w, h);
  }

  public void jump() {
    vel = -25;
  }
  
  public boolean intersects(PVector circPos, int r) {
    float closeX = circPos.x;
    float closeY = circPos.y;
    
    if (circPos.x < pos.x) closeX = pos.x;
    if (circPos.x > pos.x + w) closeX = pos.x + w;
    if (circPos.y < pos.y) closeY = pos.y;
    if (circPos.y > pos.y + h) closeY = pos.y + h;
    
    return dist(closeX, closeY, circPos.x, circPos.y) < r;
  }
 
}
class Worlds {
  PVector vel, pos;
  
  
  Worlds() {
    vel = new PVector(-random(1, 5), 0);
    pos = new PVector(width, round(random(50, ground)));
  }
  
  public void update() {
    pos.add(vel);
  }
  
  public void show() {
    image(worldsPic, pos.x, pos.y, 95, 75);
  }
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Killrona" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
