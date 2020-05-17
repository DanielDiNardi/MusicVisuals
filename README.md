# Music Visualiser Project

Name:Daniel Di Nardi

Student Number:C18487682

## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment
This assignment is a music visualiser that takes the amplitude and frequency of the music and changes the size, colour, and movement of the objects.

Songs:
[![YouTube]()](https://www.youtube.com/watch?v=yHipUThfNOc)
[![YouTube]()](https://www.youtube.com/watch?v=PaZXPx1kdtg&t=0s)

Video:[![YouTube]()](https://www.youtube.com/watch?v=AuDpUV8au8w&feature=youtu.be)
# Instructions
To start the song, you press space, and the song begins.
# How it works
I have created three functions that hold each object on screen.

The carpet function draws a 15x15 grid of cubes. The cubes are set to expand and contract depending on the amplitude of the song.
The distance from the center to the edges is taken. With this distance, the cubes will expand and contract at different rate.
The ones in the middle move first and the ones on the edges will follow. This gives the effect of oscillation or a drop of water
hitting the still body of water (rippling). The colour of the carpet uses an offset that adds to the colour value. An if statement
checks if the value has exceeded 255 which is the last value in HSB. The rate at which the colours change is influenced by the amplitude
of the song as the rate of change will speed up if the amplitude is high and vice versa. The saturation and brightness have been lowered
in order to give the colours a soothing pastel finish. The lights function is used to add shading to the cubes as if it wasn't there,
the cubes would look bland and shallow.

```Java
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
	
//Amplitude colour
if(colour < 255)
{
	colour += getSmoothedAmplitude() * 8;
}
else
{
	colour = 0;
}
```
The rectangles function creates 19 rectangles that are mirrored. The rectangles in the middle are removed using an if statement that counts how many rectangles have been draw and doesn't draw those who block the view of the carpet. The height of the rectangles changes according to the amplitude. Unlike the carpet, the rectangles and circles use frequency instead of amplitude to change the colour of the object. Each frequency is divided into 8 segment. The values of these are mapped and assigned to a colour variable. The segments are cycled through changing the colour as each segment has a different frequencies.

```Java
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
	
//Frequency colour
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
```

The circle function consists of four circles drawn on each edge of the carpet and move outwards and increase in size depending on the amplitude.

```Java
void circles()
    {
        ellipse(width / 2.5f - (getSmoothedAmplitude() * 1000), height / 3f - (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000));
        ellipse(width / 1.65f + (getSmoothedAmplitude() * 1000), height / 3f - (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000));
        ellipse(width / 2.5f - (getSmoothedAmplitude() * 1000), height / 1.5f + (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000));
        ellipse(width / 1.65f + (getSmoothedAmplitude() * 1000), height / 1.5f + (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000), 1 * (getSmoothedAmplitude() * 1000));
    }
```

# What I am most proud of in the assignment
The thing I am most proud of in the assignment is carpet. I found a gif online of an illusion and decided to recreate it in Java. I took some time to work out all of the mechanics and figure out the positioning, size and distances of the carpet.
# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

