Richard:
Image resize
Image flip: Horizontal; Vertical
Brightness adjustment
Contrast adjustment
Image export
Other error avoidance/prevention (Program quitting when saving/saveAsing/exporting an empty image)

Jasper:
Sharpen filter
Gaussian blur filter
Median filter
Exception handling
Other error avoidance/prevention
(- If user tries to open non-image file, an error message pops up.
- Inputs (kernelsize, multiplier) for Resize, MedianFilter, Contrast, Brightness, GaussianBlur throw 
IllegalArgumentException if out of bounds e.g. Below zero / not within -100 to 100.
- Sliders have been added for UI ease as well as error handling,  so user cannot entire inputs
outisde a certain range)

Luke:
Image rotations: 90◦, left; 90◦, right; 180◦
Other error avoidance/prevention (If user opens another image, pop up says to save current picture)

Aiden:
Multilingual support (not completed)

Tests:
The code was tested by Jasper and all the test files are named ___Test.java. Tests have been done 
for Brightness, Contrast, Resize and ImagePanel. Examples include checking that exceptions are 
thrown for out of bounds inputs, constructors work as expected, null image files throw a NullPointerException, 
default values are consistent etc.

We also had someone who isn't really computer savy and tell them to try to break the program, this worked great
and we fixed alot of the problems which went over our head.

Description:
Andie is a software which allows for non destructive image editing, this means that all the changes are saved
in a seperate file that keeps all the changes done to it. This means the original image is not permanantly
changed. There are several features to this program all within seperate tabs to manipulate the image. 
The program has been throughly tested to avoid any error or possiblility of misinputs by the user.

Issues:
There are some issues along the way of developing ANDIE, most notably in our team. We haven't been able to
contact Jacob at all and he hasn't contributed anything to it. Aiden unenrolled from the university
but didn't tell us until one week till due date. It was really bad timing as Aiden's tasks were multilingual
support and unit testing, therefore we had to rush the last parts. Unfortunately we hadn't quite been able to
finish multilingual support, the problem with that is the user needs to restart the program everytime they
want to switch a language. On the other hand, we managed to complete unit testing, but it is a bit rushed.