class CDC {
  float acc, vel, pos;
  int h = 75;
  int w = 25;

  CDC() {
    acc = 0.8;
    vel = 0;
    pos = ground;
  }

  void update() {
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

  void show() {
    fill(0, 255, 0);
    rect(100, pos - h, w, h);
  }

  void jump() {
    vel = -25;
  }
}
