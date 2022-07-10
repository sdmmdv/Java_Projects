# List of projects

- [Geometric Shapes](#shapes)
- [Rattlesnake](#rattleSnake)
- [Four Game](#fourgame)
- [Connect Client](#connectClient)


## Geometric Shapes <a name="shapes"></a>
### Choose a point on the plane, and fill a collection with several regular shapes 
### (circle, regular triangle, square, regular hexagon). How many shapes contain the given point?
Each shape can be represented by its center and side length (or radius), if we assume that one
side of the polygons are parallel with x axis, and its nodes lies on or above this side.
Load and create the shapes from a text file. The first line of the file contains the number of the
shapes, and each following line contain a shape. The first character will identify the type of the
shape, which is followed by the center coordinate and the side length or radius.
Manage the shapes uniformly, so derive them from the same super class.

For detailed info navigate to --> [docs](./Shapes/docs)

## Rattlesnake <a name="rattleSnake"></a>
We have a rattlesnake in a desert, and our snake is initially two units long (head and rattler). We have to collect with our snake the foods on the level, that appears randomly. Only one food piece is placed randomly at a time on the level (on a field, where there is no snake). The snake starts off from the center of the level in a random direction. The player can control the movement of the snake’s head with keyboard buttons.
If the snake eats a food piece, then its length grows by one unit.
It makes the game harder that there are rocks in the desert. If the snake collides with a rock, then the game ends.
We also lose the game, if the snake goes into itself, or into the boundary of the game level.
In these situations, show a popup messagebox, where the player can type his name and save it together with the amount of food eaten to the database.

Create a menu item, which displays a highscore table of the players for the 10 best scores. Also, create a menu item which restarts the game.

For detailed info navigate to --> [docs](./RattleSnake/docs)

## Four Game <a name="fourgame"></a>
This a two-player game is played on a board consists of n x n fields, where each field contains a value between 0 and 4. Initially, all the fields contain the value of 0. If a player chooses a field, then the value of the field and its neighbors incremented by one (if the value is less than 4). The player’s score represents how many fields did he make to have the value of 4. If a value of a field reaches 4, then the field is colorized with the color of the actual player (red or blue). The game ends, when all fields have the value of 4. The player having the higher score wins.

Implement this game, and let the board size be selectable (3x3, 5x5, 7x7). The game should recognize if it is ended, and it has to show in a message box which player won. After this, a new game should be started automatically.

For detailed info navigate to --> [docs](./FourGame/docs)

## ConnectClient <a name="connectClient"></a>
Implement simultaneous client-server interaction using specific domain and port.