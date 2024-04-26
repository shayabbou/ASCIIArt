## Ascii Art Generator

This project converts image files into ASCII art.
The program accepts a relative image path, ASCII character collections and resolution.
The output can be displayed in the console or saved in an HTML file.


## Instructions

1. Place the image file (PNG/JPEG) in the ASCIIArt folder.
2. Run the Shell file - `ascii_art.Shell`.
3. The user interface will run in the console.
4. Add ASCII characters to the character collection:
    - By default, the ASCII character collection includes 0-9.
    - Additional characters can be added using the following commands:
        - 'add <char>'
        - 'add <char>-<char>'
        - 'add all' (will add all ASCII Chars).
        - 'add space'
5. Remove ASCII chars from the character collection :
    - 'remove <char>'
    - 'remove <char>-<char>'
    - 'remove all'
    - 'remove space'
    - If the character doesn't exist in the collection, no error message will be displayed.
6. Change resolution:
    By default, the resolution is set to 128.
    The minimum and maximum resolutions are determined by the image.
    'res up': doubles the current resolution.
    'res down': divide the current resolution in 2.
    If you reach one of the resolution limits, an informative error message will be displayed.
7. Choose Image :
   By default the image is 'cat.jpeg'.
   To use a different image, place the image file (PNG/JPEG) in the ASCIIArt folder.
   Use the command: image <relative_path.jpeg/png>.
   If the image is not found, an informative error message will be displayed.
8. Output Options :
   By default, the algorithm output is displayed in the console.
   To save the output in an HTML file, use "output html".
   The HTML file will be saved in the "out" folder within the ASCIIArt directory.
9. Run the Algorithm:
   Use the command asciiArt to execute the algorithm with the current parameters.
10.Example:
   I ran the algorithm on 'cat.jpeg', added all ASCII characters, and specified "output html".
   The "out_256.html" file is the result with a resolution of 256.
   The "out_512.html" file is the result with a resolution of 512.

## Installation

1. Fork the Repository: Click on the "Fork" button in the upper-right corner of the repository's page. This action will create a copy of the repository in your GitHub account.
2. Clone this repository to your local machine using 'git clone https://github.com/shayabbou/ASCIIArt.git'
3. Navigate to the repository directory by running cd ASCIIArt.
4. Open \ASCIIArt\src\ascii_art\Shell file and run it.
5. Use the user interface in order to run the program with the parameters you need.
