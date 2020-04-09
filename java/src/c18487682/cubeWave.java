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
        
        setFrameSize(256);

        startMinim();
        loadAudio("Timecop1983 - On the Run.mp3");

        ortho(-1000, 1900, -1000, 1000, -2000, 2000);
        //ortho(left, right, bottom, top, near, far);
    }

    float ya = QUARTER_PI;
    float xa = -QUARTER_PI;
    int col = 15;
    int row = 15;
    float pz = width / 2;
    float px = width / 2;
    float py = height / 2;
    float amp = 0;
    float maxD = dist(0, 0, 400, -400);
    int colour = 0;

    public void draw()
    {
        if(colour < 255)
        {
            colour += getSmoothedAmplitude() * 8;
        }
        else
        {
            colour = 0;
        }

        background(0);
        // fill(map(getSmoothedAmplitude(), 0f, 1f, 0f, 255f), 200f, 150f);
        fill(colour, 200f, 150f);
        carpet();
    }

    void carpet()
    {
        float x, y, z;
        x = 50;
        y = getSmoothedAmplitude() * 1000;
        z = 50;
            
        float offset = 0;

        // calculateFFT();
        // calculateFrequencyBands();
        calculateAverageAmplitude();
        translate(width/2, height/2, 0);
        rotateX(xa);
        rotateY(ya);
        stroke(0);
        lights();

        // println(getSmoothedBands());

        for(int i = 0; i < col; i++)
        {
            for(int j = 0; j < row; j++)
            
            {
                pushMatrix();

                float d = dist(j * x, i * z, 400, 400);
                offset = map(d, 0, maxD, -2, 1);
                float a = (amp) + offset;
                float h = map(sin(a), 0, 1, 0, y);

                // fill(map(getSmoothedAmplitude(), 0f, 1f, 0f, 255f), 200f, 150f); 
                translate(px, 0, 0);
                box(x, h, x);

                px -= x;
                    
                popMatrix();
            }

            px = 700;

            pz = z;
            translate(0, 0, pz);

        }
        amp += getSmoothedAmplitude() * 0.25f;
    }
}



