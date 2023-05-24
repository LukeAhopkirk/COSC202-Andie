## Tests:
The code was tested by Jasper and Luke, all the test files are named ___Test.java. Tests have been done for Brightness, Contrast, Resize ImagePanel, Rotate and Crop. Examples include checking that exceptions are thrown for out of bounds inputs, constructors work as expected, null image files throw a NullPointerException, default values are consistent etc. Additionally, we handed over our ANDIE to someone who was not particularly computer savy, giving them the goal to essentially try and break the program. This worked out great for both part 1 and part 2 as it highlighted some bugs, as well as usability work ons. There were many problems that simply went over our head such as prompting the user to save their current image, weird cases of combining input that did not work (such as Resize -> Emboss / Sobel), numerous problems with the drawing functions and separating the drawing functions into their own ToolBar portion. 

## Description: 

Andie is software which allows for non-destructive image editing, this means that all the changes are saved in a separate file that keeps all the changes done to it. This means the original image is not permanently changed. There are several features to this program all within separate tabs to manipulate the image. The program has been tested to avoid any error or possibility of misinputs by the user. 

## Issues: 

There are some issues along the way of developing ANDIE, most notably in our team. To begin with, there has been zero contact from Jacob, despite trying to reach out, where he has not done any work for this project, whatsoever. Aiden also unenrolled from the university but did not tell us know until one week before the part 1 due date. This was unfortunate timing as Aiden's tasks were multilingual support and unit testing, therefore we had to rush these towards the deadline. Unfortunately, we hadn't quite been able to finish multilingual support, the problem with that is the user needs to restart the program every time they want to switch a language. However, we have been able to maintain the functionality by constantly adding the translations when new parts are added to our ANDIE. 

On top of this, Richard has been significantly ill for the last few weeks leading up to the part 2 deadline, which has disrupted our work flow a fair bit. As a team, we were unable to have meetings in person, which we highly valued as a part of this project. One of the highly beneficial aspects of in-person meetings was being able to bounce ideas off each other and combat bugs & software issues within our code. In attempts to combat Richard's illnesses, we were sure to communicate often in our group chat, as well as converting to a digital meeting space. Having online meetings made working on functions such as mouse selection difficult for Luke, where this was a highly significant function, that many other operations relied on. Overall, this left us with less time to test and debug our code. Evidently, when we did get to meet up in person, we made sure to make the most of it by getting the most critical problems people were struggling with fixed first.  

## General usage for our ANDIE: 

Throughout our project we stuck with the idea to make usability a priority, especially with tasks such as drawing operations and cropping. 

Drawing operations: 

The user is able to draw circles, ovals, rectangles and lines. However, there have been many different approaches used that we have thought of and tested. What we settled on is that the user will click, drag and release the mouse to indicate how they want to draw their shapes and select a portion of the image. If they want to stop drawing a specific shape, they can press the escape key to halt any drawing actions. This is so other drawing actions cannot take place, as we have error handling to not allow the user to do multiple drawing operations at once. Furthermore, there were even higher frequencies of issues with the fill operation. As a team we ultimately decided on the user drawing 1 shape at a time, then subsequently filling it. Countless bugs & difficulties arose from trying to implement the fill operation, as we did not have enough time (predominantly due to sickness & lack of team members) to fully implement a process where our software would know when a shape has been removed via the undo function. Only the most recent drawn shapes co-ordinates are stored, where the user can then click inside the outline with their specified colour, and fill in the shape. Trying to store multiple different shapes at once, without knowing what hadn't been removed significantly decreased the usability, where random fill portions would pop-up in random places. As well as the case where shapes were overlapping. We did not want multiple shapes to be filled in at once.  

Preview for selection & drawing operations: 

When the user clicks and drags the mouse, a preview has been programmed to display on the JFrame, above the BufferedImage (either a shape or selection box). But in our testing, this has only worked for windows computers, and not macs (we are unsure about Linux, as we did not have access to such operating system). After countless queries and checking the code, we are unsure of why this happens. Additionally, we could not get the preview for our line operation to work, where again we still could not figure out why. 

Cropping: 

Our cropping system has been made so that the user can select an area but may press the escape key to discard the chosen area. Furthermore, if the user tries to crop an area that is partially outside of the image, only the area within the image will be considered for the cropping operation. Also, if the user selects an area entirely outside of the image, an error message prompts the user to select an area inside the actual image. 

Extended filters: 

In order to apply filters all the way to the edge pixels we padded the image with zeros, enlarged the image by one pixel, applied convolution to the enlarged image then finally cropped the image to its original size. There are cases where this may create a 'halo effect' around the borders, but again as a result of our issues, that is what we settled on. 

Keyboard shortcuts:

For keyboard shortcuts, we decided to only add to those that are frequenctly used, this is because having more shortcuts for filters, colour operations etc may cause confusion for the user.

## Contributions to the project

Part one requirements:

COMPLETED (Richard) - Image resize
COMPLETED (Luke) - Image rotations: 90◦, left; 90◦, right; 180◦
COMPLETED (Richard) - Image flip: Horizontal; Vertical

COMPLETED (Richard) - Brightness adjustment
COMPLETED (Richard) - Contrast adjustment

COMPLETED (Richard) - Image export
PARTIALLY COMPLETED (Aidan) - Multilingual support

COMPLETED (Jasper) - Sharpen filter
COMPLETED (Jasper) - Gaussian blur filter
COMPLETED (Jasper) - Median filter

Part two requirements:

COMPLETED (Jasper) - Extended filters
COMPLETED (Jasper) - Filters with negative results 
COMPLETED (Jasper) - Emboss and edge detection filters 
COMPLETED (Jasper) - Toolbar for common operations

COMPLETED (Richard) - Keyboard shortcuts
COMPLETED (Richard) - Macros for record and replay of operations 

COMPLTED (Luke) - Crop to selection

COMPLETED (Jasper) - Mouse selection of rectangular regions
COMPLETED (Jasper) - Drawing functions – rectangle, ellipse, line
COMPLETED (Jasper) - Show us something. . . Coloursplash (user chooses colour, all other colours turn B&W) & Satuartion filter

General Requirements:

Exception handling (Jasper)
Other error avoidance/prevention (Jasper/Luke/Richard)
Unit Tests (Jasper/Luke)