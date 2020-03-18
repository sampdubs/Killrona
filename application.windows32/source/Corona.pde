class Corona {
  PVector vel, pos;
  
  
  Corona() {
    vel = new PVector(-random(1, 5), 0);
    pos = new PVector(width, round(random(50, ground)));
  }
  
  void update() {
    pos.add(vel);
  }
  
  void show() {
    image(coronaPic, pos.x, pos.y, 140, 75);
  }
  
  
}
