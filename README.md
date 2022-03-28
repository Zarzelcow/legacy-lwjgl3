# legacy-lwjgl3
A hacky over-engineered project that runs LWJGL3 on legacy-fabric minecraft versions (current only tested 1.8.9),
Allowing you to use modern LWJGL features on older minecraft versions.

First though credit are important
## Credits
A whole lot of this code is just code from the original LWJGL 2 project modified to work with LWJGL 3, Thanks so much to the LWJGL devs for making their license so permissive.

The next big help goes to gudenau for the original F*rge mod <https://github.com/gudenau/MC-LWJGL3> that did the same, thanks as a big portion of this code is from that project.

## Read before using
this project is still currently missing many features that would make it not such a pain to use
* Manually excluding lwjgl 2 to use this (including end uses!!) <<-- | These
* scary classloader tricks used to redefine whole LWJGL classes <<-- | All
* ships lwjgl jar in jar completely bloating file sizes <<--         | Need Fixing
---
However, if you are like me this is not enough to stop you, here are some things you should know

Using this in a dev environments will require you to manually exclude lwjgl2 from gradle, i know dont complain your already crazy enough to want to do this,
an example of doing this excludes all org.lwjgl.lwjgl group packages from dependencies
```groovy
❯ build.gradle

configurations.all {
    exclude group: "org.lwjgl.lwjgl"
}
```
Past that you need to get the mod loaded somehow,
I often end up falling back to modrinth for this but jitpack will have to work,<br> get the built artifacts from <https://jitpack.io/#Zarzelcow/legacy-lwjgl3> and paste them into your build.gradle fallow the guide on their website

## Performance Comparisons
The intention of this project was to give developers access to LWJGL 3 features on legacy versions of minecraft
and not to be a performance increasing mod but while we are here why not compare the two.

The settings used to test the two versions are the same?
I couldn't get vbos turned on in legacy-lwjgl for some reason so there might be some untapped potential there
```toml
JDK: temurin-17 (Adopt OpenJDK Hotspot 17)
JVM Options:
    -client
    -server
    -DLWJGL_DISABLE_XRANDR=true
mods:
    - fabricloader 0.13.3,
    - java 17,
    - minecraft 1.8.9
World seed: 123
Options changed from default:
- Map FPS: unlimited
- VSync: Disabled
```
Left is LWJGL2, Right is legacy-lwjgl3<br>
![results]() <br>
as you can see barely any difference, LWJGL2 hovered around 165-172 while legacy-lwjgl3 hovered around 175-183, maybe VBOs could increase this by a bit

## Contributing
This project is a work in progress, if you have any suggestions or want to contribute feel free to open an issue or pull request<br>
**Seriously please I suck at code quality and need people to help me**<br>***Really i w ill accept it im begging you my code quality is so bad 😭***![tiny potato]()

