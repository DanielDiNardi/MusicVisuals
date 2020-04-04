package c18487682;

import ie.tudublin.Visual;

public class cubeWave extends Visual
{

    public void settings()
    {
        fullScreen(P3D, SPAN);
    }
    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            
        }
    }
    public void setup()
    {
        colorMode(HSB);
        noCursor();
        background(0);
        
        setFrameSize(256);

        startMinim();
        loadAudio("Glinhorn Forest.mp3");

        ortho(-1000, 1900, -2000, 2000, -2000, 2000);
        //ortho(left, right, bottom, top, near, far);

        for(int i = 0; i < stars.length; i++)
        {
            stars[i] = new Stars();
        }
    }

    float ya = QUARTER_PI;
    float xa = -QUARTER_PI;
    int col = 14;
    int row = 14;
    float pz = width / 2;
    float px = width / 2;
    float py = height / 2;
    float amp = 0;
    float maxD = dist(0, 0, width / 28f, width / 28f);
    Stars[] stars = new Stars[100];

    public void draw()
    {
        star();
        //carpet();
    }

    void carpet()
    {
        float x, y, z;
            x = width / 14f;
            y = 400;
            z = width / 14f;
            
            float offset = 0;

            calculateAverageAmplitude();
            translate(width/2, height/2, 0);
            rotateX(xa);
            rotateY(ya);
            fill(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
            stroke(0);
            
            for(int i = 0; i < col; i++)
            {
                for(int j = 0; j < row; j++)
                {
                    pushMatrix();

                    float d = dist(j, i, x, z);
                    offset = map(d, 0, maxD, -1, 1);
                    float a = (amp) + offset;
                    float h = map(sin(a), -1, 1, 0, y);

                    translate(px, 0, 0);
                    box(x, h, x);

                    px -= x;
                    
                    popMatrix();
                }
                // offset += 0.5;

                px = 650;

                pz = z;
                translate(0, 0, pz);

            }
            amp += getSmoothedAmplitude() * 1.5;
    }

    void star()
    {
        for(int i = 0; i < stars.length; i++)
        {
            stars[i].update();
            show();
        }
    }

    void show()
    {
        fill(255);
        noStroke();
        translate(width/2, height/2, 0);
        ellipse(c18487682.Stars.x, c18487682.Stars.y, 8, 8);
    }
}



