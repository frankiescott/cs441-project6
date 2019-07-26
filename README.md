# cs441-project6

This application is 

I started by creating a generic splash screen image to display upon application startup. I then created a menu screen for the splash screen to switch to and programmed the transition to take place once all game assets have been loaded, and if the splash screen displayed for at least three seconds. From here, I created the button interface using the `glassy-ui` skin for the menu screen featuring a 'Play' button to launch the game, a 'How to Play' button to launch the instructions screen, and a 'Leaderboard' button to launch the leaderboard screen. I then created a 'Back' button for the how to play and leaderboard screens so the user has fluent control over the menu interface.

For the leaderboard screen, I used a scene2d library object called `ScrollPane` within a table container to display a scrollable list of entries. I loaded in placeholder text to dial in desired parameters, then began implementing the loading procedure to dynamically populate the leaderboard. At first, I was going to use an internal data text file to save and load leaderboard information, but I discovered the ability to utilize the internal game preferences provided by the LibGDX library. I created a `leaderboard` entry in the preferences to store 10 `<key, value>` pairs with the key respective to the player's position on the leaderboard. The value stored is the player's name and their score. For example, I can access the #1 listed player and their respective high score with the key `"1"` to return a value such as `Frankie 922`

For the how to play screen, I learned how to use a library object called `FreeTypeFontGenerator` to generate fonts that scale smoothly within the application. I imported `.ttf` font files to the project and used them to draw placeholder text to the screen that will be updated once implementation of the game is finished.

The game screen consists of placeholder code that bounces the default `badlogic` image back and fourth against the boundaries of the screen. I imported my object and interface structure from Project 2 that I will use to implement the code for the player and enemies at a later time.

Finally, I created custom graphics to use for a splash screen, background, and a logo for the menu screen. 

# Development Schedule

July 19th - Project initialization & splash screen

July 20th - Main menu screen & splash screen transition

July 21st - Game screen & how to play screen

July 23rd - Leaderboard screen

July 24th - Leaderboard data setup

July 26th - Leaderboard data loading, graphics, and positioning final touches
