package com.tomstoneberg.processing;

import processing.core.PApplet;

import java.io.File;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public abstract class Template extends PApplet
{
    private boolean savePdf;

    @Override
    public void settings()
    {
        size(800, 800);
    }

    @Override
    public void setup()
    {
    }

    @Override
    public void draw()
    {
        if(savePdf)
        {
            beginRecord(PDF, getFilename("pdf"));
        }

        doDraw();

        if(savePdf)
        {
            savePdf = false;
            endRecord();
        }

    }

    abstract protected void doDraw();

    @Override
    public void keyReleased()
    {

        if (Character.toLowerCase(key) == 's') saveFrame(getFilename("png"));
        if (Character.toLowerCase(key) == 'p') savePdf = true;
        if (key == DELETE || key == BACKSPACE) background(255);
    }

    private String getFilename(String extension)
    {
        File resourcesDirectory = new File("src/main/resources/output");
        String dir = resourcesDirectory.getAbsolutePath();
        LocalDateTime date = LocalDateTime.now();
        String text = date.format(ISO_LOCAL_DATE_TIME);
        return String.format("%s/%s-%s.%s", dir, getClass().getSimpleName(), text, extension);
    }
}
