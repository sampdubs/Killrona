ArrayList<Corona> viruses = new ArrayList<Corona>();
ArrayList<Worlds> events = new ArrayList<Worlds>();
ArrayList<Vaccine> bullets = new ArrayList<Vaccine>();
CDC player;
int ground;
PImage coronaPic;
PImage worldsPic;

void setup() {
  fullScreen();
  //size(1200, 800);
  background(255);
  noStroke();
  player = new CDC();
  ground = height - 100;
  coronaPic = loadImage("corona.png");
  worldsPic = loadImage("worlds.png");
}

void draw() {
  background(255);
  player.update();
  player.show();
  if (random(1) > 0.997) {
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
  if (random(1) > 0.997) {
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

void keyPressed() {
  if (key==' ') {
    player.jump();
  } else if (key == 'q') {
    bullets.add(new Vaccine(player.pos - player.h/2));
  }
}

void loose() {
  background(0);
  textAlign(CENTER, CENTER);
  textSize(width / 10);
  fill(255, 0, 0);
  text("YOU LOST", width / 2, height / 2);
  noLoop();
}
