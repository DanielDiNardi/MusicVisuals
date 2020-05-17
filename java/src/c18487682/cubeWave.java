package c18487682;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

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
        //loadAudio("Baby's On Fire.mp3");
        loadAudio("STAR.mp3");

        //ortho(-1000, 1900, -1000, 1000, -2000, 2000);
    }

    float ya = QUARTER_PI;
    float xa = -QUARTER_PI;
    int col = 15;
    int row = 15;
    float pz = width / 2;
    float px = width / 2;
    float py = height / 2;
    float amp = 0;
    float maxD = dist(0, 0, 700, -700);
    int colour = 0;
    float[] colourBands;
    int colourBandsCounter = 0;
    float bandsColour;

    public void draw()
    {
        try 
        {
            calculateFFT();
        } catch (VisualException e) 
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        colourBands = getSmoothedBands();
        if(colourBandsCounter < 7 && frameCount % 10 == 0)
        {
            bandsColour = map(colourBands[colourBandsCounter], 0, colourBands.length, 0, 255);
        }
        colourBandsCounter++;
        if(colourBandsCounter > 7)
        {
            colourBandsCounter = 0;
        }

        if(colour < 255)
        {
            colour += getSmoothedAmplitude() * 8;
        }
        else
        {
            colour = 0;
        }

        background(0);
        fill(bandsColour % 255, 200, 150);
        rectangles();
        circles();
        fill(colour, 200f, 150f);
        carpet();
    }

    void rectangles()
    {
        noStroke();
        int size = 20;
        float space = width / 20;
        int counter = 0;

        for(int i = 0; i < width; i++)
        {
            if(counter < 6 || counter > 12)
            {
                rect(space, height / 2, size, getSmoothedAmplitude() * 1000);
                rect(space, height / 2, size, getSmoothedAmplitude() * -1000);
            }

            counter++;
            space += width / 20;
        }
    }

    void circles()
    {
        ellipse(width / 2.5f - (getSmoothedAmplitude() * 1000), height / 3f - (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000));
        ellipse(width / 1.65f + (getSmoothedAmplitude() * 1000), height / 3f - (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000));
        ellipse(width / 2.5f - (getSmoothedAmplitude() * 1000), height / 1.5f + (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000));
        ellipse(width / 1.65f + (getSmoothedAmplitude() * 1000), height / 1.5f + (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000));
    }

    void carpet()
    {
        float x, y, z;
        x = 50;
        y = getSmoothedAmplitude() * 1000;
        z = 50;
            
        float offset = 0;

        calculateAverageAmplitude();
        translate(width/4, height/2, -500);
        rotateX(xa);
        rotateY(ya);
        stroke(0);
        lights();


        for(int i = 0; i < col; i++)
        {
            for(int j = 0; j < row; j++)
            
            {
                pushMatrix();

                float d = dist(j * x, i * z, 400, 400);
                offset = map(d, 0, maxD, -2, 1);
                float a = (amp) + offset;
                float h = map(sin(a), 0, 1, 0, y);
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


