package c18487682;

import ie.tudublin.Visual;
import processing.core.PImage;

public class Space extends Visual
{
    PImage space;

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
        colorMode(RGB);
        noCursor();
        
        setFrameSize(256);

        startMinim();
        loadAudio("C418 - Sweden (Caution & Crisis Remix).mp3");
        space = loadImage("space.jpeg");
        space.resize(width, height);
    }

    public void draw()
    {
        float axialRotation = 0.005f;
        float offset = 250f;
        float sunDistanceOffset = 500f;
        calculateAverageAmplitude();
        //background(space);
        background(0);
        noStroke();
        pointLight(255, 255, 0, width/2, height/2, 0);
        camera(width * 2, height, 0, width/2, height/2, 0, 1, 0, 0);

        //Sun position, rotation, colour and creation
        stroke(c18487682.Sun.colourR,c18487682.Sun.colourG,c18487682.Sun.colourB);
        translate(width/2, height/2);
        rotateY(c18487682.Sun.rotation);
        //fill(c18487682.Sun.colourR,c18487682.Sun.colourG,c18487682.Sun.colourB);
        sphere(c18487682.Sun.size + (getSmoothedAmplitude() * offset));

        //Mercury position, rotation, colour and creation
        stroke(c18487682.Planet.mercuryColourR, c18487682.Planet.mercuryColourG, c18487682.Planet.mercuryColourB);
        translate(c18487682.Planet.mercuryDistanceFromSun + (getSmoothedAmplitude() * sunDistanceOffset), c18487682.Sun.size*2 - height/2);
        rotateY(c18487682.Planet.mercuryRotation);
        //fill(c18487682.Planet.mercuryColourR, c18487682.Planet.mercuryColourG, c18487682.Planet.mercuryColourB);
        sphere(c18487682.Planet.mercurySize + (getSmoothedAmplitude() * offset));

        //Venus position, rotation, colour and creation
        stroke(c18487682.Planet.venusColourR, c18487682.Planet.venusColourG, c18487682.Planet.venusColourB);
        translate((c18487682.Planet.venusDistanceFromSun + (getSmoothedAmplitude() * sunDistanceOffset)) * 2, c18487682.Sun.size*2 - height/2);
        rotateY(c18487682.Planet.venusRotation);
        //fill(c18487682.Planet.venusColourR, c18487682.Planet.venusColourG, c18487682.Planet.venusColourB);
        sphere(c18487682.Planet.venusSize + (getSmoothedAmplitude() * offset));

        //Earth position, rotation, colour and creation
        stroke(c18487682.Planet.earthColourR, c18487682.Planet.earthColourG, c18487682.Planet.earthColourB);
        translate((c18487682.Planet.earthDistanceFromSun + (getSmoothedAmplitude() * sunDistanceOffset)) * 3, c18487682.Sun.size*2 - height/2);
        rotateY(c18487682.Planet.earthRotation);
        //fill(c18487682.Planet.earthColourR, c18487682.Planet.earthColourG, c18487682.Planet.earthColourB);
        sphere(c18487682.Planet.earthSize + (getSmoothedAmplitude() * offset));

        //Mars position, rotation, colour and creation
        stroke(c18487682.Planet.marsColourR, c18487682.Planet.marsColourG, c18487682.Planet.marsColourB);
        translate((c18487682.Planet.marsDistanceFromSun + (getSmoothedAmplitude() * sunDistanceOffset)) * 4, c18487682.Sun.size*2 - height/2);
        rotateY(c18487682.Planet.marsRotation);
        //fill(c18487682.Planet.marsColourR, c18487682.Planet.marsColourG, c18487682.Planet.marsColourB);
        sphere(c18487682.Planet.marsSize + (getSmoothedAmplitude() * offset));

        //Jupiter position, rotation, colour and creation
        stroke(c18487682.Planet.jupiterColourR, c18487682.Planet.jupiterColourG, c18487682.Planet.jupiterColourB);
        translate((c18487682.Planet.jupiterDistanceFromSun + (getSmoothedAmplitude() * sunDistanceOffset)) * 5, c18487682.Sun.size*2 - height/2);
        rotateY(c18487682.Planet.jupiterRotation);
        //fill(c18487682.Planet.jupiterColourR, c18487682.Planet.jupiterColourG, c18487682.Planet.jupiterColourB);
        sphere(c18487682.Planet.jupiterSize + (getSmoothedAmplitude() * offset));

        //Rotations
        c18487682.Sun.rotation += axialRotation;
        c18487682.Planet.mercuryRotation += axialRotation;
        c18487682.Planet.venusRotation += axialRotation;
        c18487682.Planet.earthRotation += axialRotation;
        c18487682.Planet.marsRotation += axialRotation;
        c18487682.Planet.jupiterRotation += axialRotation;
    }
}