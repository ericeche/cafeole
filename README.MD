# Programmer's code review

##Instructions

- Install .apk
- Run app icon CaffeOle
- By default clicking on search button with no data, will look for Cafe places within a 5 mile radius
- Or Search for something else
- Single search has been deactivated. It is used only in the free version.


## Programmer's technical

- Passing objects between activities using Parcelable instead of Serialazable

- GoogleSearchType.java

        1. Array manipulation from resources
        2. Uses Parcelable objects
        3. ArrayAdapters

- GoogleType.java

        1. Implement this class from "Parcelable"
          So that you can pass this class Object to another using Intents
          Otherwise you can't pass to another actitivy
          write custom code for marshaling and unmarshaling so it creates
          less garbage objects in comparison to Serialization.

          Serialization is a marker interface,
          which implies the user cannot marshal the data according to their requirements.
          In Serialization, a marshaling operation is performed on a Java Virtual Machine (JVM)
          using the Java reflection API.
          This helps identify the Java objects member and behavior,
          but also ends up creating a lot of garbage objects.
          Due to this, the Serialization process is slow in comparison to Parcelable.

- MainActivity.java

        1. Receives parcelable object and displays object in Log.i
        2. Instantiates http connections
        3. Uses Asynch task to load google data
        4. Passes parcelable object to Asynch task



