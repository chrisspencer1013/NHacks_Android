# NHacks_Android
## Hackathon - Intro to Android

1. Install android studio from [here](https://developer.android.com/studio)
    - make sure to install with an android emulator if you are planning on using it (you can do it later too)
2. Select `Start a new Android Studio project`
3. Select `Empty Activity` (I encourage you to check out some of the boilerplates next time!) 
4. Configure your project:
  - We will name it `RPS`
  - Don't modify the package name for now
  - Choose where you'd like to save it (default is fine)
  - Language: `Java` 
  - API level: 21 (5.0 Lollipop)

Notice that we already have quite a bit here. We don't need to worry about most of this for now, so in the interest of time, I will proceed. If you are curious as to what any of this is, let me know. 

**Tangent:** everything created for you was done for a reason. Again, google can help, but there is a really useful function built in to Android Studio (and many other IDEs) -> Right click on a keyword that you want to know more about and select 'Go to' and 'Definition'. This will bring you to where the keyword is defined, which can help in your exploration of Android programming.

5. Open up your main java file

The important thing to get out of this is that this **class** `extends` AppCompatActivity, and upon creation it invokes `setContentView`, which will link it to our `activity_main.xml`, which defines how our application will be layed out as well as some functionality. You will see this in the upcoming steps.

*Does anyone know what a class is?* If not, let's google it together and find some information on it.

(Just in case if you can't find much: [Here is one](https://medium.freecodecamp.org/object-oriented-programming-concepts-21bb035f7260), and [here is another](https://dev.to/wathsara/java-object-oriented-programing-oop-32ao))

6. Check out the XML 
*(I will show how to get to text view and help explain what is going on here)*

**BUILD!** (you've built a hello world app, congrats!)
  
***REMEMBER TO MAKE USE OF REFACTORING/RENAMING WITHIN ANDROID STUDIO***

7. Create 3 buttons, change text, rename ids
8. Constraint layout stuff (ugh, this is just kinda trial and error for the most part)

**BUILD!** 

*So now we three buttons...that do nothing...yahooooo*

Now for **FUNCTIONALITY!**
What do we want to do? Use case time!
  - User picks an option (with buttons)
  - Computer picks an option
    - any ideas on how?
  - Compare two options, determine winner
  - Tell user results
    - any ideas on how?

*How do we add functionality to the buttons?* 

We want to listen for an event! An event in android is a lot like an event in real life. Let's look at the use case of "I went to the store". "Entering the store" or "purchasing 15 frozen pizza" could be events apart of that use case. Similarly in android, event occur in execution of a use case. Examples could be: changing focus to another app or section of the app, pressing a key, or *clicking a button*.

The event that occurs when you click a button is an `onClick` event, which means we will use an `OnClickListener` to listen for this event. *(If this seems like a bit of a leap in logic, you are kinda right. In the real world, you'd likely be looking though documentation and googling to find out what information you need.)*

For this we will use an interface. similar to inheritance (`extends`), but different. Back to google! Stack overflow is also an incredible resource!

Just in case, here's a good link: https://stackoverflow.com/questions/10839131/implements-vs-extends-when-to-use-whats-the-difference

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener
```

When you do this, Android Studio will yell at you with an error. Right clicking on the error will give you the option to override. This will generate code for you :) 

When we override a method, we are taking the inherited class from the parent and overriding it to do something new. A good example would be from the animal class earlier. Most animals can run, but they all run at different speeds. Overriding the `run()` method could look like this:

Original:
```java
    public void run(){
        System.out.println("The animal is running");
    }
```
Overriding it for a slower animal:
```java
    @override
    public void run() {
        System.out.println("The animal is running slowly");
    }
```

Back to android! Now we have the `onClick` method to work with! We will need to override the original (just like the `onCreate` earlier, it was using the functionality of another class/interface with a twist)

So within our `MainActivity` class, we will put in the following method:

```java
    @Override
    public void onClick(View v) {

    }

```

and we will associate each of the buttons with it by adding an onclick event to the XML, for example:

```xml
    <Button
        android:id="@+id/btnScissors"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="52dp"
        android:layout_marginBottom="64dp"
        android:text="Scissors"
+       android:onClick="onClick"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

```

Let's store some information from the view that is passed with the method.

```java
    @Override
    public void onClick(View v) {
        String userChoice = null; //This will store the user's choice in a string
        int clickedId = v.getId(); //This will store the id of the button that was clicked


    }
```
Autocomplete and intellisense is your friend! (most of the time...)

Initially the id doesn't look like much to us. It's just a bunch of numbers, right? Not quite! This number uniquely identifies each button, meaning we can always use this number to trace back to which was pressed.

Next is to use that id to assign a choice of Rock, Paper, or Scissors to each. *How can we do this?*

One way would be strings. This allows us to have a human-readable choice instead of just a big number.

Now for some conditional logic to assign the correct string. When the id of the clicked object is the same as the id of one of the buttons, we need to set the variable `userChoice` to capture that for later. We will do this with an equality operator `==`

```java
    @Override
    public void onClick(View v) {
        String userChoice = null; // Note: this is here due to 'scope'
        int clickedId = v.getId();

        if (clickedId == R.id.btnPaper) {
            userChoice = "Paper";
        }

    }
```

Similar with the other cases, but using an `else` keyword to tell the computer this `if` block isn't quite done:

```java
    @Override
    public void onClick(View v) {
        String userChoice = null;
        int clickedId = v.getId();

        if (clickedId == R.id.btnPaper) {
            userChoice = "Paper";
        }
        else if (clickedId == R.id.btnRock) {
            userChoice = "Rock";
        }
        else if (clickedId == R.id.btnScissors) {
            userChoice = "Scissors";
        }
    }
```

**BUILD!**
Wait, this is kind of a bummer. The app still doesn't do anything. Sure, the app knows what the user selected, but how do we verify that ourselves? 

Let's log it! After the block of code we just finished, let's call Log at the [approprate level](https://stackoverflow.com/questions/7959263/android-log-v-log-d-log-i-log-w-log-e-when-to-use-each-one)

```java
    Log.d("RPS", userChoice);
```

**BUILD!** 

This time we should be able to see our choice being output to the log (LogCat in the bottom view of Android Studio)

Does it work?

If no ... *oof* Make sure to check for semicolons at the end of lines. The red squigglies should help you to pinpoint where the issue is. If not, raise your hand and we can help!

If yes, YAY! We completed the first task of our use case! Onto the next: making the computer pick an option.

We can use the `Random` library to simulate another player's choice. Import that bad boi:

```java
import java.util.Random;
```

Then we can access its functionality to randomly choose an option:

```java
    Random rand = new Random();
    int computerInt = rand.nextInt(2); //Random int from 0 to 2 (inclusive)
```

Instead of using if/elseif logic again, do we have any other options for assigning a string based upon which integer was picked by the computer?

Yes we do! A `switch`/`case` statement!

```java
        String computerChoice = null;
        switch (computerInt)
        {
            case 0:
                computerChoice = "Paper";
                // These 'breaks' are required in this case
                // it tells the JVM to stop executing the switch 
                // and continue with the rest of the code
                break; 
            case 1:
                computerChoice = "Rock";
                break;
            case 2:
                computerChoice = "Scissors";
                break;
        }
```

Nice! Now we have the computer making a choice! Two tasks to go.

Up next is comparing the choices from user to computer and determining a winner.

*How do we compare?* One good way to look at this is to determine the outputs and work back to what conditions are required to get there. This keeps logic a bit tighter, as you will see.

The first case is a tie. We have a tie when both choices are the same, so we will use an equality operator again: `==`

```java
        String results = null;
        if (userChoice == computerChoice)
        {
            results = "Tied!";
        }
```
The second case is winning. Where does that happen? 

Let's map it out really quick so we can see all cases. This can be read as right column (user), intersection, top row (computer) -> so the user's `Rock` `Loses` against the computer's `Paper`. This is overkill, but it helps us to see every win condition in a single place. This can be a valuable tool when things get more complex than 3 options. 

|          | Rock | Paper | Scissors |
|----------|:----:|:-----:|:--------:|
| Rock     | T    | L     | W        |
| Paper    | W    | T     | L        |
| Scissors | L    | W     | T        |

So the win cases can be applied in logic like this using the logical and operator `&&`

```java
  else if (userChoice == "Paper" && computerChoice == "Rock")
  {
      results = "Win!"
  }
  else if (userChoice == "Rock" && computerChoice == "Scissors")
  {
      results = "Win!"
  }
  else if (userChoice == "Scissors" && computerChoice == "{Paper")
  {
      results = Win!"
  }
```

*Is there a better way to do this?* We are repeating the same exact code in each else if statement. 

We can use the logical or `||` in this case where one or the other might be true. 

Here is a much better and more maintainable piece of code that accomplishes the same thing. This is what was meant earlier by "keep logic tight":

```java
        else if ((userChoice == "Paper" && computerChoice == "Rock")
                || (userChoice == "Rock" && computerChoice == "Scissors")
                || (userChoice == "Scissors" && computerChoice == "Paper"))
        {
            result = "Win!";
        }
```

Mkay, now let's repeat all that work and find all losing cases. *Unless someone was a better idea...*

Let's be lazy (aka super efficient :point_left::sunglasses::point_right:) and use `else` to cover all other cases.

Now the entire block looks like this:

```java
        String results = null;
        if (userChoice == computerChoice)
        {
            results = "Tied!";
        }        
        else if ((userChoice == "Paper" && computerChoice == "Rock")
                || (userChoice == "Rock" && computerChoice == "Scissors")
                || (userChoice == "Scissors" && computerChoice == "Paper"))
        {
            results = "Win!";
        }
        else
        {
            results = "Lose!";
        }
```

One more down, and for the last bit of logic: display the results. *any ideas? what have you seen in apps you use?* 

For this we will use Toast messages. 

First, import the functionality:

```java
import android.widget.Toast;
```

Then let's use it to display a toast message:

```java
    Toast.makeText(MainActivity.this, results, Toast.LENGTH_SHORT).show();
```

And we are "done"! Here is the code we came up with:

`JAVA`
```java
package com.example.rps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        String userChoice = null;
        int clickedId = v.getId();

        if (clickedId == R.id.btnPaper) {
            userChoice = "Paper";
        }
        else if (clickedId == R.id.btnRock) {
            userChoice = "Rock";
        }
        else if (clickedId == R.id.btnScissors) {
            userChoice = "Scissors";
        }

        Random rand = new Random();
        int computerInt = rand.nextInt(2);

        String computerChoice = null;
        switch (computerInt)
        {
            case 0:
                computerChoice = "Paper";
                break;
            case 1:
                computerChoice = "Rock";
                break;
            case 2:
                computerChoice = "Scissors";
                break;
        }

        String results = null;
        if (userChoice == computerChoice)
        {
            results = "Tied!";
        }
        else if ((userChoice == "Paper" && computerChoice == "Rock")
                || (userChoice == "Rock" && computerChoice == "Scissors")
                || (userChoice == "Scissors" && computerChoice == "Paper"))
        {
            results = "Win!";
        }
        else
        {
            results = "Lose!";
        }

        Toast.makeText(MainActivity.this, results, Toast.LENGTH_SHORT).show();
    }
}

```


`XML` (constraints might be different, this is from a slightly different project)
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/btnScissors"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="52dp"
        android:layout_marginBottom="64dp"
        android:text="Scissors"
        android:onClick="onClick"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/btnPaper"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginBottom="64dp"
        android:text="Paper"
        android:onClick="onClick"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/btnRock"
        app:layout_constraintHorizontal_bias="0.48"
        app:layout_constraintStart_toEndOf="@+id/btnScissors"
        app:layout_constraintTop_toBottomOf="@+id/imagePlayer"
        app:layout_constraintVertical_bias="1.0" />

    <Button
        android:id="@+id/btnRock"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="52dp"
        android:layout_marginBottom="64dp"
        android:text="Rock"
        android:onClick="onClick"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />


</android.support.constraint.ConstraintLayout>
```

But are we really done? Never! Time to refactor! No code is perfect, and this has many opportunities for improvement that would make the code more clear or more maintainable to us and others working on our code.

*How could we improve this? Think creatively!* 

Ways to improve:
- Use `ENUM` to standardize and clean up choices (with built in random choices!)
- Add photos of rock, paper, and scissors to show what the user and computer chose
- Refactor -> Pull out common functionality to reusable methods
- Multiplayer! Over bluetooth or wifi!
- clean up user/computer choice comparison with use of variables
