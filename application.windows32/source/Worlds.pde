class Worlds {
  PVector vel, pos;
  
  
  Worlds() {
    vel = new PVector(-random(1, 5), 0);
    pos = new PVector(width, round(random(50, ground)));
  }
  
  void update() {
    pos.add(vel);
  }
  
  void show() {
    image(worldsPic, pos.x, pos.y, 95, 75);
  }
}
