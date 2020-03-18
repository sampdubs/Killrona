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

  void update() {
    pos.x += vel;
    col += 10;
    col = col % 256;
  }

  void show() {
    fill(col, 255, 255);
    rect(pos.x, pos.y, w, h);
  }

  void jump() {
    vel = -25;
  }
  
  boolean intersects(PVector circPos, int r) {
    float closeX = circPos.x;
    float closeY = circPos.y;
    
    if (circPos.x < pos.x) closeX = pos.x;
    if (circPos.x > pos.x + w) closeX = pos.x + w;
    if (circPos.y < pos.y) closeY = pos.y;
    if (circPos.y > pos.y + h) closeY = pos.y + h;
    
    return dist(closeX, closeY, circPos.x, circPos.y) < r;
  }
 
}
